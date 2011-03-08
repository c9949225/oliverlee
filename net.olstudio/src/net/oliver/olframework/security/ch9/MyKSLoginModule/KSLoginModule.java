import javax.security.auth.x500.X500Principal;
import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.*;
import java.util.*;
import java.security.cert.Certificate;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import javax.security.auth.spi.LoginModule;

public class KSLoginModule implements LoginModule {
   // initial state
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map options;

    // configurable option
    private boolean debug = false;

    // the authentication status
    private boolean succeeded = false;
    private boolean commitSucceeded = false;

    // username and password
    private String username;
private char[] password;
    private char[] pkpassword;
private Certificate[] cchain;
    private javax.security.auth.x500.X500Principal userPrincipal;
private KeyStore ks;

    public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map sharedState, Map options) {
 
	  this.subject = subject;
	  this.callbackHandler = callbackHandler;
	  this.sharedState = sharedState;
	  this.options = options;

	  // initialize any configured options
	  debug = "true".equalsIgnoreCase((String)options.get("debug"));
    }


    public boolean login() throws LoginException {

	// prompt for a user name and password
	  if (callbackHandler == null)
	      throw new LoginException("Error: no CallbackHandler available " +
		  	  "to garner authentication information from the user");
     String keyStoreURL= (String)options.get("keyStoreURL");
       Callback[] callbacks = new Callback[5];
       TextOutputCallback txtCallback =
		         new TextOutputCallback(
				    TextOutputCallback.INFORMATION,
		        "ÇëµÇÂ¼ÃÜÔ¿¿â");
        ConfirmationCallback confirmCallback =
		             new ConfirmationCallback(
		             ConfirmationCallback.INFORMATION,
		             ConfirmationCallback.OK_CANCEL_OPTION,
		             ConfirmationCallback.OK);

	  callbacks[0]= txtCallback; 
	  callbacks[1] = new NameCallback("ÃÜÔ¿¿â±ðÃû");
	  callbacks[2] = new PasswordCallback("ÃÜÔ¿¿â±£»¤¿ÚÁî ", false);
	  callbacks[3] = new PasswordCallback("Ë½Ô¿±£»¤¿ÚÁî ", false);
	  callbacks[4] = confirmCallback; 

 
	  try {

	    callbackHandler.handle(callbacks);
	    username = ((NameCallback)callbacks[1]).getName(); 
	    char[] tmpPassword = ((PasswordCallback)callbacks[2]).getPassword();
	    if (tmpPassword == null) {
		   // treat a NULL password as an empty password
		   tmpPassword = new char[0];
	    }
	    password = new char[tmpPassword.length];
	    System.arraycopy(tmpPassword, 0,
			password, 0, tmpPassword.length);
	    ((PasswordCallback)callbacks[2]).clearPassword();

tmpPassword = ((PasswordCallback)callbacks[3]).getPassword();
	    if (tmpPassword == null) {
			tmpPassword = new char[0];
	    }
	    pkpassword = new char[tmpPassword.length];
	    System.arraycopy(tmpPassword, 0,
			pkpassword, 0, tmpPassword.length);
	    ((PasswordCallback)callbacks[3]).clearPassword();
 
	} catch (java.io.IOException ioe) {
	    throw new LoginException(ioe.toString());
	} catch (UnsupportedCallbackException uce) {
	    throw new LoginException("Error: " + uce.getCallback().toString() +
		" not available to garner authentication information " +
		"from the user");
	}

	// print debugging information
	if (debug) {
	    System.out.println("\t\t[SampleLoginModule] " +
				"user entered user name: " +
				username);
	    System.out.print("\t\t[SampleLoginModule] " +
				"user entered password: ");
	    for (int i = 0; i < password.length; i++)
		   System.out.print(password[i]);
	    System.out.println();
	}

int confirmationResult = confirmCallback.getSelectedIndex();

	if (confirmationResult == ConfirmationCallback.CANCEL) {
		     throw new LoginException("Login cancelled");
	}
	try {
		  ks = KeyStore.getInstance("JKS");
	      InputStream in = new URL(keyStoreURL).openStream();
	      ks.load(in, password);
	      in.close();
	      Key privateKey =
	     	  ks.getKey(username, pkpassword);
	      if (privateKey == null
		        || !(privateKey instanceof PrivateKey)) {
		throw new FailedLoginException(
		    "Unable to recover key from keystore");
	      }
	      succeeded = true;
	      return true;

	}
	catch (Exception e) {
succeeded = false;
	        throw new LoginException(
		         "Error in login to Keystore " + e);
}
 }
 public boolean commit() throws LoginException {
	if (succeeded == false) {
	    return false;
	} else {
	    // add a Principal (authenticated identity)
	    // to the Subject
        try{
cchain =
		       ks.getCertificateChain(username);
X509Certificate certificate = (X509Certificate)cchain[0];
userPrincipal = new javax.security.auth.x500.X500Principal
		   (certificate.getSubjectDN().getName());
    }catch(Exception e){
         throw new LoginException(e.toString());
    }
	    subject.getPrincipals( ).add(userPrincipal);

	    if (debug) {
		System.out.println("\t\t[SampleLoginModule] " +
				"added SamplePrincipal to Subject");
	    }

	    // in any case, clean out state
	    username = null;
	    for (int i = 0; i < password.length; i++)
		password[i] = ' ';
	    password = null;

	    commitSucceeded = true;
	    return true;
	}
    }

    public boolean abort() throws LoginException {
	   if (succeeded == false) {
	       return false;
	   } else if (succeeded == true && commitSucceeded == false) {
	      // login succeeded but overall authentication failed
	       succeeded = false;
	       username = null;
	       if (password != null) {
		for (int i = 0; i < password.length; i++)
				    password[i] = ' ';
		password = null;
	    		}
    userPrincipal = null;
} else {
	    // overall authentication succeeded and commit succeeded,
		    // but someone else's commit failed
    logout();
	}
	return true;
    }

    public boolean logout() throws LoginException {

	subject.getPrincipals().remove(userPrincipal);
	succeeded = false;
	succeeded = commitSucceeded;
		username = null;
	if (password != null) {
		    for (int i = 0; i < password.length; i++)
					password[i] = ' ';
	    password = null;
		}
	userPrincipal = null;
		return true;
    }
}

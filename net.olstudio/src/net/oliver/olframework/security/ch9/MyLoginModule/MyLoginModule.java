import java.util.*;
import java.io.IOException;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import javax.security.auth.spi.*;

public class MyLoginModule implements LoginModule {

    private Subject subject;
    private CallbackHandler callbackHandler;
    private boolean succeeded = false;
    private String username;
    private String password;
    private MyPrincipal userPrincipal;

    public void initialize(Subject subject, CallbackHandler callbackHandler,
    	              Map sharedState, Map options) {
        this.subject = subject;
	this.callbackHandler = callbackHandler;
    }

    public boolean login() throws LoginException {
	if (callbackHandler == null){
	    throw new LoginException("Error: no CallbackHandler available");
	}
	Callback[] callbacks = new Callback[2];
	callbacks[0] = new NameCallback("user name: ");
	callbacks[1] = new PasswordCallback("password: ", false);
 
	try {
	    callbackHandler.handle(callbacks);
	    username = ((NameCallback)callbacks[0]).getName();
	    char[] tmpPassword = ((PasswordCallback)callbacks[1]).getPassword();
            password=new String(tmpPassword);
	} catch (java.io.IOException ioe) {
	    throw new LoginException(ioe.toString());
	} catch (UnsupportedCallbackException uce) {
	    throw new LoginException(uce.toString( ));
	}
	
	if (username.equals("testUser") && password.equals("testPassword")){
            succeeded=true;
	    return true;
	} else {
	    succeeded = false;
   	    throw new FailedLoginException("Error name//password pair");
	}
    }

    public boolean commit( ) throws LoginException {
	if (succeeded == false) {
	    return false;
	} 
	else {
	    userPrincipal = new MyPrincipal(username);
	    if (!subject.getPrincipals().contains(userPrincipal)){
		subject.getPrincipals().add(userPrincipal);
	    }
	    username=null;
	    password=null;
	    return true;
	}
    }

    public boolean abort( ) throws LoginException {
	if (succeeded == false) {
	    username = null;
 	    password=null;
	    userPrincipal = null;
	    return false;
	} else {
	    logout();
	}
	return true;
    }

    public boolean logout() throws LoginException {
	subject.getPrincipals().remove(userPrincipal);
	username=null;
	password=null;
	userPrincipal=null;
	succeeded = false;
	return true;
    }
}

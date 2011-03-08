package net.oliver.olframework.security.ch8.mypermission.OwnPerm;
import java.io.*;
public class MyRate{
     private double rate=0.8;
     public void setRate(double r){
      	SecurityManager sm = System.getSecurityManager();
	if (sm != null) {
	          sm.checkPermission(new RatePermission("setR"));
	}
          rate=r;
     }

     public double getRate( ){
        return rate;
     }
}

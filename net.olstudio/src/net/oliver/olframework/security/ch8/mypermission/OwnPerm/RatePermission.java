package my.test;
import java.security.*;
public final class RatePermission extends BasicPermission {
    public RatePermission(String name){
	super(name);
    }
    public RatePermission(String name, String actions){
	super(name, actions);
    }
}
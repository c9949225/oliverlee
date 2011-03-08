import java.io.*;
import java.security.*;

public final class SuffixPermission extends BasicPermission {
    public SuffixPermission(String name){
	super(name);
    }
    public SuffixPermission(String name, String actions){
	super(name, actions);
    }
}


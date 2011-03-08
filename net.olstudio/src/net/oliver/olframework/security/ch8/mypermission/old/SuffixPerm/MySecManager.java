package net.oliver.olframework.security.ch8.mypermission.old.SuffixPerm;

import java.io.*;
import java.security.*;
public class MySecManager extends SecurityManager {
  public void checkRead(String file) {
              int p1=file.lastIndexOf(".");
              int p2=file.length();
              String suffix=file.substring(p1,p2);
       checkPermission(new SuffixPermission(suffix));
       checkPermission(new FilePermission(file,"read"));
  }
}

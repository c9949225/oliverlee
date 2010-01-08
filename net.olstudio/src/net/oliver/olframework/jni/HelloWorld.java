package net.oliver.olframework.jni;

public class HelloWorld {
	static {
		System.loadLibrary("helloworld");
	}

	public native void print();

	public static void main(String[] args) {
		HelloWorld hello = new HelloWorld();
		hello.print();
	}
}

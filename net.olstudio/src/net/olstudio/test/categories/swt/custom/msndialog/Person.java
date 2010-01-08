package net.olstudio.test.categories.swt.custom.msndialog;

public class Person {
	private String userName;

	private String password;

	public Person(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public String toString() {
		return userName;
	}
}

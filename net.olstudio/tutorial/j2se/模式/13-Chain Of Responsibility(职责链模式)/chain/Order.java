package chain;

public class Order {
	
	private int cache;
	private String message;

	public Order(int c) {
		cache = c;
		message = "";
	}

	public int getCache() {
		return cache;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		message = msg;
	}
}

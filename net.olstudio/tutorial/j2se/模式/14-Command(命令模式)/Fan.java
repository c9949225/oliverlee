public class Fan {
	private String state;

	public Fan() {
		state = "off";
	}

	public void startRotate() {
		System.out.println("Fan is Being started !");
		state = "on";
	}

	public void stopRotate() {
		System.out.println("Fan has been stopped!");
		state = "off";
	}

	public String getState() {
		return state;
	}
}
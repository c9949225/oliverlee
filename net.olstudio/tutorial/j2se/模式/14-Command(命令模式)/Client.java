public class Client {
	public static void main(String[] args) {
		Fan fan = new Fan();
		Light light = new Light();
		Dealer dealer = new Dealer();
		Command command = new LightOnCommand(light);
		dealer.deal(command);
		command = new FanOnCommand(fan);
		dealer.deal(command);

		command = new LightOnCommand(light);
		dealer.deal(command);
		command = new FanOnCommand(fan);
		dealer.deal(command);

		command = new LightOffCommand(light);
		dealer.deal(command);

		command = new FanOffCommand(fan);
		dealer.deal(command);

		while (dealer.unDeal());
	}
}
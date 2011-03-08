public class MotorolaFactory implements MobileFactory {
	public Mobile produceMobile() {
		return new Motorola();
	}
}
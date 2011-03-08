public class NokiaFactory implements MobileFactory {
	public Mobile produceMobile() {
		return new Nokia();
	}
}
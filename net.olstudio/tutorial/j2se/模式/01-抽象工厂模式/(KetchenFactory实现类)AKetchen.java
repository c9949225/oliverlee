public class AKetchen implements KetchenFactory {
	public Food getFood() {
		return new Milk();
	}

	public TableWare getTableWare() {
		return new Spoon();
	}
}
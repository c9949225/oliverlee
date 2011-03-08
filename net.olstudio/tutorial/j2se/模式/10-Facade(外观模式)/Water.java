public class Water {
	boolean waterIsBoiling;

	public Water() {
		setWaterIsBoiling(false);
		System.out.println("纯净的水准备好了");
	}

	public void setWaterIsBoiling(boolean isWaterBoiling) {
		waterIsBoiling = isWaterBoiling;
	}

	public boolean getWaterIsBoiling() {
		return waterIsBoiling;
	}

	public void boilFacadeWater() {
		setWaterIsBoiling(true);
		System.out.println("水在沸腾");
	}
}
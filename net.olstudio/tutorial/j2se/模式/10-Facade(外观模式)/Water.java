public class Water {
	boolean waterIsBoiling;

	public Water() {
		setWaterIsBoiling(false);
		System.out.println("������ˮ׼������");
	}

	public void setWaterIsBoiling(boolean isWaterBoiling) {
		waterIsBoiling = isWaterBoiling;
	}

	public boolean getWaterIsBoiling() {
		return waterIsBoiling;
	}

	public void boilFacadeWater() {
		setWaterIsBoiling(true);
		System.out.println("ˮ�ڷ���");
	}
}
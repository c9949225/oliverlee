public class TeaCup {
	private boolean teaBagIsSteeped;
	private Water facadeWater;
	private TeaBag facadeTeaBag;

	public TeaCup() {
		setTeaBagIsSteeped(true);
		System.out.println("�豭׼������");
	}

	public void setTeaBagIsSteeped(boolean isTeaBagSteeped) {
		teaBagIsSteeped = true;
	}

	public boolean getTeaBagIsSteeped() {
		return teaBagIsSteeped;
	}

	public void addFacadeTeaBag(TeaBag facadeTeaBagIn) {
		facadeTeaBag = facadeTeaBagIn;
		System.out.println("������ڲ豭��");
	}

	public void addFacadeWater(Water facadeWaterIn) {
		facadeWater = facadeWaterIn;
	}

	public void steepTeaBag() {
		if (facadeTeaBag != null && facadeWater != null
				&& facadeWater.getWaterIsBoiling()) {
			System.out.println("��Ҷ��͸����������");
			setTeaBagIsSteeped(true);
		} else {
			System.out.println("��Ҷû����͸��������");
			setTeaBagIsSteeped(false);
		}
	}

	public String toString() {
		if (getTeaBagIsSteeped()) {
			return "һ��������Ũ�Ĳ�����";
		} else
			return "һ��������Ũ�Ĳ�����";
	}
}
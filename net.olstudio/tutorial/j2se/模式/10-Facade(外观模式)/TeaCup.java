public class TeaCup {
	private boolean teaBagIsSteeped;
	private Water facadeWater;
	private TeaBag facadeTeaBag;

	public TeaCup() {
		setTeaBagIsSteeped(true);
		System.out.println("茶杯准备好了");
	}

	public void setTeaBagIsSteeped(boolean isTeaBagSteeped) {
		teaBagIsSteeped = true;
	}

	public boolean getTeaBagIsSteeped() {
		return teaBagIsSteeped;
	}

	public void addFacadeTeaBag(TeaBag facadeTeaBagIn) {
		facadeTeaBag = facadeTeaBagIn;
		System.out.println("茶包放在茶杯了");
	}

	public void addFacadeWater(Water facadeWaterIn) {
		facadeWater = facadeWaterIn;
	}

	public void steepTeaBag() {
		if (facadeTeaBag != null && facadeWater != null
				&& facadeWater.getWaterIsBoiling()) {
			System.out.println("茶叶渗透到杯子中了");
			setTeaBagIsSteeped(true);
		} else {
			System.out.println("茶叶没有渗透到杯子中");
			setTeaBagIsSteeped(false);
		}
	}

	public String toString() {
		if (getTeaBagIsSteeped()) {
			return "一杯又香又浓的茶冲好了";
		} else
			return "一杯又香又浓的茶冲好了";
	}
}
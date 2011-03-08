public class FacadeCuppaMaker {
	private boolean TeaBagIsSteeped;

	public FacadeCuppaMaker() {
		System.out.println("FacadeCuppaMaker ׼���ó����");
	}

	public TeaCup makeACuppa() {
		TeaCup cup = new TeaCup();
		TeaBag teaBag = new TeaBag();
		Water water = new Water();
		cup.addFacadeTeaBag(teaBag);
		water.boilFacadeWater();
		cup.addFacadeWater(water);
		cup.steepTeaBag();
		return cup;
	}
}
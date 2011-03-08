public class Client {
	public static void main(String[] args) {
		FacadeCuppaMaker cuppaMaker = new FacadeCuppaMaker();
		TeaCup teaCup = cuppaMaker.makeACuppa();
		System.out.println(teaCup);
	}
}
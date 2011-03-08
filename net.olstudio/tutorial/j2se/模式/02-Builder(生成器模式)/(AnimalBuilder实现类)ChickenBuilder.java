
public class ChickenBuilder implements AnimalBuilder {
	private Animal chicken = null;
	
	// 当调用方法的时候 才去创建对象的属性
	public ChickenBuilder() {
		chicken = new Animal();
	}

	public void buildName() {
		chicken.setName("chicken");
	}

	public void buildLegs() {
		chicken.setLegs(2);
	}

	public void buildMeat() {
		chicken.setMeat("chicken");
	}

	public Animal getAnimal() {
		// 分别调用各个方法来创建各个属性
		buildName();
		buildLegs();
		buildMeat();
		return chicken;
	}
}
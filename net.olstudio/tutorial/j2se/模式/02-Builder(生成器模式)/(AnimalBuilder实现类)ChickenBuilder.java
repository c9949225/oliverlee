
public class ChickenBuilder implements AnimalBuilder {
	private Animal chicken = null;
	
	// �����÷�����ʱ�� ��ȥ�������������
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
		// �ֱ���ø���������������������
		buildName();
		buildLegs();
		buildMeat();
		return chicken;
	}
}
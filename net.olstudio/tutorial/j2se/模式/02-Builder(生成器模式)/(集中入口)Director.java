/*
 * Ҫ��ר�ŵ�Director,�������������ͳһ���
 */
public class Director {
	
	public Animal getAnimal(String aName) {
		
		if (aName.equals("chicken")) {
			return new ChickenBuilder().getAnimal();
		} else if (aName.equals("pig")) {
			return new PigBuilder().getAnimal();
		} else
			return null;
	}
}
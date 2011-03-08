/*
 * 要有专门的Director,当作创建动物的统一入口
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
/*
 * ���ۣ�
 * 
 * ������ģʽʹ�ÿͻ����Ը���"��ϸ�Ŀ���һ������Ĺ���"��
 * 
 * ÿ�������builder�����˴�����װ��һ���ض���Ʒ�����д��룬
 * 
 * ��Щ����ֻ��дһ�Σ�����Ա���ͬ��Directorʹ�ã��Ӷ�����װ�����ͬ�Ķ���
 * 
 * �ò�ͬ��ʽʵ��ͬ���Ľӿ������ɲ�ͬ�Ķ���
 */
public class Client {
	public static void description(Animal animal) {
		String desc = "This animal is called " + animal.getName();
		desc += "\n Its meat is " + animal.getMeat();
		desc += "\n And it has " + animal.getLegs() + " legs.";
		System.out.println(desc);
	}

	public static void main(String[] args) {
		String aName = "chicken";
		Director d = new Director();
		Animal animal = d.getAnimal(aName);
		description(animal);
		aName = "pig";
		animal = d.getAnimal(aName);
		description(animal);
	}
}
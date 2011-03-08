package net.oliver.olframework.util.reflection.mirror.beans;

public class Level1Bean {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	private Level2Bean level2Bean;

	public Level2Bean getLevel2Bean() {
		return level2Bean;
	}

	public void setLevel2Bean(Level2Bean level2Bean) {
		this.level2Bean = level2Bean;
	}
	
}

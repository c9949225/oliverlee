package net.oliver.olframework.util.reflection.mirror.beans;

public class Level2Bean {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Level3Bean level3Bean;

	public Level3Bean getLevel2Bean() {
		return level3Bean;
	}

	public void setLevel3Bean(Level3Bean level3Bean) {
		this.level3Bean = level3Bean;
	}
}

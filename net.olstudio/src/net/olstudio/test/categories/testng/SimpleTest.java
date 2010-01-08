package net.olstudio.test.categories.testng;

import org.testng.annotations.*;

// Oliver 于 2008-7-15 下午02:45:22 作出注释:
// 使用TestNg不需要继承任何类或实现任何方法
// 方法的名称可以起任何名字
// 测试方法可以划分到'组'
public class SimpleTest {

	// Oliver 于 2008-7-15 下午02:43:24 作出注释:
	// 当测试类被创建后,但任何测试方法执行之前时运行{setUp()} 
	@BeforeClass
	public void setUp() {
		// code that will be invoked when this test is instantiated
	}
	
	@Test(groups = { "fast" })
	public void aFastTest() {
		System.out.println("Fast test");
	}

	@Test(groups = { "slow" })
	public void aSlowTest() {
		System.out.println("Slow test");
	}

}

// 运行该类的ant脚本
/*
<project default="test">

 <path id="cp">
   <pathelement location="lib/testng-testng-4.4-jdk15.jar"/>
   <pathelement location="build"/>
 </path>

 <taskdef name="testng" classpathref="cp"
          classname="org.testng.TestNGAntTask" />

 <target name="test">
   <!-- 这里表明只允许fast组的测试方法 -->
   <testng classpathref="cp" groups="fast">
     <classfileset dir="build" includes="example1/*.class"/>
   </testng>
 </target>

</project>
*/
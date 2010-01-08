package net.olstudio.test.categories.testng;

import org.testng.annotations.*;

// Oliver �� 2008-7-15 ����02:45:22 ����ע��:
// ʹ��TestNg����Ҫ�̳��κ����ʵ���κη���
// ���������ƿ������κ�����
// ���Է������Ի��ֵ�'��'
public class SimpleTest {

	// Oliver �� 2008-7-15 ����02:43:24 ����ע��:
	// �������౻������,���κβ��Է���ִ��֮ǰʱ����{setUp()} 
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

// ���и����ant�ű�
/*
<project default="test">

 <path id="cp">
   <pathelement location="lib/testng-testng-4.4-jdk15.jar"/>
   <pathelement location="build"/>
 </path>

 <taskdef name="testng" classpathref="cp"
          classname="org.testng.TestNGAntTask" />

 <target name="test">
   <!-- �������ֻ����fast��Ĳ��Է��� -->
   <testng classpathref="cp" groups="fast">
     <classfileset dir="build" includes="example1/*.class"/>
   </testng>
 </target>

</project>
*/
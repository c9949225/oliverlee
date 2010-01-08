package net.oliver.olframework.util.path;

public class PathTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String linuxOsPath = "/home/jboss/jboss-4.0.5.GA/server/default/deploy/cjteller.war/web.xml";
		
		IPath path = new Path(linuxOsPath);
		
		System.out.println(path.getFileExtension()); /* xml */
		System.out.println(path.getDevice()); /* null */
		System.out.println(path.isValidPath("/home/jboss/")); /* true */
		System.out.println(path.lastSegment()); /* web.xml */
		System.out.println(path.makeUNC(true)); /* //home/jboss/jboss-4.0.5.GA/server/default/deploy/cjteller.war/web.xml */
		IPath testpath = new Path("/home/jboss/jboss-4.0.5.GA/xyz");
		System.out.println(path.matchingFirstSegments(testpath)); /* 3 */
		System.out.println(path.makeRelative()); /* home/jboss/jboss-4.0.5.GA/server/default/deploy/cjteller.war/web.xml */
		System.out.println(path.makeAbsolute()); /* /home/jboss/jboss-4.0.5.GA/server/default/deploy/cjteller.war/web.xml */
		
		System.out.println(path.toOSString()); /* \home\jboss\jboss-4.0.5.GA\server\default\deploy\cjteller.war\web.xml */
		System.out.println(path.toPortableString()); /* /home/jboss/jboss-4.0.5.GA/server/default/deploy/cjteller.war/web.xml */
		// ��β���Ƴ�2��
		System.out.println(path.removeLastSegments(2)); /* /home/jboss/jboss-4.0.5.GA/server/default/deploy */
		// �õ������ļ���
		System.out.println(path.removeFirstSegments(path.segmentCount() - 1)); /* web.xml */
		System.out.println(path.removeFirstSegments(path.segmentCount() - 1).lastSegment());  /* web.xml */
	}

}

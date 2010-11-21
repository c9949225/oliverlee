package net.oliver.olframework.util.compress.jzlib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


/**
 * ѹ����ѹ������
 * 
 * @author rockyzheng
 * 
 */
public class JzlibUtil {

	// �������ݵ���󳤶�
	private static final int MAXLENGTH = 102400;
	private static final int BUFFERSIZE = 1024;

	/**
	 * ѹ������
	 * 
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static byte[] Compress(byte[] object) throws IOException {

		byte[] data = null;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ZOutputStream zOut = new ZOutputStream(out,
					JZlib.Z_BEST_COMPRESSION);
			DataOutputStream objOut = new DataOutputStream(zOut);
			objOut.write(object);
			objOut.flush();
			zOut.close();
			data = out.toByteArray();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return data;
	}

	/**
	 * ��ѹ��ѹ��������
	 * 
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static byte[] UnCompress(byte[] object) throws IOException {

		byte[] data = new byte[MAXLENGTH];
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(object);
			ZInputStream zIn = new ZInputStream(in);
			DataInputStream objIn = new DataInputStream(zIn);

			int len = 0;
			int count = 0;
			while ((count = objIn.read(data, len, len + BUFFERSIZE)) != -1) {
				len = len + count;
			}

			byte[] trueData = new byte[len];
			System.arraycopy(data, 0, trueData, 0, len);

			objIn.close();
			zIn.close();
			in.close();

			return trueData;

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void main(String[] args) {

		try {
			String content = "�����Ŀ�������ķ���֤�ݷ���˼������ʮfjskfjsdkfjsdkfj���ɾ��ܵ���������Ŀ�������Ŀ�������ķ���˵�Ŀ�������Ŀ��������Ͽ��羰��˼�����������Ǿ������ڵĿ�������Ŀ�������Ŀ������迧�Ⱦ��Ǵ򿪸���������Ŀ����Ϳ�ʼ������Ϳ��ٷ������ָ����鿪������Ŀ������迧�Ȼ��ϵķ�˾�����͵Ŀ�������Ŀ������̵Ľ𷢿Ƽ�ʱ�������ڵĿ�������ĸ���ʡ�ط��ɷ��͵�������ʼ�ķ���������ٵĿ�������Ŀ��������";
			System.out.println(content);
			byte[] origin = content.getBytes();
			System.out.println("orign length is : " + origin.length);
			byte[] compressed = Compress(origin);
			System.out.println("compressed length is : " + compressed.length);

			byte[] unCompressed = UnCompress(compressed);
			System.out.println("unCompressed length is : "
					+ unCompressed.length);

			String newContent = new String(unCompressed);
			System.out.println(newContent);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
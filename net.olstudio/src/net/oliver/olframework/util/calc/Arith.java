package net.oliver.olframework.util.calc;

import java.math.BigDecimal;

public class Arith {

	private static final int DEF_DIV_SCALE = 10;

	private Arith() {

	}

	/**   
	 *   �ṩ���_�ļӷ��\��   
	 *   @param   args   
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**   
	 *   �ṩ�˾��_�Ĝp���\��   
	 *     
	 *   @param   args   
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**   
	 *   �ṩ�˾��_�ĳ˷��\��   
	 *   @param   args   
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**   
	 *   �ṩ��(����)���_�ĳ����\�㣬���l������������r�r�����_��   
	 *   С���c���ᣱ10λ   
	 *   @param   args   
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**   
	 *   �ṩ��(����)���_�ĳ����\�㣬���l������������r�r����scale����ָ��   
	 *   ���ȣ�����Ĕ����Ē�����   
	 *   @param   args   
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The   scale   must   be   a   positive   integer   or   zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**   
	 *   �ṩ�˾��_��С��λ�Ē�����̎��   
	 *   @param   args   
	 */

	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The   scale   must   be   a   positive   integer   or   zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}

	public static void main(String[] args) {
		System.out.println(add(1.2321231, 3.7865765));
		System.out.println(sub(6.4523423, 1.2321231));
		System.out.println(mul(6.4523423, 3.7865765));
		System.out.println(div(6.4523423, 3.7865765, 5));
		System.out.println(round(3.7865765, 5));
	}
}

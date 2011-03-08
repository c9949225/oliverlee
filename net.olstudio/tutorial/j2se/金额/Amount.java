/*
 *  
 *  java�н��н��ļ��㾭����������ʧ����,������������ԭ��Ӧ����cpu�Ը������ļ��㷽ʽ�й�,������Ľ���:
��ԭ�����������κ�һ�����Զ��ڸ������ļ��㶼�ǲ���ȷ�ġ�
��Ϊ���ڵ�Computer���ǻ��ڶ����������洢����ġ�
�������8��3ʱ��Computer��ת��Ϊ�����Ƶļӷ�1000��11��1011��Ȼ����ת��Ϊʮ������Ϊ11��
�����㷨����������˵�ǲ���������ģ�������������㷶Χ����
�����ڸ�����������ʱ�ͻ��������Ϊ�еĸ�����ת����Ϊ������ʱ��һ������ѭ��С����
����ʮ���Ƶ�0.4��ת����Ϊ������Ϊ0.0110011001100110....����������0.4+0.3ʱ�Ͳ���׼ȷ�������0.7��
���Ǿ���һЩ���봦����ܵó���ȷ�������������������������Ľϴ�ʱ��
��ʹ����һЩ���봦��Ҳ���ܵõ���ȷ�Ľ���ˡ�
��Ȼ����,��ô��java��Ϊ�˱��־�����Ҫ��������?
һ�仰:��BigDecimal��������;BigDecimal�Ĺ��캯�������漸��:
BigDecimal(BigInteger val)
          Translates a BigInteger into a BigDecimal.
BigDecimal(BigInteger unscaledVal, int scale)
          Translates a BigInteger unscaled value and an int scale into a BigDecimal.
BigDecimal(double val)
          Translates a double into a BigDecimal.
BigDecimal(String val)
          Translates the String representation of a BigDecimal into a BigDecimal.
����jdk�����ĵ�˵��,�������double���͹��캯���Ļ��п��ܶ�ʧ����,�Ƽ�����String���͵Ĺ��캯��.
Note: the results of this constructor can be somewhat unpredictable.
One might assume that new BigDecimal(.1) is exactly equal to .1, but it is actually equal to
.1000000000000000055511151231257827021181583404541015625. This is so because .1 cannot be represented
exactly as a double (or, for that matter, as a binary fraction of any finite length).
Thus, the long value that is being passed in to the constructor is not exactly equal to .1,
appearances notwithstanding.

���õĹ�����д��һ��������Amount,�Խ�����ճ������������Ľ���������:
 */
import java.io.Serializable;
import java.math.BigDecimal;

/** 
 * �������. ֧��������������㣬����this����.
 */
public class Amount implements Serializable {

	private BigDecimal value;

	/**
	 * �ṩĬ�Ͼ���10
	 */
	private int scale = 10;

	/**
	 * double���͹��캯��
	 * 
	 * @param value
	 */
	public Amount(double value) {
		this.value = new BigDecimal(Double.toString(value));
	}

	/**
	 * String���͹��캯��
	 * 
	 * @param value
	 */
	public Amount(String value) {
		this.value = new BigDecimal(value);
	}

	/**
	 * ȡ��BigDecimal��ֵ
	 * 
	 * @return
	 */
	public BigDecimal getValue() {
		return this.value;
	}

	/**
	 * ����double���͵���ֵ���
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double add(double v1, double v2) {
		Amount a1 = new Amount(v1);
		Amount a2 = new Amount(v2);
		return add(a1, a2);
	}

	/**
	 * �������
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double div(double v1, double v2) {
		Amount a1 = new Amount(v1);
		Amount a2 = new Amount(v2);
		return this.divide(a1, a2);
	}

	/**
	 * ���
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double sub(double v1, double v2) {
		Amount a1 = new Amount(v1);
		Amount a2 = new Amount(v2);
		return this.subtract(a1, a2);
	}

	/**
	 * ���
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double mul(double v1, double v2) {
		Amount a1 = new Amount(v1);
		Amount a2 = new Amount(v2);
		return this.multiply(a1, a2);
	}

	/**
	 * ����Amount���͵����ݽ������
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double add(Amount v1, Amount v2) {
		return v1.getValue().add(v2.getValue()).doubleValue();
	}

	/**
	 * ����Amount���ͱ������
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double divide(Amount v1, Amount v2) {
		if (scale < 0) {
			throw new IllegalArgumentException("����ָ������,��ָ��һ��>=0�ľ���");
		}
		return v1.getValue().divide(v2.getValue(), scale,
				BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * �������
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double multiply(Amount v1, Amount v2) {
		return v1.getValue().multiply(v2.getValue()).doubleValue();
	}

	/**
	 * �������
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double subtract(Amount v1, Amount v2) {
		return v1.getValue().subtract(v2.getValue()).doubleValue();
	}

	/**
	 * ����value�ĸ�����ֵ
	 * 
	 * @return
	 */
	public double doubleValue() {
		return this.getValue().doubleValue();
	}

	/**
	 * ���þ���
	 * @param scale
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}
}
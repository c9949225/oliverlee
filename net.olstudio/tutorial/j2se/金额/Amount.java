/*
 *  
 *  java中进行金额的计算经常浮点数丢失精度,造成这种问题的原因应该与cpu对浮点数的计算方式有关,有下面的介绍:
从原理上来讲，任何一门语言对于浮点数的计算都是不精确的。
因为现在的Computer都是基于二进制数来存储计算的。
例如计算8＋3时，Computer会转换为二进制的加法1000＋11＝1011，然后再转换为十进制数为11。
这种算法对于整数来说是不会产生误差的（如果不超过计算范围）；
而对于浮点数计算有时就会产生误差。因为有的浮点数转换成为二进制时是一个无穷循环小数。
例如十进制的0.4，转换成为二进制为0.0110011001100110....，这样，在0.4+0.3时就不能准确的算出是0.7，
而是经过一些舍入处理才能得出正确结果，但经过多次运算误差产生的较大时，
即使经过一些舍入处理也不能得到精确的结果了。
既然这样,那么在java中为了保持精度需要怎样做呢?
一句话:用BigDecimal进行运算;BigDecimal的构造函数有下面几种:
BigDecimal(BigInteger val)
          Translates a BigInteger into a BigDecimal.
BigDecimal(BigInteger unscaledVal, int scale)
          Translates a BigInteger unscaled value and an int scale into a BigDecimal.
BigDecimal(double val)
          Translates a double into a BigDecimal.
BigDecimal(String val)
          Translates the String representation of a BigDecimal into a BigDecimal.
按照jdk帮助文档说明,如果采用double类型构造函数的话有可能丢失精度,推荐采用String类型的构造函数.
Note: the results of this constructor can be somewhat unpredictable.
One might assume that new BigDecimal(.1) is exactly equal to .1, but it is actually equal to
.1000000000000000055511151231257827021181583404541015625. This is so because .1 cannot be represented
exactly as a double (or, for that matter, as a binary fraction of any finite length).
Thus, the long value that is being passed in to the constructor is not exactly equal to .1,
appearances notwithstanding.

在用的过程中写了一个帮助类Amount,以解决在日常开发中遇到的金额计算问题:
 */
import java.io.Serializable;
import java.math.BigDecimal;

/** 
 * 金额类型. 支持自身的四则运算，并将this返回.
 */
public class Amount implements Serializable {

	private BigDecimal value;

	/**
	 * 提供默认精度10
	 */
	private int scale = 10;

	/**
	 * double类型构造函数
	 * 
	 * @param value
	 */
	public Amount(double value) {
		this.value = new BigDecimal(Double.toString(value));
	}

	/**
	 * String类型构造函数
	 * 
	 * @param value
	 */
	public Amount(String value) {
		this.value = new BigDecimal(value);
	}

	/**
	 * 取得BigDecimal的值
	 * 
	 * @return
	 */
	public BigDecimal getValue() {
		return this.value;
	}

	/**
	 * 两个double类型的数值相加
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
	 * 两数相除
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
	 * 相减
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
	 * 相乘
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
	 * 两个Amount类型的数据进行相加
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double add(Amount v1, Amount v2) {
		return v1.getValue().add(v2.getValue()).doubleValue();
	}

	/**
	 * 两个Amount类型变量相除
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double divide(Amount v1, Amount v2) {
		if (scale < 0) {
			throw new IllegalArgumentException("精度指定错误,请指定一个>=0的精度");
		}
		return v1.getValue().divide(v2.getValue(), scale,
				BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 两数相乘
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double multiply(Amount v1, Amount v2) {
		return v1.getValue().multiply(v2.getValue()).doubleValue();
	}

	/**
	 * 两数相减
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double subtract(Amount v1, Amount v2) {
		return v1.getValue().subtract(v2.getValue()).doubleValue();
	}

	/**
	 * 返回value的浮点数值
	 * 
	 * @return
	 */
	public double doubleValue() {
		return this.getValue().doubleValue();
	}

	/**
	 * 设置精度
	 * @param scale
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}
}
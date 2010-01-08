package net.oliver.olframework.util.filesystem.xml.castor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

//import org.exolab.castor.xml.MarshalException;
//import org.exolab.castor.xml.Marshaller;
//import org.exolab.castor.xml.ValidationException;

public class TestBean {
	private String m_carrier;
	private int m_number;
	private String m_departure;
	private String m_arrival;

	public TestBean() {
	}

	public void setCarrier(String carrier) {
		m_carrier = carrier;
	}

	public String getCarrier() {
		return m_carrier;
	}

	public void setNumber(int number) {
		m_number = number;
	}

	public int getNumber() {
		return m_number;
	}

	public void setDepartureTime(String time) {
		m_departure = time;
	}

	public String getDepartureTime() {
		return m_departure;
	}

	public void setArrivalTime(String time) {
		m_arrival = time;
	}

	public String getArrivalTime() {
		return m_arrival;
	}

	public static void main(String[] argv) {
		// build a test bean
		/*TestBean bean = new TestBean();
		// 直接对属性的设置就是一个子标签
		bean.setCarrier("AR");
		bean.setNumber(676);
		bean.setDepartureTime("6:23a");
		bean.setArrivalTime("8:42a");
		try {
			// write it out as XML (if not already present)
			File file = new File("E:/test.xml");
			if (!file.exists()) {
				Writer writer = new FileWriter(file);
				Marshaller.marshal(bean, writer);
			}

			// now restore the value and list what we get
//			Reader reader = new FileReader(file);
//			TestBean read = (TestBean) Unmarshaller.unmarshal(TestBean.class,
//					reader);
//
//			System.out.println("Restored flight " + read.getCarrier()
//					+ read.getNumber() + " departing at "
//					+ read.getDepartureTime() + " and arriving at "
//					+ read.getArrivalTime());

		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		} catch (MarshalException ex) {
			ex.printStackTrace(System.err);
		} catch (ValidationException ex) {
			ex.printStackTrace(System.err);
		}*/
	}
}

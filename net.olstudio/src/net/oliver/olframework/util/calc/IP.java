package net.oliver.olframework.util.calc;

public class IP {
	public static void main(String[] args) {
		String current = "192.168.111.11";
		String start = "192.168.0.0";
		String end = "192.168.254.254";

		IP ip = new IP();

		if (ip.betweenIP(start, end, current)) {
			System.out.println(current + "在" + start + "-" + end + "之间");
		} else {
			System.out.println(current + "不在" + start + "-" + end + "之间");
		}
	}

	public boolean betweenIP(String start, String end, String current) {
		boolean result = false;

		start = start.replaceAll("(^|\\.)(\\d)(\\.|$)", "$100$2$3");
		start = start.replaceAll("(^|\\.)(\\d)(\\.|$)", "$100$2$3");
		start = start.replaceAll("(^|\\.)(\\d{2})(\\.|$)", "$10$2$3");
		start = start.replaceAll("(^|\\.)(\\d{2})(\\.|$)", "$10$2$3");

		end = end.replaceAll("(^|\\.)(\\d)(\\.|$)", "$100$2$3");
		end = end.replaceAll("(^|\\.)(\\d)(\\.|$)", "$100$2$3");
		end = end.replaceAll("(^|\\.)(\\d{2})(\\.|$)", "$10$2$3");
		end = end.replaceAll("(^|\\.)(\\d{2})(\\.|$)", "$10$2$3");

		current = current.replaceAll("(^|\\.)(\\d)(\\.|$)", "$100$2$3");
		current = current.replaceAll("(^|\\.)(\\d)(\\.|$)", "$100$2$3");
		current = current.replaceAll("(^|\\.)(\\d{2})(\\.|$)", "$10$2$3");
		current = current.replaceAll("(^|\\.)(\\d{2})(\\.|$)", "$10$2$3");

		if ((current.compareTo(start) >= 0) && (current.compareTo(end) <= 0)) {
			result = true;
		}
		return result;
	}
}
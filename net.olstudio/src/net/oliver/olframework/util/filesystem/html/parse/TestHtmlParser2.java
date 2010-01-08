package net.oliver.olframework.util.filesystem.html.parse;

import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

public class TestHtmlParser2 {
	public static void main(String[] args) throws Exception {
		String eu = null;
		String tmp = null;
		float euBid = 0f; // 欧元兑美元的卖价
		float euOffer = 0f; // 欧元兑美元的买价

		Parser parser = new Parser("http://secure.efxnow.com/Rates2/CHN/Rates_CHN.aspx");

		TagNameFilter filter = new TagNameFilter("TD");
		NodeList nl = parser.extractAllNodesThatMatch(filter);

		for (int i = 1; i < nl.size(); i++) {
			tmp = nl.elementAt(i).toPlainTextString();
			if (tmp.indexOf("EUR/USD") > 0) {
				eu = nl.elementAt(i + 1).toPlainTextString();
			}
		}
		eu = eu.replaceAll(" ", "");
		eu = eu.replaceAll("&nbsp;", "");

		euBid = Float.parseFloat(eu.substring(0, 6));
		euOffer = Float.parseFloat(eu.substring(0, 4) + eu.substring(7, 9));
		System.out.println("EUR/USD Bid price is " + euBid);
		System.out.println("EUR/USD Offer price is " + euOffer);

	}
}
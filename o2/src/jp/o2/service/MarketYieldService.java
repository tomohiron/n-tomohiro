package jp.o2.service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class MarketYieldService {

	private static final Logger logger = Logger
			.getLogger(MarketYieldService.class.getName());

	public void getNSpline(List<Map<String, Object>> yeildArray) {
		logger.info(yeildArray.toString());

		for (Map<String, Object> yield : yeildArray) {
			String id = (String) yield.get("id");
			double rate = ((Double) yield.get("rate")).doubleValue();
			logger.info("id=" + id);
			logger.info("rate=" + rate);
		}

	}
}

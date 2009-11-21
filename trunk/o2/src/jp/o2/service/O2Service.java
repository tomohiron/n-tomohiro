package jp.o2.service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import flex.messaging.FlexContext;

public class O2Service {

    private static final Logger logger = Logger.getLogger(O2Service.class.getName());

    public void setGreeting(String value) {
        logger.severe("setGreeting");

        HttpSession session = FlexContext.getHttpRequest().getSession();
        session.setAttribute("greeting", value);
    }

    public String getMessage(String target) {
        logger.warning("getMessage");

        HttpSession session = FlexContext.getHttpRequest().getSession();
        String greeting = (String) session.getAttribute("greeting");

        String message = greeting + ", " + target + ".";
        return message;
    }

    public Map<String, Object> getSwapMessage(Map<String, Object> source) {
        logger.info("getSwapMessage");
        Map<String, Object> dest = new HashMap<String, Object>();
        dest.put("text1", source.get("text2"));
        dest.put("text2", source.get("text1"));
        return dest;
    }
}

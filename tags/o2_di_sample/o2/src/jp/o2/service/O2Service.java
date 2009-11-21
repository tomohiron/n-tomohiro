package jp.o2.service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class O2Service {

    private SessionData sessionData;

    private static final Logger logger = Logger.getLogger(O2Service.class.getName());

    public void setGreeting(String value) {
        logger.info("setGreeting");

        sessionData.setGreeting(value);
    }

    public String getMessage(String target) {
        logger.info("getMessage");

        String greeting = sessionData.getGreeting();

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

    public void setSessionData(SessionData sessionData) {
        this.sessionData = sessionData;
    }

}

package jp.o2.service;

import javax.servlet.http.HttpSession;

import flex.messaging.FlexContext;

public class SessionDataImplFlex implements SessionData {

    private static final String ATTRIBUTE_GREETING = "greeting";

    @Override
    public void setGreeting(String value) {
        getSession().setAttribute(ATTRIBUTE_GREETING, value);
    }

    @Override
    public String getGreeting() {
        return (String) getSession().getAttribute(ATTRIBUTE_GREETING);
    }

    private HttpSession getSession() {
        return FlexContext.getHttpRequest().getSession();
    }

}

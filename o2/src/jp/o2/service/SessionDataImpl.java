package jp.o2.service;

public class SessionDataImpl implements SessionData {

    private String greeting;

    @Override
    public void setGreeting(String value) {
        greeting = value;
    }

    @Override
    public String getGreeting() {
        return greeting;
    }

}

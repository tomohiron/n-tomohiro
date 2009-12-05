package jp.gacha;

import java.util.List;

import javax.jdo.PersistenceManager;

public class AdminService {

    public static void updateMessage(String value) {
        Message message = new Message(value);
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(message);
        } finally {
            pm.close();
        }
    }

    public static String getMessage() {
        String query = "select from " + Message.class.getName() + " order by createdAt desc";
        PersistenceManager pm = PMF.get().getPersistenceManager();
        List<Message> messages = (List<Message>) pm.newQuery(query).execute();

        if (messages.isEmpty()) {
            return "";
        }

        return messages.get(0).getValue();
    }

}

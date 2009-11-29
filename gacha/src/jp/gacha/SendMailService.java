package jp.gacha;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class SendMailService extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(SendMailService.class
			.getName());

	public static void sendMail(Map<String, String> content)
			throws IOException, AddressException, MessagingException {

		ResourceBundle bundle = ResourceBundle.getBundle("gacha");
		String from = bundle.getString("mail.from");
		String to1 = bundle.getString("mail.to.1");
		String to2 = bundle.getString("mail.to.2");
		String subject = bundle.getString("mail.subject") + "-"
				+ content.get("soushin");

		String maxString = bundle.getString("group.max");
		int max = Integer.parseInt(maxString);

		StringBuffer msgBody = new StringBuffer();
		msgBody.append(content.get("year") + "-" + content.get("month"));
		msgBody.append("-" + content.get("date") + "\n");
		msgBody.append("送信者:" + content.get("soushin") + "\n");
		msgBody.append("現場名:" + content.get("genba") + "\n");

		for (int count = 1; count <= max; count++) {
			String groupName = content.get("group" + count);
			if ("".equals(groupName)) {
				continue;
			}
			msgBody.append(groupName + "x");
			msgBody.append(content.get("amount" + count) + "名\n");
		}

		msgBody.append("合計:" + content.get("total") + "名\n");
		msgBody.append("連絡事項:" + content.get("memo") + "\n");

		Session session = Session.getDefaultInstance(new Properties(), null);
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));
		msg.addRecipient(Message.RecipientType.TO,
				new InternetAddress(to1, to1));
		msg.addRecipient(Message.RecipientType.TO,
				new InternetAddress(to2, to2));
		msg.setSubject(subject, "ISO-2022-JP");
		msg.setText(msgBody.toString());

		Transport.send(msg);

		LOGGER.info(msgBody.toString());
	}
}

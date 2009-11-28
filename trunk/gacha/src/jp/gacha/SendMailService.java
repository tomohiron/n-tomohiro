package jp.gacha;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

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

	public static void sendMail(Map<String, String> content)
			throws IOException, AddressException, MessagingException {

		String from = "gachapin.noreply@gmail.com";
		String to = "tomohiron@softbank.ne.jp";
		// "08012704369@docomo.ne.jp"
		String subject = "TK";

		StringBuffer msgBody = new StringBuffer();
		msgBody.append(content.get("year") + "-" + content.get("month"));
		msgBody.append("-" + content.get("date") + "\n");
		msgBody.append("現場名：" + content.get("genba") + "\n");
		msgBody.append(content.get("group01") + "×");
		msgBody.append(content.get("amount01") + "名\n");
		msgBody.append(content.get("group02") + "×");
		msgBody.append(content.get("amount02") + "名\n");
		msgBody.append("連絡事項：" + content.get("memo") + "\n");

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to, to));
		msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(from,
				from));
		msg.setSubject(subject);
		msg.setText(msgBody.toString());

		Transport.send(msg);
	}
}

package jp.gacha;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GachaServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(GachaServlet.class
			.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String name = (String) req.getParameter("name");
		String age = (String) req.getParameter("age");

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		StringBuffer msgBody = new StringBuffer();
		msgBody.append("name : " + name + "\n");
		msgBody.append("age : " + age + "\n");

		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("gachapin.noreply@gmail.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"08012704369@docomo.ne.jp", "MURATA Naoya"));
			// msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
			// "tomohiron@softbank.ne.jp", "naoya"));
			msg.setSubject("from gacha-pin");
			msg.setText(msgBody.toString());
			Transport.send(msg);

		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			LOGGER.severe(e.getStackTrace().toString());
			resp.getWriter().println("<html><body>エラーが発生しました。</body></html>");
			return;
		}

		resp.getWriter().println("<html><body>メールを送信しました。</body></html>");
	}
}

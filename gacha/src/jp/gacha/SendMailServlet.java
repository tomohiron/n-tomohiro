package jp.gacha;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SendMailServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(SendMailServlet.class
			.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Map<String, String> content = new HashMap<String, String>();

		Enumeration<String> e = req.getParameterNames();
		while (e.hasMoreElements()) {
			String name = e.nextElement();
			String parameter = req.getParameter(name);
			content.put(name, parameter);
		}

		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");

		try {
			SendMailService.sendMail(content);
		} catch (Exception ex) {
			LOGGER.severe(ex.getMessage());
			resp.getWriter().println(
					"<html><body>エラーが発生し、メール送信に失敗しました。</body></html>");
			return;
		}

		LOGGER.info(content.toString());
		resp.getWriter().println("<html><body>メールを送信しました。</body></html>");
	}
}

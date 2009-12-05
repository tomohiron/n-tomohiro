package jp.gacha;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UpdateServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UpdateServlet.class.getName());

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String value = req.getParameter("message");
        AdminService.updateMessage(value);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println("<html><body>管理情報を更新しました。</body></html>");
    }
}

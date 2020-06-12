package servlet;

import dao.ArticleDao;
import entity.Article;
import org.thymeleaf.context.Context;
import utils.ThUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @author a2008q
 * @since 2020/6/12 8:21
 */
@WebServlet(name = "SendServlet", urlPatterns = "/send")
public class SendServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("GET".equals(req.getMethod())) {
            Context context = new Context();
            ThUtil.print("send", context, resp);
        } else {
            req.setCharacterEncoding("utf-8");
            String title = req.getParameter("title");
            String abs = req.getParameter("abs");
            String content = req.getParameter("content");
            String imgName = req.getParameter("imgName");
            String putTop = req.getParameter("putTop");
            int isTop = putTop == null ? 0 : 1;
            Article a = new Article(title, abs, isTop, System.currentTimeMillis(), System.currentTimeMillis(), imgName, content);
            ArticleDao dao = new ArticleDao();
            dao.insert(a);
            resp.sendRedirect("/list");
        }
    }
}

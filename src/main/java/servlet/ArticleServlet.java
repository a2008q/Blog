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
 * @since 2020/6/11 16:16
 */
@WebServlet(name = "ArticleServlet", urlPatterns = "/article")
public class ArticleServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String oId = req.getParameter("oId");
        ArticleDao dao = new ArticleDao();
        Article a =dao.findById(oId);
        Context context = new Context();
        context.setVariable("a",a);
        ThUtil.print("article",context,resp);

    }
}

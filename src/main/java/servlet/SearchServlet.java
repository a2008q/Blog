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
import java.util.List;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @author a2008q
 * @since 2020/6/11 15:50
 */
@WebServlet(name = "searchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String keyword = req.getParameter("keyword");
        ArticleDao dao = new ArticleDao();
        List<Article> list = dao.findByKeyword(keyword);
        Context context = new Context();
        context.setVariable("lists", list);
        ThUtil.print("list", context, resp);
    }
}

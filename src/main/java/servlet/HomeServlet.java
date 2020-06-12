package servlet;

import dao.ArticleDao;
import dao.LinkDao;
import dao.TagDao;
import entity.Article;
import entity.Link;
import entity.Tag;
import entity.User;
import org.thymeleaf.context.Context;
import utils.ThUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @author a2008q
 * @since 2020/6/10 9:07
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("home");
            return;
        }
        Context context = new Context();
        context.setVariable("userName", user.getUserName());
        ArticleDao dao = new ArticleDao();
        List<Article> list = dao.getHomeList();
        context.setVariable("first", list.get(0));
        List<Article> second = list.subList(1, 4);
        context.setVariable("second", second);
        List<Article> third = list.subList(4, list.size());
        context.setVariable("thirds", third);
        List<Article> newList = dao.getNewList();
        context.setVariable("newList", newList);
        List<Article> commentList = dao.getCommentList();
        context.setVariable("commentList", commentList);
        List<Article> viewList = dao.getViewList();
        context.setVariable("viewList", viewList);
        TagDao tagDao = new TagDao();
        List<Tag> tags = tagDao.getList();
        context.setVariable("tags", tags);
        List<Link> links = LinkDao.findAll();
        context.setVariable("links", links);
        ThUtil.print("index", context, resp);
    }
}

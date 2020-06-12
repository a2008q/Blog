package servlet;

import dao.ArticleDao;
import dao.LinkDao;
import dao.TagDao;
import entity.Article;
import entity.Link;
import entity.Tag;
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
 * @since 2020/6/11 10:40
 */
@WebServlet(urlPatterns = "/list")
public class ListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        ArticleDao dao = new ArticleDao();
        List<Article> lists = dao.findAll();
        context.setVariable("lists",lists);
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
        ThUtil.print("list", context, resp);
    }
}

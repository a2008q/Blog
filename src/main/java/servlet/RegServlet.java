package servlet;

import dao.UserDao;
import entity.User;
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
 * @since 2020/6/12 9:38
 */
@WebServlet(name = "RegServlet", urlPatterns = "/reg")
public class RegServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("GET".equals(req.getMethod())) {
            Context context = new Context();
            ThUtil.print("reg", context, resp);
        }else {
            String userName = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("pwd");
            UserDao dao = new UserDao();
            dao.save(new User(userName,password,email));
            resp.sendRedirect("login");
        }
    }
}

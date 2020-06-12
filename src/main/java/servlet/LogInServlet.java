package servlet;

import dao.UserDao;
import entity.User;
import org.omg.CORBA.IRObject;
import org.thymeleaf.context.Context;
import utils.ThUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @author a2008q
 * @since 2020/6/12 9:23
 */
@WebServlet(name = "LogInServlet", urlPatterns = "/login")
public class LogInServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("GET".equals(req.getMethod())) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null) {
                resp.sendRedirect("home");
                return;
            }
            Context context = new Context();
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userName")) {
                        context.setVariable("userName", cookie.getValue());
                    }
                    if (cookie.getName().equals("password")) {
                        context.setVariable("password", cookie.getValue());
                    }
                }
            }
            ThUtil.print("login", context, resp);
        } else {
            String userName = req.getParameter("name");
            String password = req.getParameter("pwd");
            String rem = req.getParameter("rem");
            UserDao dao = new UserDao();
            User user = dao.login(userName, password);
            if (user == null) {
                resp.sendRedirect("login");
            } else {
                if (rem != null) {
                    Cookie c1 = new Cookie("userName", userName);
                    Cookie c2 = new Cookie("password", password);
                    resp.addCookie(c1);
                    resp.addCookie(c2);

                }
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                resp.sendRedirect("home");
            }
        }
    }
}

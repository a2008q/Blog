package utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @author a2008q
 * @since 2020/6/9 16:30
 */
public class ThUtil {
    private static TemplateEngine te;

    static {
        te = new TemplateEngine();
        ClassLoaderTemplateResolver r = new ClassLoaderTemplateResolver();
        r.setCharacterEncoding("utf-8");
        r.setSuffix(".html");
        te.setTemplateResolver(r);
    }

    public static void print(String fileName, Context context, HttpServletResponse resp) throws IOException {
        String html = te.process(fileName, context);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(html);
        out.close();
    }
}

package dao;

import entity.Article;
import org.junit.Test;

import java.util.List;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @author a2008q
 * @since 2020/6/11 8:33
 */
public class testDao {
    @Test
    public void testArticleDao(){
        ArticleDao dao = new ArticleDao();
        Article a = new Article("测试","test",1,System.currentTimeMillis(),System.currentTimeMillis(),"img9.jpg","测试test");
        dao.insert(a);
    }
}

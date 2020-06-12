package dao;

import entity.Tag;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @author a2008q
 * @since 2020/6/10 15:31
 */
public class TagDao {

    public List<Tag> findByArticleId(int oId) {
        List<Tag> list = new ArrayList<Tag>();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "select title " +
                    "from tag t join tag_article ta " +
                    "on ta.tag_oId=t.oId " +
                    "where ta.article_oId=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, oId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String title = rs.getString(1);
                list.add(new Tag(title));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Tag> getList() {
        List<Tag> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "select oId,title,referenceCount from tag " +
                    "order by referenceCount desc limit 0,5;";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int oId = rs.getInt(1);
                String title = rs.getString(2);
                int referenceCount = rs.getInt(3);
                list.add(new Tag(oId, referenceCount, title));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

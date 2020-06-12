package dao;

import entity.Link;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @author a2008q
 * @since 2020/6/11 10:16
 */
public class LinkDao {

    public static List<Link> findAll() {
        List<Link> list = new ArrayList<Link>();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "select title,address from link";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String title = rs.getString(1);
                String address = rs.getString(2);
                list.add(new Link(title, address));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

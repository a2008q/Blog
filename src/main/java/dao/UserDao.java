package dao;

import entity.User;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @author a2008q
 * @since 2020/6/12 10:17
 */
public class UserDao {

    public void save(User user) {
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "insert into user(userName,password,email) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User login(String userName, String password) {
        User user = new User();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "select oId from user where userName=? and password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int oId = rs.getInt(1);
                user = new User(oId,userName,password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}

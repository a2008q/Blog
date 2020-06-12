package dao;

import entity.Article;
import entity.Tag;
import utils.DBUtils;
import utils.MdUtil;

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
 * @since 2020/6/10 10:34
 */
public class ArticleDao {
    public List<Article> getHomeList() {
        List<Article> list = new ArrayList<Article>();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "select a.oId,a.title,a.abstract,a.commentCount,a.viewCount," +
                    "a.putTop,a.created,a.updated,a.imgName,u.userName " +
                    "from article a join user u " +
                    "on u.oId=a.authorId " +
                    "order by a.putTop desc,a.created desc limit 0,8;";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int oId = rs.getInt(1);
                String title = rs.getString(2);
                String abs = rs.getString(3);
                int commentCount = rs.getInt(4);
                int viewCount = rs.getInt(5);
                int putTop = rs.getInt(6);
                long created = rs.getLong(7);
                long updated = rs.getLong(8);
                String imgName = rs.getString(9);
                String userName = rs.getString(10);
                TagDao dao = new TagDao();
                List<Tag> tags = dao.findByArticleId(oId);
                Article a = new Article(oId, title, abs, commentCount, viewCount, putTop, created, updated, imgName, userName);
                a.setTags(tags);
                list.add(a);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Article> getNewList() {
        List<Article> list = new ArrayList<Article>();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "select oId,title from article order by created " +
                    " desc limit 0,5;";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int oId = rs.getInt(1);
                String title = rs.getString(2);
                list.add(new Article(oId, title));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Article> getViewList() {
        List<Article> list = new ArrayList<Article>();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "select oId,title from article order by viewCount " +
                    " desc limit 0,5;";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int oId = rs.getInt(1);
                String title = rs.getString(2);
                list.add(new Article(oId, title));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Article> getCommentList() {
        List<Article> list = new ArrayList<Article>();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "select oId,title from article order by commentCount " +
                    " desc limit 0,5;";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int oId = rs.getInt(1);
                String title = rs.getString(2);
                list.add(new Article(oId, title));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Article> findAll() {
        List<Article> list = new ArrayList<Article>();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "select a.oId,a.title,a.abstract,a.commentCount,a.viewCount," +
                    "a.putTop,a.created,a.updated,a.imgName,u.userName " +
                    "from article a join user u " +
                    "on u.oId=a.authorId " +
                    "order by a.created desc limit 0,8;";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int oId = rs.getInt(1);
                String title = rs.getString(2);
                String abs = rs.getString(3);
                int commentCount = rs.getInt(4);
                int viewCount = rs.getInt(5);
                int putTop = rs.getInt(6);
                long created = rs.getLong(7);
                long updated = rs.getLong(8);
                String imgName = rs.getString(9);
                String userName = rs.getString(10);
                TagDao dao = new TagDao();
                List<Tag> tags = dao.findByArticleId(oId);
                Article a = new Article(oId, title, abs, commentCount, viewCount, putTop, created, updated, imgName, userName);
                a.setTags(tags);
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Article> findByKeyword(String keyword) {
        List<Article> list = new ArrayList<Article>();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "select a.oId,a.title,a.abstract,a.commentCount,a.viewCount," +
                    "a.putTop,a.created,a.updated,a.imgName,u.userName " +
                    "from article a join user u " +
                    "on u.oId=a.authorId where a.title like ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int oId = rs.getInt(1);
                String title = rs.getString(2);
                String abs = rs.getString(3);
                int commentCount = rs.getInt(4);
                int viewCount = rs.getInt(5);
                int putTop = rs.getInt(6);
                long created = rs.getLong(7);
                long updated = rs.getLong(8);
                String imgName = rs.getString(9);
                String userName = rs.getString(10);
                Article a = new Article(oId, title, abs, commentCount, viewCount, putTop, created, updated, imgName, userName);
                TagDao dao = new TagDao();
                List<Tag> tags = dao.findByArticleId(oId);
                a.setTags(tags);
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Article findById(String id) {
        Article a = new Article();
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "select a.oId,a.title,a.abstract,a.commentCount,a.viewCount," +
                    "a.putTop,a.created,a.updated,a.imgName,u.userName,a.content " +
                    "from article a join user u " +
                    "on u.oId=a.authorId where a.oId = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int oId = rs.getInt(1);
                String title = rs.getString(2);
                String abs = rs.getString(3);
                int commentCount = rs.getInt(4);
                int viewCount = rs.getInt(5);
                int putTop = rs.getInt(6);
                long created = rs.getLong(7);
                long updated = rs.getLong(8);
                String imgName = rs.getString(9);
                String userName = rs.getString(10);
                String content = rs.getString(11);
                a = new Article(oId, title, abs, commentCount, viewCount, putTop, created, updated, imgName, userName);
                TagDao dao = new TagDao();
                List<Tag> tags = dao.findByArticleId(oId);
                a.setTags(tags);
                content = MdUtil.markdownToHtmlExtensions(content);
                a.setContent(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public void insert(Article a) {
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "insert into article(title,abstract,content,commentCount,viewCount,putTop,imgName,created,updated,authorId) values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getAbs());
            ps.setString(3, a.getContent());
            ps.setInt(4, 0);
            ps.setInt(5, 0);
            ps.setInt(6, a.getPutTop());
            ps.setString(7, a.getImgName());
            ps.setLong(8, a.getCreated());
            ps.setLong(9, a.getUpdated());
            ps.setInt(10, 15);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

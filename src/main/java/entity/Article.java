package entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @author a2008q
 * @since 2020/6/10 9:29
 */
public class Article {
    private Integer oId;
    private String title;
    //摘要
    private String abs;
    private Integer commentCount;
    private Integer viewCount;
    private Integer putTop;
    //创建时间
    private Long created;
    //更新时间
    private Long updated;
    private String imgName;
    private String userName;
    private List<Tag> tags;
    private String createdStr;
    private String updatedStr;
    private String content;

    public Article(String title, String abs, Integer putTop, Long created, Long updated, String imgName, String content) {
        this.title = title;
        this.abs = abs;
        this.putTop = putTop;
        this.created = created;
        this.updated = updated;
        this.imgName = imgName;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdatedStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(this.updated);
        return sdf.format(date);
    }

    public void setUpdatedStr(String updatedStr) {
        this.updatedStr = updatedStr;
    }

    public Article() {

    }

    public String getCreatedStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(this.created);
        return sdf.format(date);
    }

    public void setCreatedStr(String createdStr) {
        this.createdStr = createdStr;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Article(Integer oId, String title) {
        this.oId = oId;
        this.title = title;
    }

    public Article(Integer oId, String title, String abs, Integer commentCount, Integer viewCount, Integer putTop, Long created, Long updated, String imgName, String userName) {
        this.oId = oId;
        this.title = title;
        this.abs = abs;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
        this.putTop = putTop;
        this.created = created;
        this.updated = updated;
        this.imgName = imgName;
        this.userName = userName;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getPutTop() {
        return putTop;
    }

    public void setPutTop(Integer putTop) {
        this.putTop = putTop;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

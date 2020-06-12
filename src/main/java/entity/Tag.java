package entity;

import java.sql.PreparedStatement;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @author a2008q
 * @since 2020/6/10 15:37
 */
public class Tag {
    private int oId;
    private int referenceCount;
    private String title;

    public Tag(String title) {
        this.title = title;
    }

    public Tag() {
    }

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public int getReferenceCount() {
        return referenceCount;
    }

    public void setReferenceCount(int referenceCount) {
        this.referenceCount = referenceCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Tag(int oId, int referenceCount, String title) {
        this.oId = oId;
        this.referenceCount = referenceCount;
        this.title = title;
    }
}

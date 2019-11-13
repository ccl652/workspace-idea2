package com.jk.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈Powers〉
 *
 * @author chenchunlan
 * @create 2019/11/4
 * @since 1.0.0
 */
public class Powers implements Serializable {

    private Integer id;

    private String text;

    private String url;

    private String types;

    private String power;

    private Integer pid;

    private boolean checked;

    private List<Powers> children;

    private String ptext;

    private String purl;

    public List<Powers> getChildren() {
        return children;
    }

    public void setChildren(List<Powers> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getPtext() {
        return ptext;
    }

    public void setPtext(String ptext) {
        this.ptext = ptext;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    @Override
    public String toString() {
        return "Powers{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", types='" + types + '\'' +
                ", power='" + power + '\'' +
                ", pid=" + pid +
                ", checked=" + checked +
                ", ptext='" + ptext + '\'' +
                ", purl='" + purl + '\'' +
                '}';
    }
}
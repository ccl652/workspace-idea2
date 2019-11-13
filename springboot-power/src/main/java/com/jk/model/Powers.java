package com.jk.model;


import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈Powers〉
 *
 * @author Administrator
 * @create 2019/11/4
 * @since 1.0.0
 */
public class Powers {

    private Integer id;

    private String text;

    private String url;

    private Integer pid;

    private boolean checked;

    private String ptext;

    private String purl;

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getPtext() {
        return ptext;
    }

    public void setPtext(String ptext) {
        this.ptext = ptext;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private List<Powers> children;

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
        this.text = text == null ? null : text.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

}
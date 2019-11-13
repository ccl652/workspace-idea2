package com.jk.model;

import java.io.Serializable;

/**
 * 〈角色表〉<br>
 * 〈Roles〉
 *
 * @author chenchunlan
 * @create 2019/11/4
 * @since 1.0.0
 */
public class Roles implements Serializable {

    private Integer rokid;

    private String rokname;

    private boolean checked;

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Integer getRokid() {
        return rokid;
    }

    public void setRokid(Integer rokid) {
        this.rokid = rokid;
    }

    public String getRokname() {
        return rokname;
    }

    public void setRokname(String rokname) {
        this.rokname = rokname == null ? null : rokname.trim();
    }

}
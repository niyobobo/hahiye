package rw.transax.hahiye.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

@Entity
public class InterestModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String uid;
    private String name;
    private String icon;

    @Ignore
    public InterestModel() {
    }

    public InterestModel(int id, String uid, String name, String icon) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.icon = icon;
    }

    @Ignore
    public InterestModel(String uid, String name, String icon) {
        this.uid = uid;
        this.name = name;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}

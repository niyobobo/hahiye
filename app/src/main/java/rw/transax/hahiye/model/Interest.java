package rw.transax.hahiye.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "interest")
public class Interest {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String uid;
    private String name;
    private String icon;
    private boolean isFollowed;
    private boolean isUploaded;

    @Ignore
    public Interest() {
    }

    @Ignore
    public Interest(String uid, String name, String icon) {
        this.uid = uid;
        this.name = name;
        this.icon = icon;
        this.isFollowed = false;
        this.isUploaded = false;
    }

    public Interest(int id, String uid, String name, String icon, boolean isFollowed, boolean isUploaded) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.icon = icon;
        this.isFollowed = isFollowed;
        this.isUploaded = isUploaded;
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

    public boolean isFollowed() {
        return isFollowed;
    }

    public void setFollowed(boolean followed) {
        isFollowed = followed;
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }
}

package rw.transax.hahiye.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "interest")
public class InterestModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String uid;
    private String name;
    private String icon;
    private int isFollowed;
    private int isUploaded;

    @Ignore
    public InterestModel() {
    }


    public InterestModel(int id, String uid, String name, String icon, int isFollowed, int isUploaded) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.icon = icon;
        this.isFollowed = isFollowed;
        this.isUploaded = isUploaded;
    }

    @Ignore
    public InterestModel(String uid, String name, String icon) {
        this.uid = uid;
        this.name = name;
        this.icon = icon;
        this.isFollowed = 0;
        this.isUploaded = 0;
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

    public int getIsFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(int isFollowed) {
        this.isFollowed = isFollowed;
    }

    public int getIsUploaded() {
        return isUploaded;
    }

    public void setIsUploaded(int isUploaded) {
        this.isUploaded = isUploaded;
    }
}

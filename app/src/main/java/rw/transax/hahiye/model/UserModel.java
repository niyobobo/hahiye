package rw.transax.hahiye.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "user")
public class UserModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String uid;
    private String name;
    private String username;
    private String password;
    private String email;
    private String profile_url;
    private boolean verified;
    private Date created_at;

    //private List<PlaceModel> follows;
    //private List<InterestModel> interested;
    @Ignore
    public UserModel() {
    }

    @Ignore
    public UserModel(String uid, String name, String username, String password, String email,
                     String profile_url, boolean verified, Date created_at) {
        this.uid = uid;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profile_url = profile_url;
        this.verified = verified;
        this.created_at = created_at;
        //this.follows = follows;
        //this.interested = interested;
    }

    public UserModel(int id, String uid, String name, String username, String password,
                     String email, String profile_url, boolean verified, Date created_at) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profile_url = profile_url;
        this.verified = verified;
        this.created_at = created_at;
        //this.follows = follows;
        //this.interested = interested;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

//    public List<PlaceModel> getFollows() {
//        return follows;
//    }
//
//    public void setFollows(List<PlaceModel> follows) {
//        this.follows = follows;
//    }
//
//    public List<InterestModel> getInterested() {
//        return interested;
//    }
//
//    public void setInterested(List<InterestModel> interested) {
//        this.interested = interested;
//    }
//
}

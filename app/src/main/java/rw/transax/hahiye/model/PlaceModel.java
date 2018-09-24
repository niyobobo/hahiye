package rw.transax.hahiye.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "places")
public class PlaceModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String uid;
    private String name;
    private String featured;
    private String website;
    private String tagLine;
    private String contact;
    private int verified;
    private String location;
    private String amenity;
    private Date createdAt;

    @Ignore
    public PlaceModel() {
    }

    public PlaceModel(int id, String uid, String name, String featured, String website, String tagLine,
                      String contact, int verified, String location, String amenity, Date createdAt) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.featured = featured;
        this.website = website;
        this.tagLine = tagLine;
        this.contact = contact;
        this.verified = verified;
        this.location = location;
        this.amenity = amenity;
        this.createdAt = createdAt;
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

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String  location) {
        this.location = location;
    }

    public String getAmenity() {
        return amenity;
    }

    public void setAmenity(String amenity) {
        this.amenity = amenity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    private static class Location {

        private String type;
        private String coordinates;

        public Location(String type, String coordinates) {
            this.type = type;
            this.coordinates = coordinates;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(String coordinates) {
            this.coordinates = coordinates;
        }
    }
}

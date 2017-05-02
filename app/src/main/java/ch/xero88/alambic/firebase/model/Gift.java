package ch.xero88.alambic.firebase.model;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Gift {

    public Gift() { // for firebase
    }

    private String name;
    private String description;
    private String picturePath;
    private int points;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

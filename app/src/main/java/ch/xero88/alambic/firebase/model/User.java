package ch.xero88.alambic.firebase.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public int points = 0;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
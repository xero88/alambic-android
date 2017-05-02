package ch.xero88.alambic.firebase.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Member {

    public int availablePoints = 0;
    public int usedPoints = 0;

    public Member() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(int availablePoints) {
        this.availablePoints = availablePoints;
    }

    public int getUsedPoints() {
        return usedPoints;
    }

    public void setUsedPoints(int usedPoints) {
        this.usedPoints = usedPoints;
    }
}
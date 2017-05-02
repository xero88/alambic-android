package ch.xero88.alambic.firebase.model;

import java.util.Date;

public class BoughtGift extends Gift {

    private Date boughtAt;

    public BoughtGift(Gift gift) {
        super();

        // populate gift with superclass gift
        setId(gift.getId());
        setName(gift.getName());
        setPoints(gift.getPoints());
        setDescription(getDescription());
    }

    public Date getBoughtAt() {
        return boughtAt;
    }

    public void setBoughtAt(Date boughtAt) {
        this.boughtAt = boughtAt;
    }
}

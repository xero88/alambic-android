package ch.xero88.alambic.firebase;

import ch.xero88.alambic.firebase.service.GiftService;
import ch.xero88.alambic.firebase.service.MemberService;
import ch.xero88.alambic.firebase.service.StripeService;

public class ServicesManager {

    /**
     * Instance of ServicesManager
     */
    private static ServicesManager instance;
    public static ServicesManager getInstance() {
        if(instance == null)
            instance = new ServicesManager();

        return instance;
    }

    /**
     * Services
     */
    private static GiftService giftService = null;
    private static MemberService memberService = null;
    private static StripeService stripeService = null;

    public GiftService getGiftService() {
        if(giftService == null)
            giftService = new GiftService();

        return giftService;
    }

    public MemberService getMemberService() {
        if(memberService == null)
            memberService = new MemberService();

        return memberService;
    }

    public StripeService getStripeService() {
        if(stripeService == null)
            stripeService = new StripeService();

        return stripeService;
    }
}

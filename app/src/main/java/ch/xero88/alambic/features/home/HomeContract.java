package ch.xero88.alambic.features.home;

public interface HomeContract {

    interface View {
        void updateWelcome(String displayName);
    }

    interface UserActionsListener {
        void init();
    }
}

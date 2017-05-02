package ch.xero88.alambic.features.home;

public interface HomeContract {

    interface View {
        void updateMemberName(String displayName);
        void updatePoints(int nbPoints);
        void showErrorCannotGetPoints(String error);
    }

    interface UserActionsListener {
        void init();
        void loggout();
    }
}

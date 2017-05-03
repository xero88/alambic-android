package ch.xero88.alambic.features.payTicket;

import com.stripe.android.model.Card;

interface PayTicketContract {

    interface View {
        void showErrorOnStripe();
        void cardSuccessfulAdded();
    }

    interface UserActionsListener {
        void sendStripeTokenToBackend(Card cardToSave);
    }
}
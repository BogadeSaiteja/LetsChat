package com.saiteja.bogade.letschat.core.files.logout;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by saite_000 on 2/20/2017.
 */

public class LogoutInteractor implements LogoutContract.Interactor {
    private LogoutContract.OnLogoutListener mOnLogoutListener;

    public LogoutInteractor(LogoutContract.OnLogoutListener onLogoutListener) {
        mOnLogoutListener = onLogoutListener;
    }

    @Override
    public void performFirebaseLogout() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
            mOnLogoutListener.onSuccess("Successfully logged out!");
        } else {
            mOnLogoutListener.onFailure("No user logged in yet!");
        }
    }
}

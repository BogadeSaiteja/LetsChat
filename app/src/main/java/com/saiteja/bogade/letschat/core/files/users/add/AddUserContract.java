package com.saiteja.bogade.letschat.core.files.users.add;
/**
 * Created by saite_000 on 2/20/2017.
 */

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;


public interface AddUserContract {
    interface View {
        void onAddUserSuccess(String message);

        void onAddUserFailure(String message);
    }

    interface Presenter {
        void addUser(Context context, FirebaseUser firebaseUser);
    }

    interface Interactor {
        void addUserToDatabase(Context context, FirebaseUser firebaseUser);
    }

    interface OnUserDatabaseListener {
        void onSuccess(String message);

        void onFailure(String message);
    }
}

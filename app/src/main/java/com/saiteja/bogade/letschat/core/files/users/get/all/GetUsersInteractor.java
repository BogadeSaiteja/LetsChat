package com.saiteja.bogade.letschat.core.files.users.get.all;
/**
 * Created by saite_000 on 2/20/2017.
 */

import android.text.TextUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saiteja.bogade.letschat.model.User;
import com.saiteja.bogade.letschat.util.Constants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GetUsersInteractor implements GetUsersContract.Interactor {
    private static final String TAG = "GetUsersInteractor";

    private GetUsersContract.OnGetAllUsersListener mOnGetAllUsersListener;

    public GetUsersInteractor(GetUsersContract.OnGetAllUsersListener onGetAllUsersListener) {
        this.mOnGetAllUsersListener = onGetAllUsersListener;
    }


    @Override
    public void getAllUsersFromFirebase() {
        FirebaseDatabase.getInstance().getReference().child(Constants.ARG_USERS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
                List<User> users = new ArrayList<>();
                while (dataSnapshots.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshots.next();
                    User user = dataSnapshotChild.getValue(User.class);
                    if (!TextUtils.equals(user.uid, FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        users.add(user);
                    }
                }
                mOnGetAllUsersListener.onGetAllUsersSuccess(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mOnGetAllUsersListener.onGetAllUsersFailure(databaseError.getMessage());
            }
        });
    }

    @Override
    public void getChatUsersFromFirebase() {
        /*FirebaseDatabase.getInstance().getReference().child(Constants.ARG_CHAT_ROOMS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataSnapshots=dataSnapshot.getChildren().iterator();
                List<User> users=new ArrayList<>();
                while (dataSnapshots.hasNext()){
                    DataSnapshot dataSnapshotChild=dataSnapshots.next();
                    dataSnapshotChild.getRef().
                    Chat chat=dataSnapshotChild.getValue(Chat.class);
                    if(chat.)4
                    if(!TextUtils.equals(user.uid,FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        users.add(user);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }
}

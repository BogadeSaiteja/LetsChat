package com.saiteja.bogade.letschat.ui.classes.activities;
/**
 * Created by saite_000 on 2/18/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.saiteja.bogade.letschat.R;
import com.saiteja.bogade.letschat.core.files.registration.RegisterContract;
import com.saiteja.bogade.letschat.core.files.registration.RegisterPresenter;
import com.saiteja.bogade.letschat.core.files.users.add.AddUserContract;
import com.saiteja.bogade.letschat.core.files.users.add.AddUserPresenter;
import com.saiteja.bogade.letschat.util.LoadingScreen;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RegisterContract.View, AddUserContract.View {

    private RegisterPresenter mRegisterPresenter;
    private AddUserPresenter mAddUserPresenter;
    private Context mContext;
    private EditText mETxtEmail, mETxtPassword;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this;
        mRegisterPresenter = new RegisterPresenter(this);
        mAddUserPresenter = new AddUserPresenter(this);
        mETxtEmail = (EditText) findViewById(R.id.edit_text_email_id);
        mETxtPassword = (EditText) findViewById(R.id.edit_text_password);
        mBtnRegister = (Button) findViewById(R.id.button_register);
        mBtnRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        switch (viewId) {
            case R.id.button_register:
                String emailId = mETxtEmail.getText().toString();
                String password = mETxtPassword.getText().toString();
                mRegisterPresenter.register(RegisterActivity.this, emailId, password);
                LoadingScreen.showProgressDialog(mContext, getString(R.string.loading));
                break;
        }
    }

    @Override
    public void onRegistrationSuccess(FirebaseUser firebaseUser) {
        LoadingScreen.updateIndicatorMessage(mContext, getString(R.string.adding_user_to_db));
        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
        mAddUserPresenter.addUser(RegisterActivity.this.getApplicationContext(), firebaseUser);
    }

    @Override
    public void onRegistrationFailure(String message) {
        LoadingScreen.dismissProgressDialog();
        Toast.makeText(this, "Registration failed!+\n" + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddUserSuccess(String message) {
        LoadingScreen.dismissProgressDialog();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mContext, UserListsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAddUserFailure(String message) {
        LoadingScreen.dismissProgressDialog();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
package com.saiteja.bogade.letschat.ui.classes.activities;
/**
 * Created by saite_000 on 2/18/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.saiteja.bogade.letschat.R;
import com.saiteja.bogade.letschat.core.files.login.LoginContract;
import com.saiteja.bogade.letschat.core.files.login.LoginPresenter;
import com.saiteja.bogade.letschat.util.LoadingScreen;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.View {

    private TextView mRegisterTV, mForgotPwdTV;
    private EditText mUserNameET, mPasswordET;
    private Button mSignInBtn;
    private Context mContext;

    private LoginPresenter mLoginPresenter;

    public static void startIntent(Context context, int flags) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        setUpView();
        applyCosmetics(mRegisterTV);
        applyCosmetics(mForgotPwdTV);

    }

    private void setUpView() {
        mRegisterTV = (TextView) findViewById(R.id.register_TV);
        mForgotPwdTV = (TextView) findViewById(R.id.forgot_pwd_TV);
        mUserNameET = (EditText) findViewById(R.id.username_ET);
        mPasswordET = (EditText) findViewById(R.id.password_ET);
        mSignInBtn = (Button) findViewById(R.id.sign_in_Btn);
        mSignInBtn.setOnClickListener(this);
        mRegisterTV.setOnClickListener(this);
        mForgotPwdTV.setOnClickListener(this);
        mLoginPresenter = new LoginPresenter(this);
    }

    private void applyCosmetics(TextView textView) {
        SpannableString spannableString = new SpannableString(textView.getText());
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
        textView.setText(spannableString);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sign_in_Btn:
                mLoginPresenter.login(LoginActivity.this, mUserNameET.getText().toString(), mPasswordET.getText().toString());
                LoadingScreen.showProgressDialog(mContext, "Logging in..");
                return;
            case R.id.forgot_pwd_TV:
                return;
            case R.id.register_TV:
                Intent registerIntent = new Intent(mContext, RegisterActivity.class);
                startActivity(registerIntent);
                return;
        }
    }

    @Override
    public void onLoginSuccess(String message) {
        LoadingScreen.dismissProgressDialog();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mContext, UserListsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailure(String message) {
        LoadingScreen.dismissProgressDialog();
        Toast.makeText(mContext, "Error: " + message, Toast.LENGTH_SHORT).show();
    }
}

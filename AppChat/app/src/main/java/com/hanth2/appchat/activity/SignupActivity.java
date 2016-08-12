package com.hanth2.appchat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.hanth2.appchat.R;
import com.hanth2.appchat.base.BaseActivity;

/**
 * Created by HanTH2 on 8/11/2016.
 */
public class SignupActivity extends BaseActivity implements View.OnClickListener{
    private final String TAG = SignupActivity.class.getSimpleName();
    private Button mBtnRegisterAccount;
    private EditText mEdtLoginUserName;
    private EditText mEdtLoginPassword;
    private EditText mEdtLoginRePassword;
    private String mEmail;
    private String mPassword;
    private String mPasswordRe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initView();
    }

    private void initView(){
        mBtnRegisterAccount = (Button)findViewById(R.id.btn_signup_account);
        mEdtLoginUserName = (EditText)findViewById(R.id.edt_login_user_name);
        mEdtLoginPassword = (EditText)findViewById(R.id.edt_login_password);
        mEdtLoginRePassword = (EditText)findViewById(R.id.edt_login_re_password);
        mBtnRegisterAccount.setOnClickListener(this);
    }

    private void SignupAccUser(){
        mEmail = mEdtLoginUserName.getText().toString();
        mPassword = mEdtLoginPassword.getText().toString();
        mPasswordRe = mEdtLoginRePassword.getText().toString();
        if (mPassword.equals(mPasswordRe)){
            Log.d(TAG, "createAccount:" + mEmail);
            showProgressDiaglog();
            // [START create_user_with_email]
            mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getApplicationContext(), "Authentication Success", Toast.LENGTH_LONG).show();
                                // [START_EXCLUDE]
                                hideProgressDialog();
                                // [END_EXCLUDE]
                                swichScreen();
                            }

                        }
                    });
            // [END create_user_with_email]

        }else {
            Toast.makeText(getApplicationContext(), "Password wrong", Toast.LENGTH_LONG).show();
        }
    }

    private void swichScreen(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signup_account:
                SignupAccUser();
                break;
        }
    }
}

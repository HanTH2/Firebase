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
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 1;
    private EditText mEdtLoginUserName;
    private EditText mEdtLoginPassword;
    private Button mBtnLoginSignin;
    private String mEmail;
    private String mPassword;
    private Button mBtnLoginSignup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView(){
        mEdtLoginUserName = (EditText)findViewById(R.id.edt_login_user_name);
        mEdtLoginPassword = (EditText)findViewById(R.id.edt_login_password);
        mBtnLoginSignin = (Button)findViewById(R.id.btn_login_signin);
        mBtnLoginSignup = (Button)findViewById(R.id.btn_login_signup);

        mBtnLoginSignin.setOnClickListener(this);
        mBtnLoginSignup.setOnClickListener(this);
    }

    private void SignIn(){
        showProgressDiaglog();
        mEmail = mEdtLoginUserName.getText().toString();
        mPassword = mEdtLoginPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, "auth fail",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            hideProgressDialog();
                        }

                        // ...
                    }
                });
    }

    private void switchScreen(){
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login_signin:
                SignIn();
                break;
            case R.id.btn_login_signup:
                switchScreen();
                break;
            default:
                break;
        }
    }
}

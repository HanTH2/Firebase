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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hanth2.appchat.R;
import com.hanth2.appchat.base.BaseActivity;

/**
 * Created by HanTH2 on 8/11/2016.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();;
    public static String USER_LOGIN;
    private EditText mEdtLoginUserName;
    private EditText mEdtLoginPassword;
    private Button mBtnLoginSignin;
    private String mEmail;
    private String mPassword;
    private Button mBtnLoginSignup;

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        onListenner();
    }

    private void initView(){
        mEdtLoginUserName = (EditText)findViewById(R.id.edt_login_user_name);
        mEdtLoginPassword = (EditText)findViewById(R.id.edt_login_password);
        mBtnLoginSignin = (Button)findViewById(R.id.btn_login_signin);
        mBtnLoginSignup = (Button)findViewById(R.id.btn_login_signup);

        mBtnLoginSignin.setOnClickListener(this);
        mBtnLoginSignup.setOnClickListener(this);
    }

    private void onListenner(){
        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
                //updateUI(user);
                // [END_EXCLUDE]
            }
        };
        // [END auth_state_listener]
    }

    private void SignIn(){
        //mEmail = mEdtLoginUserName.getText().toString();
        //mPassword = mEdtLoginPassword.getText().toString();
        mEmail = "hanth2test@gmail.com";
        mPassword = "Tigonlonton";
        Log.d(TAG, "signIn:" + mEmail);
        showProgressDiaglog();
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            // [START_EXCLUDE]
                            hideProgressDialog();
                            // [END_EXCLUDE]
                            Toast.makeText(getApplicationContext(), "Authentication Success", Toast.LENGTH_LONG).show();
                            switchScreenMain();
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    private void switchScreenMain(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(USER_LOGIN, mEmail);
        startActivity(intent);
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

    // [START on_start_add_listener]
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    // [END on_stop_remove_listener]
}

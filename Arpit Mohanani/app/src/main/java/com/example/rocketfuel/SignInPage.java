package com.example.rocketfuel;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import java.util.Arrays;

public class SignInPage extends AppCompatActivity {

    ImageView signBtnGoogle;
    ImageView signBtnFacebook;
    MaterialButton btnSignUp;

    CallbackManager callbackManager;
    AccessToken facebookAccessToken;

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    GoogleSignInAccount googleSignInAccount;
    ActivityResultLauncher<Intent> activityResultLauncher;
    Intent openHomeActivity;
    Bundle bundle = new Bundle();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signBtnGoogle = findViewById(R.id.imgGoogle);
        signBtnFacebook = findViewById(R.id.imgFacebook);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInPage.this, SignUpPage.class));
                finish();
            }
        });

        //Facebook SignIn
        FacebookSignInSetup();
        signBtnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(SignInPage.this, Arrays.asList("public_profile"));
            }
        });


        //Google SignIn
        GoogleSignInSetup();
        signBtnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                activityResultLauncher.launch(signInIntent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void FacebookSignInSetup(){
        callbackManager = CallbackManager.Factory.create();
        facebookAccessToken = AccessToken.getCurrentAccessToken();
        if(facebookAccessToken!=null && !facebookAccessToken.isExpired()){
            bundle.putString("SIGNINTYPE","Facebook");
            openHomeActivity = new Intent(SignInPage.this,HomeActivity.class);
            openHomeActivity.putExtras(bundle);
            startActivity(openHomeActivity);
            finish();
        }
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        bundle.putString("SIGNINTYPE","Facebook");
                        openHomeActivity = new Intent(SignInPage.this,HomeActivity.class);
                        openHomeActivity.putExtras(bundle);
                        startActivity(openHomeActivity);
                        finish();
                    }
                    @Override
                    public void onCancel() {
                        // App code
                    }
                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    private void GoogleSignInSetup(){
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);
        googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

        // checking if user is already logged in
        if(googleSignInAccount != null){
            bundle.putString("SIGNINTYPE","Google");
            openHomeActivity = new Intent(SignInPage.this,HomeActivity.class);
            openHomeActivity.putExtras(bundle);
            startActivity(openHomeActivity);
            finish();
        }
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        //getting signed in account after user selected an account from google accounts dialog
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                        handleGoogleSignInTask(task);
                    }
                });
    }

    private void handleGoogleSignInTask(Task<GoogleSignInAccount> task){
        try {
            GoogleSignInAccount acct = task.getResult(ApiException.class);

            String personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
//            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            //opening HomeActivity
            bundle.putString("SIGNINTYPE","Google");
            openHomeActivity = new Intent(SignInPage.this,HomeActivity.class);
            openHomeActivity.putExtras(bundle);
            startActivity(openHomeActivity);
            finish();
        } catch (ApiException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed or Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.drunkornot.lingudetect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.drunkornot.lingudetect.lingu.AppSettings;
import com.drunkornot.lingudetect.lingu.Database;
import com.drunkornot.lingudetect.lingu.IUserDataChangeListener;
import com.drunkornot.lingudetect.lingu.UserData;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthenticationActivity extends AppCompatActivity implements IUserDataChangeListener  {

    private static final String TAG = "LoginActivity";
    //region Firebase SignIn
    private FirebaseAuth mAuth;
    //endregion
    //region Google SignIn
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    //endregion
    //region UI
    Button btnLogInAnonymously;
    SignInButton btnLogInWithGoogle;
    TextView txtStatus;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        btnLogInAnonymously = findViewById(R.id.btnAuthAnonymously);
        btnLogInWithGoogle = findViewById(R.id.btnAuthWithGoogle);

        txtStatus = findViewById(R.id.txtStatus);

        mAuth = FirebaseAuth.getInstance();

        Database.AddListener(this);
        
        btnLogInAnonymously.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthenticateAnonymously();
            }
        });

        btnLogInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthenticateWithGoogle();
            }
        });


    }

//region Request LogIn
private void AuthenticateAnonymously() {
        AuthenticationWaiting();
    mAuth.signInAnonymously()
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInAnonymously:success");
                        AuthenticationSuccess(AppSettings.AuthType.Anonymous);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInAnonymously:failure", task.getException());
                        AuthenticationFailure("Anonymous authentication failure");
                    }
                }
            });
}

private void AuthenticateWithGoogle() {
        AuthenticationWaiting();
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build();

    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_SIGN_IN);
}
//endregion

//region Reply LogIn

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
                AuthenticationFailure("Google authentication failure");
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            AuthenticationSuccess(AppSettings.AuthType.Google);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            AuthenticationFailure("Firebase Auth with Google Failure");
                        }
                    }
                });
    }

//endregion

    public void AuthenticationWaiting() {
        txtStatus.setText("Authentication in progress...");
    }

    public void AuthenticationSuccess(AppSettings.AuthType authType) {
        AppSettings.Instance().SetAuthType(authType);
        Database.GetData(mAuth.getUid());
    }

    public void AuthenticationFailure(String message) {
        txtStatus.setText(message);
    }

    @Override
    public void onUserDataReceivedFromDatabase(UserData userData) {
        if(!AppSettings.Instance().IsUserSignedIn()) {
        if(userData == null) {
            userData = new UserData(mAuth.getUid());
        }
        AppSettings.Instance().ChangeCurrentUser(userData);
        AuthenticationActivity.this.startActivity(new Intent(AuthenticationActivity.this, MainActivity.class));
        }
    }
    @Override
    public void onUserDataReceivedFailure(){
        if(!AppSettings.Instance().IsUserSignedIn()) {
            AuthenticationFailure("Failed getting data from database");
            AppSettings.Instance().SetAuthType(AppSettings.AuthType.None);
        }
    }
}
/*
 * *
 *  * Created by Adam Elaoumari on 26/12/20 00:59
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 19/12/20 21:19
 *  
 */

package com.adamlbs.reportaggression;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.OAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMPTY = "";
    private EditText etUsername;
    public String maintenance;
    private EditText etPassword;
    private String username;
    private String number;
    private static final int MY_REQUEST_CODE = 1;

    private String password;
    private GoogleSignInClient googleSignInClient;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private static final int RC_SIGN_IN = 49404;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private ProgressDialog pDialog;
    private final String login_url = "https://api.lineflag.com/login.php";
    private SessionHandler session;
    Activity context = this;
    Location gps_loc;
    Location network_loc;
    Location final_loc;
    double longitude;
    double latitude;
    String userCountry, userAddress;
//TODO Tout fini ici

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Location", MODE_PRIVATE);
        pref.getString("city", null);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        try {

            gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (gps_loc != null) {
            final_loc = gps_loc;
            latitude = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
        } else if (network_loc != null) {
            final_loc = network_loc;
            latitude = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
        } else {
            latitude = 0.0;
            longitude = 0.0;
        }
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                userCountry = addresses.get(0).getLocality();
                userAddress = addresses.get(0).getAdminArea();
                Log.d("LOCATION DEV", "token " + userCountry);

            } else {
                userCountry = "oeoe";
                Log.d("LOCATION DEV", "IDK " + userCountry);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        String requiredPermission = Manifest.permission.ACCESS_FINE_LOCATION;
        int checkVal = LoginActivity.this.checkCallingOrSelfPermission(requiredPermission);
        if (checkVal == PackageManager.PERMISSION_GRANTED) {
            Log.d("permission granted", "IDK " + userCountry);
        }
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("city", userCountry).apply();


        setTheme(R.style.AppTheme);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        session = new SessionHandler(getApplicationContext());
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Build a GoogleSignInClient with the options specified by gso.
        if (session.isLoggedIn()) {
loadDashboard();
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        OAuthProvider.Builder provider = OAuthProvider.newBuilder("twitter.com");
        provider.addCustomParameter("lang", "fr");

        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        etUsername = findViewById(R.id.etLoginUsername);
        etPassword = findViewById(R.id.etLoginPassword);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        ImageButton login = findViewById(R.id.btnLogin);
        ImageButton twitter = findViewById(R.id.twitter);
        ImageButton googleButton = findViewById(R.id.sign_in_button);
        ImageButton phoneButton = findViewById(R.id.phone);

        googleButton.setOnClickListener(v -> {
            if (v.getId() == R.id.sign_in_button) {
                googleconfig();
            }
        });

        //Launch SMS screen when Register Button is clicked
        phoneButton.setOnClickListener(v -> config_phone());

        login.setOnClickListener(v -> config());
        twitter.setOnClickListener(v -> twitterconfig());
        final FloatingActionButton fab = findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(view -> ((Application) getApplication()).getShaky().startFeedbackFlow());
        }
    }

    /**
     * Launch Dashboard Activity on Successful Login
     */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // Take user to log in screen
            Log.d("Firebase", "NOT LOGGED IN");

        } else {
            // User already logged in
            loadDashboard();
        }
    }

    private void loadDashboard() {
        if (Objects.equals(userCountry, "Marseille")) {
            Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(i);
            finish();
        } else {
            loadDashboardParis();
        }

    }

    private void loadDashboardParis() {
        Intent i = new Intent(getApplicationContext(), DashboardParis.class);
        startActivity(i);
        finish();
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        mAuth.getCurrentUser();
                        loadDashboard();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                    }

                    // ...
                });
    }

    /**
     * Display Progress bar while Logging in
     */

    private void phonelogin() {
        Log.w(TAG, "1");
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Se connecter par SMS");
        alert.setMessage("Veuillez entrer votre numéro de téléphone."+
                "\n" +
                "\nDes frais standard peuvent s'appliquer lors de la réception du SMS." );

// Set an EditText view to get user input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);

        alert.setView(input);
        final String prefix = "+33";
        input.setText(prefix);
        alert.setPositiveButton("Ok", (dialog, whichButton) -> {

            number = input.getText().toString().toLowerCase().trim();

            phone();


        });

        alert.setNegativeButton("Cancel", (dialog, whichButton) -> {
            // Canceled.
        });

        alert.show();
    }

    private void phone() {
        ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "En attente du SMS de vérification",
                "Veuillez patienter...", true);
        PhoneAuthProvider.OnVerificationStateChangedCallbacks     mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            String mVerificationId;
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);

                signInWithPhoneAuthCredential(credential);
            }
            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {

                    dialog.dismiss();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Toast.makeText(LoginActivity.this, "Veuillez contacter @LineFlagApp sur Twitter où secouer votre téléphone.. ",
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }

                Toast.makeText(LoginActivity.this, "Impossible de vous connecter par téléphone. Veuillez secouer votre téléphone pour signaler un bug. ",
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
            }
        };// OnVerificationStateChangedCallbacks

PhoneAuthOptions options =
        PhoneAuthOptions.newBuilder(mAuth)
        .setPhoneNumber(number)
        .setTimeout(60L, TimeUnit.SECONDS)
        .setActivity(this)
        .setCallbacks(mCallbacks)
        .build();

PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void login2() {
        username = etUsername.getText().toString().toLowerCase().trim();
        password = etPassword.getText().toString().trim();
        Log.w(TAG, "1");
        if (validateInputs()) {
            login();
        }

  }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithCredential:success");

                        task.getResult().getUser();
                        loadDashboard();
                    } else {
                        Toast.makeText(LoginActivity.this, "Impossible de vous connecter par téléphone. Veuillez secouer votre téléphone pour signaler ce problème.",
                                Toast.LENGTH_SHORT).show();

                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                    }
                });
    }
    private void config_phone() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .build();
        mFirebaseRemoteConfig.fetch(0);

        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        boolean updated = task.getResult();
                        Log.d(TAG, "Config params updated: " + updated);
                        //  Toast.makeText(LoginActivity.this, "Fetch and activate succeeded",
                        //        Toast.LENGTH_SHORT).show();
                        // Toast.makeText(LoginActivity.this, mFirebaseRemoteConfig.getString("maintenance"),
                        //       Toast.LENGTH_SHORT).show();
                        maintenance = mFirebaseRemoteConfig.getString("maintenance_phone");
                        if (maintenance.equals("0")) {
                            Log.w(TAG, "0");
                            Toast.makeText(LoginActivity.this, "La connexion via SMS a été désactivée pour une maintenance.",
                                    Toast.LENGTH_SHORT).show();
                        } else
                            phonelogin();
                        //Retrieve the data entered in the edit texts
                    }


                    else {
                        Toast.makeText(LoginActivity.this, "Erreur. Veuillez secouer votre téléphone pour signaler cette erreur.",
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void config() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .build();
        mFirebaseRemoteConfig.fetch(0);

        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        boolean updated = task.getResult();
                        Log.d(TAG, "Config params updated: " + updated);
                        //  Toast.makeText(LoginActivity.this, "Fetch and activate succeeded",
                        //        Toast.LENGTH_SHORT).show();
                        // Toast.makeText(LoginActivity.this, mFirebaseRemoteConfig.getString("maintenance"),
                        //       Toast.LENGTH_SHORT).show();
                        maintenance = mFirebaseRemoteConfig.getString("maintenance");
                        if (maintenance.equals("0")) {
                            Log.w(TAG, "0");
                            Toast.makeText(LoginActivity.this, "La connexion via mail/mot de passe a été désactivée.",
                                    Toast.LENGTH_SHORT).show();
                        } else
                            login2();
                        //Retrieve the data entered in the edit texts
                    }


                    else {
                        Toast.makeText(LoginActivity.this, "Erreur. Veuillez réessayer ultérieurement..",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void googleconfig() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .build();
        mFirebaseRemoteConfig.fetch(0);

        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        boolean updated = task.getResult();
                        Log.d(TAG, "Config params updated: " + updated);
                        //  Toast.makeText(LoginActivity.this, "Fetch and activate succeeded",
                        //        Toast.LENGTH_SHORT).show();
                        // Toast.makeText(LoginActivity.this, mFirebaseRemoteConfig.getString("maintenance"),
                        //       Toast.LENGTH_SHORT).show();
                        maintenance = mFirebaseRemoteConfig.getString("maintenance");
                        if (maintenance.equals("0")) {
                            Log.w(TAG, "0");
                            Toast.makeText(LoginActivity.this, "La connexion via Google a été désactivée.",
                                    Toast.LENGTH_SHORT).show();
                        } else

                            signIn();
                        //Retrieve the data entered in the edit texts
                    }


                    else {
                        Toast.makeText(LoginActivity.this, "Erreur. Veuillez secouer votre téléphone pour signaler cette erreur.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void twitterconfig() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .build();
        mFirebaseRemoteConfig.fetch(0);

        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        boolean updated = task.getResult();
                        Log.d(TAG, "Config params updated: " + updated);
                        //  Toast.makeText(LoginActivity.this, "Fetch and activate succeeded",
                        //        Toast.LENGTH_SHORT).show();
                        // Toast.makeText(LoginActivity.this, mFirebaseRemoteConfig.getString("maintenance"),
                        //       Toast.LENGTH_SHORT).show();
                        maintenance = mFirebaseRemoteConfig.getString("maintenance");
                        if (maintenance.equals("0")) {
                            Log.w(TAG, "0");
                            Toast.makeText(LoginActivity.this, "La connexion via Twitter a été désactivée.",
                                    Toast.LENGTH_SHORT).show();
                        } else
                            twitterLogin();
                        //Retrieve the data entered in the edit texts
                    }


                    else {
                        Toast.makeText(LoginActivity.this, "Erreur. Veuillez secouer votre téléphone pour signaler cette erreur.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

private void twitterLogin() {
    OAuthProvider.Builder provider = OAuthProvider.newBuilder("twitter.com");
    provider.addCustomParameter("lang", "fr");
    mAuth
            .startActivityForSignInWithProvider(/* activity= */ this, provider.build())
            .addOnSuccessListener(
                    authResult -> {
                        // User is signed in.
                        // IdP data available in
                        // authResult.getAdditionalUserInfo().getProfile().
                        // The OAuth access token can also be retrieved:
                        // authResult.getCredential().getAccessToken().
                        // The OAuth secret can be retrieved by calling:
                        // authResult.getCredential().getSecret().
                        Log.d(TAG, "signInWithCredential:success");
                        loadDashboard();
                    })
            .addOnFailureListener(
                    e -> Log.w(TAG, "Twitter sign in failed", e));

}
    private void displayLoader() {
        pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Logging In.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }
    private void login() {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_USERNAME, username);
            request.put(KEY_PASSWORD, password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, login_url, request, response -> {
                    pDialog.dismiss();
                    try {
                        //Check if user got logged in successfully

                        if (response.getInt(KEY_STATUS) == 0) {
                            session.loginUser(username,response.getString(KEY_FULL_NAME));
                            loadDashboard();

                        }else{
                            Toast.makeText(getApplicationContext(),
                                    response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    pDialog.dismiss();

                    //Display error message whenever an error occurs
                    Toast.makeText(LoginActivity.this, "Impossible de contacter le serveur d'authentification... Veuillez réssayer ultérieurement. ",
                            Toast.LENGTH_SHORT).show();
                }) {

                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> params = new HashMap<>();
                        params.put("User-Agent", "LineFlag-App");
                        params.put("language", "fr");

                        return params;
                    }
                };

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }

    /**
     * Validates inputs and shows error if any
     */
    private boolean validateInputs() {
        if(KEY_EMPTY.equals(username)){
            etUsername.setError("Username cannot be empty");
            etUsername.requestFocus();
            return false;
        }
        if(KEY_EMPTY.equals(password)){
            etPassword.setError("Password cannot be empty");
            etPassword.requestFocus();
            return false;
        }
        return true;
    }
    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

        @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == MY_REQUEST_CODE) {
                if (resultCode != RESULT_OK) {
                    Log.d("1", "Update flow failed! Result code: " + resultCode);
                    // If the update is cancelled or fails,
                    // you can request to start the update again.
                }
            }
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }
}

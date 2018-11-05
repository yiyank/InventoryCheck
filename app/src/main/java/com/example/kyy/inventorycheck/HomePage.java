package com.example.kyy.inventorycheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomePage extends Activity implements View.OnClickListener {

    private static final String TAG = "HomePage";

    private Button LogoutButton, AddButton, CheckButton;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        LogoutButton = findViewById(R.id.LogoutButton);
        AddButton = findViewById(R.id.AddButton);
        CheckButton = findViewById(R.id.CheckButton);

        setupFirebaseListener();
        LogoutButton.setOnClickListener(HomePage.this);
        AddButton.setOnClickListener(HomePage.this);
        CheckButton.setOnClickListener(HomePage.this);
    }


    @Override
    public void onClick(View view) {

        if (view == LogoutButton) {
            Log.d(TAG, "onClick: attempting to sign out the user");
            FirebaseAuth.getInstance().signOut();
        } else if (view == AddButton) {
            Intent intent2 = new Intent(HomePage.this, AddActivity.class);
            startActivity(intent2);
        } else if (view == CheckButton) {
            Intent intent3 = new Intent(HomePage.this, CheckActivity.class);
            startActivity(intent3);
        }

    }

    private void setupFirebaseListener(){
        Log.d(TAG,"setupFirebaseListener: setting up the state listener.");
        mAuthStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    Log.d(TAG,"onAuthStateChanged: signed_in: " + user.getUid());
                }else {
                    Log.d(TAG, "onAuthStateChanged: signed_out");
                    Toast.makeText(HomePage.this,"Signed out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomePage.this, Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthStateListener);
    }
    @Override
    public void onStop(){
        super.onStop();
        if(mAuthStateListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater optionMenuInflater = getMenuInflater();
        optionMenuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.Home:
                //Intent go1 = new Intent(this, HomePage.class);
                //this.startActivity(go1);
                return true;
            case R.id.Add:
                Intent go2 = new Intent(this, AddActivity.class);
                this.startActivity(go2);
                return true;
            case R.id.Check:
                Intent go3 = new Intent(this, CheckActivity.class);
                this.startActivity(go3);
                return true;

            default:
                return false;
        }
    }
}
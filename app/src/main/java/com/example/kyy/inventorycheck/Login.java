package com.example.kyy.inventorycheck;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class Login extends Activity {

    private static final String TAG = "Login";
    //Define private buttons and edit texts
    private Button LogButton, Register;
    private EditText username, password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Firebase Initialization to track sign-in and sign-out
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user !=null){
                    //User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" +user.getUid());
                }else{
                    //User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };


        //link the private variable with the buttons and texts in the user interface
        LogButton = findViewById(R.id.Log);
        Register = findViewById(R.id.Register);
        username = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);

        //set onclick activity for each button
        LogButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                signIn(username.getText().toString(), password.getText().toString());
            }
        });

        Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                createAccount(username.getText().toString(),password.getText().toString());
            }
        });

    }

    //Initialize start and top authentication listeners
    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    public void onStop(){
        super.onStop();
        if(mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



    public void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                if (!task.isSuccessful()) {
                    Toast.makeText(Login.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(Login.this, "Register Successful", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithEmail:onComplete:" +task.isSuccessful());
                if(!task.isSuccessful()){
                    Toast.makeText(Login.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intentCreate = new Intent(Login.this, HomePage.class);
                    startActivity(intentCreate);
                }
            }
        });
    }




















}



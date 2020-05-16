package com.e.myquizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class LoginActivity extends AppCompatActivity {

    AppCompatButton mAppCompatButton;
    AppCompatButton mLoginButton;

    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;

    TextInputLayout mEmailTextInputLayout;
    TextInputLayout mPasswordTextInputLayout;

    EditText mEmailEditText;
    EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseAuth.getCurrentUser();

        mAppCompatButton=findViewById(R.id.not_a_member_signup_button);
        mLoginButton=findViewById(R.id.login_button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });


//        mAppCompatButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
//
//            final String email=mEmailEditText.getText().toString().trim();
//             final String password=mPasswordEditText.getText().toString().trim();
//
//                mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(LoginActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                            if(task.isSuccessful()){
//                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
//                            }else {
//                                Toast.makeText(LoginActivity.this,"Sign in Failed!",Toast.LENGTH_SHORT).show();
//                            }
//                    }
//                });
//
//            }
//        });


    }
}

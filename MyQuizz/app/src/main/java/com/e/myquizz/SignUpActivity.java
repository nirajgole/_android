package com.e.myquizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout mNameTextInputLayout;
    EditText mNameEditText;

    TextInputLayout mEmailTextInputLayout;
    EditText mEmailEditText;

    TextInputLayout mPasswordInputText;
    EditText mPasswordEditText;

    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;

    AppCompatButton mAppCompatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseAuth.getCurrentUser();

        mNameTextInputLayout=findViewById(R.id.nameTextInputLayout);
        mNameEditText=findViewById(R.id.nameEditTextView);

        mEmailTextInputLayout=findViewById(R.id.emailTextInputLayout);
        mEmailEditText=findViewById(R.id.emailEditTextView);

        mPasswordInputText=findViewById(R.id.passwordTextInputLayout);
        mPasswordEditText=findViewById(R.id.passwordEditTextView);


        mAppCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = mEmailEditText.getText().toString().trim();
                final String password = mPasswordEditText.getText().toString().trim();

                mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                        }else{
                            Toast.makeText(SignUpActivity.this,"Sign up Failed!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}

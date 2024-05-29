package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    Button cancel,submit;
    User user;
    private FirebaseAuth mAuth;
    private DatabaseReference myDB;
    EditText usernameTF,fullNameTF,phoneNumberTF,photoURLTF,passwordTF,confirmPasswordTF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cancel = (Button) findViewById(R.id.cancelBtn);
        submit = (Button) findViewById(R.id.submitBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                registerUser();
                
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
    private void registerUser(){
        photoURLTF = (EditText) findViewById(R.id.regProfilePhotoTF);
        usernameTF = (EditText) findViewById(R.id.regUsernameTF);
        fullNameTF = (EditText) findViewById(R.id.regFullnameTF);
        phoneNumberTF = (EditText) findViewById(R.id.regPhoneNumberTF);
        passwordTF = (EditText) findViewById(R.id.regPasswordTF);
        confirmPasswordTF = (EditText) findViewById(R.id.regConfirmPasswordTF);
        myDB = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        if (passwordTF.getText().toString().equals(confirmPasswordTF.getText().toString()) ){
            mAuth.createUserWithEmailAndPassword(usernameTF.getText().toString(),passwordTF.getText().toString()).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String infoID = myDB.push().getKey();
                    User userInfo = new User(fullNameTF.getText().toString(),photoURLTF.getText().toString(),user.getUid(),phoneNumberTF.getText().toString());
                    if(infoID!=null){
                        myDB.child("userInfo").child(infoID).setValue(userInfo).addOnCompleteListener(Register.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Register.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                Intent redirectToLogin = new Intent(Register.this,MainActivity.class);
                                startActivity(redirectToLogin);
                            }
                        });
                    }else{
                        Toast.makeText(Register.this, "Something wrong happened while trying to register.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(Register.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
        }
    }
}
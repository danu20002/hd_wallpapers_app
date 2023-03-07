package com.example.firing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.database.FirebaseDatabase;

public class signup_activity extends AppCompatActivity {
EditText signup_email,signup_password;

Button signupbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
     signup_email=findViewById(R.id.signupemail);
     signup_password=findViewById(R.id.signuppassword);
     signupbtn=findViewById(R.id.signupbtn);
        FirebaseAuth auth=FirebaseAuth.getInstance();


      signupbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String user=signup_email.getText().toString();
              String password=signup_password.getText().toString();
              if(user.isEmpty()){
                  signup_email.setError("required");
              }
                  if(password.isEmpty()){
                      signup_password.setError("required");
                  }else {
                      auth.createUserWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if(task.isSuccessful()){
                                  auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                      @Override
                                      public void onComplete(@NonNull Task<Void> task) {
                                          if(task.isSuccessful()){
                                              Toast.makeText(signup_activity.this, "signup successful", Toast.LENGTH_SHORT).show();
                                              Intent intent=new Intent(signup_activity.this,MainActivity.class);
                                              startActivity(intent);
                                              finish();
                                          }else{
                                              Toast.makeText(signup_activity.this, "email verification error occured", Toast.LENGTH_SHORT).show();
                                          }

                                      }
                                  });



                              }else {
                                  Toast.makeText(signup_activity.this, "something wrong with data"+task.getException(), Toast.LENGTH_SHORT).show();
                              }
                          }
                      });
                  }

          }
      });
    }
}
package com.example.firing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
public  static final String SHARED_PREFS="sharedprefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginbtn=(Button) findViewById(R.id.loginbtn);
        EditText email=(EditText) findViewById(R.id.Email);
        EditText password=(EditText) findViewById(R.id.password);
        TextView forgotpassword=(TextView) findViewById(R.id.forgotpassword);
        FirebaseAuth auth=FirebaseAuth.getInstance();

CheckBox checkBox=(CheckBox) findViewById(R.id.stayloggedin);
        checkbox();



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailcheck=email.getText().toString();
                String passwordcheck=password.getText().toString();
               if (!emailcheck.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailcheck).matches()){
                   if (!passwordcheck.isEmpty()){
                       auth.signInWithEmailAndPassword(emailcheck,passwordcheck).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                           @Override
                           public void onSuccess(AuthResult authResult) {
                               String emailkink=email.getText().toString();
                               SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                               SharedPreferences.Editor editor=sharedPreferences.edit();
                               editor.putString("name","true");
                               editor.apply();
                                      if(auth.getCurrentUser().isEmailVerified()){
                                          Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                                          startActivity(new Intent(MainActivity.this,afterlogin.class));
                                          finish();
                                      }else {
                                          Toast.makeText(MainActivity.this, "email not verified yet", Toast.LENGTH_SHORT).show();
                                      }


                           }

                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(MainActivity.this, "sorry login failure", Toast.LENGTH_SHORT).show();
                           }
                       });
                   }else {
                       email.setError("required bro");
                   }
               }else if(emailcheck.isEmpty()){
                   password.setError("required bro");

               }else{
                   email.setError("please enter emaiil");
               }


            }
        });
        TextView newuser=(TextView) findViewById(R.id.newuser);
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,signup_activity.class);
                startActivity(intent);
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,forgotpassword.class));
                  finish();
            }
        });


    }

    private void checkbox() {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String check=sharedPreferences.getString("name","");
        if(check.equals("true")){
            Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,mainscreen.class));
            finish();
        }
    }
}
package com.example.firing;

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
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
EditText forgotemail;
FirebaseAuth auth;
Button forgotbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        forgotbtn=findViewById(R.id.forgotbtn);
        forgotemail=findViewById(R.id.forgotemail);
        auth=FirebaseAuth.getInstance();
        forgotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String forgotmail=forgotemail.getText().toString();
                if(!forgotmail.isEmpty()){
                    auth.sendPasswordResetEmail(forgotmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                forgotbtn.setText("see your Gmail");
                                startActivity(new Intent(forgotpassword.this,MainActivity.class));
                                finish();
                            }else{
                                Toast.makeText(forgotpassword.this, "something went wrong"+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
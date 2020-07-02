package com.rizik.training.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText EmailId, Password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        EmailId = findViewById(R.id.editText_email);
        Password = findViewById(R.id.editText_password);
        tvSignIn = findViewById(R.id.textView_SignIn);
        btnSignUp = findViewById(R.id.button_signUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = EmailId.getText().toString();
                String Pwd = Password.getText().toString();
                if (Email.isEmpty()){
                    EmailId.setError("Please Enter Email Id!");
                    EmailId.requestFocus();
                }
                else if(Pwd.isEmpty()){
                    Password.setError("Please Enter Your Password!");
                    Password.requestFocus();
                }
                else if(Email.isEmpty() && Pwd.isEmpty()){
                    Toast.makeText(MainActivity.this, "Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                }
                else if (!(Email.isEmpty() && Pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(Email, Pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Daftar gagal, coba lagi", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "Terjadi Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}

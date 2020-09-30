package com.ldh.onthidaihoc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ldh.onthidaihoc.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {



    private EditText edtEmail, edtPassword;
    private TextView tvCreateUser;
    private Button btnLogin;

    public final static String EMAIL_REGEX = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mapping();
        firebaseAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(this);
        tvCreateUser.setOnClickListener(this);
    }

    private void mapping() {
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        tvCreateUser = findViewById(R.id.tv_register);
        btnLogin = findViewById(R.id.btn_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang Đăng Nhập!");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_register:
                register();
                break;

        }
    }


    private void register() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(LoginActivity.this);
    }

    private void login() {

        String email = edtEmail.getText().toString();
        String pass = edtPassword.getText().toString();

        if (!email.matches(EMAIL_REGEX)) {
            edtEmail.setError("Hãy Nhập Đúng Email!");
            return;
        } else {
            loginUser(email, pass);
        }
    }

    private void loginUser(String email, String pass) {
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        } else {

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();

            }
        });
    }
}
package com.ldh.onthidaihoc.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ldh.onthidaihoc.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtCheckPass, edtPass, edtPhone, edtEmail;
    private Button btnRegister;

    private TextView tvLogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    public final static String EMAIL_REGEX = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mapping();


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang Đăng Ký");
        firebaseAuth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                edtCheckPass.setError(null);
                edtPass.setError(null);
                edtPhone.setError(null);
                edtEmail.setError(null);

                if (edtPass.getText().toString().isEmpty()) {
                    edtPass.setError("Chưa Nhập");
                    return;
                }
                if (edtPhone.getText().toString().isEmpty()) {
                    edtPhone.setError("Chưa Nhập");
                    return;
                }
                if (edtEmail.getText().toString().isEmpty()) {
                    edtEmail.setError("Chưa Nhập");
                    return;
                }
                if (!edtPass.getText().toString().equals(edtCheckPass.getText().toString())){
                    edtCheckPass.setError("Xác Nhận Mật Khẩu Sai");
                }

                if (!edtEmail.getText().toString().matches(EMAIL_REGEX)){
                    edtEmail.setError("Hãy Nhập Đúng Email!");
                    return;
                }
                if (edtPhone.getText().toString().length()!=10){
                    edtPhone.setError("Hãy Nhập Đúng Số Điện Thoại");
                    return;
                }else {
                    createUser(edtEmail.getText().toString().trim(),edtPass.getText().toString().trim());
                }



            }


        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                Animatoo.animateSlideRight(RegisterActivity.this);
            }
        });
    }

    private void createUser(String email, String pass) {
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    progressDialog.dismiss();
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
                                    finish();

                                }else {

                                }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                }
            });
    }


    private void mapping() {

        edtCheckPass = findViewById(R.id.edt_check_pass);
        edtPass = findViewById(R.id.edt_password);
        edtPhone = findViewById(R.id.edt_phone);
        edtEmail = findViewById(R.id.edt_email);
        btnRegister = findViewById(R.id.bt_register);
        tvLogin = findViewById(R.id.tv_login);

    }
}
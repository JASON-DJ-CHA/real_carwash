package com.jason.example.toyproject_carwash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jason.example.toyproject_carwash.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        binding.btnSignupInLogin.setOnClickListener(this::moveToSignup);
        binding.btnLogin.setOnClickListener(this::login);
        binding.btnMoveMain.setOnClickListener(this::moveToMain);
    }

    private void moveToMain(View view) {
        useIntent(MainActivity.class);
    }

    private void login(View view) {
        String email = binding.etEmailAddress.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if(email.length() >0 && password.length() >0){
            //firebase
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                informMsg("로그인 성공");
                                useIntent(MainActivity.class);

                            } else {
                                // If sign in fails, display a message to the user.
                                if(task.getException() != null){
                                    informMsg(task.getException().toString());
                                }
                                informMsg("로그인에 실패하였습니다.");
                            }
                        }
                    });
            //firebase end
        }else {
            informMsg("이메일 혹은 비밀번호를 확인해주세요.");
        }

    }

    private void moveToSignup(View view) {
        Intent intent = new Intent(this, Sign_up_Activity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    private void informMsg(String msg) {Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();}
    private void useIntent(Class className){
        Intent intent = new Intent(this, className);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
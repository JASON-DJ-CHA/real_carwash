package com.jason.example.toyproject_carwash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jason.example.toyproject_carwash.databinding.ActivitySignUpBinding;

public class Sign_up_Activity extends AppCompatActivity {
    //FirebaseAuth 인스턴스 선언
    private FirebaseAuth mAuth;

    //    데이터 바인딩 사용하기1
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // FirebaseAuth 인스턴스를 초기화
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //      데이터바인딩사용하기 2
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        String email = binding.etEmailAddress.getText().toString();
        String pw1 = binding.etPassword1.getText().toString();
        String pw2 = binding.etPassword2.getText().toString();

        binding.btnCheck.setOnClickListener(this::emailCheck);
        binding.btnSignUp.setOnClickListener(this::signup);
        binding.imgX.setOnClickListener(this::backToLogin);

        // textView들 변경관련 기능 추가해주기.

        // 전체 동의 checkBox 기능 넣어주기


    }

    //활동을 초기화 시 사용자의 현재 로그인 상태 확인
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    private void backToLogin(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private void signup(View view) {
        createUser();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private  void createUser(){
        String email = binding.etEmailAddress.getText().toString();
        String password1 = binding.etPassword1.getText().toString();
        String password2 = binding.etPassword2.getText().toString();

        // 각 글자가 0이 아닐때.
        if(email.length() > 0 && password1.length() > 7 && password2.length() > 7){
            //pw가 일치할때
            if(password1.equals(password2)){
                // 체크박스 확인
                if(binding.cbFirst.isChecked() && binding.cbSecond.isChecked()) {
                    mAuth.createUserWithEmailAndPassword(email, password1)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        informMsg("회원가입 성공");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        informMsg("회원가입 실패");
                                    }
                                }
                            });
                } else{
                    informMsg("체크박스 항목을 확인해주세요.");
                }
                //체크박스
            }else {
                informMsg("비밀번호가 일치하지 않습니다.");

                binding.tvPassword.setText("비밀번호가 일치하지 않습니다.");
            }
            //pw일치 확인
        }else {
            informMsg("각 항목에 알맞게 입력해주세요");
        }
        //각 글자가 0이 아닐때 확인
    }

    private void emailCheck(View view) {
        //email 중복확인 기능 넣어주기.
    }

    private  void informMsg(String msg) {Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();}
}
package com.jason.example.toyproject_carwash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jason.example.toyproject_carwash.databinding.ActivitySignUpBinding;

public class Sign_up_Activity extends AppCompatActivity {

    //    데이터 바인딩 사용하기1
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //      데이터바인딩사용하기 2
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        String email = binding.etEmailAddress.getText().toString();
        String pw1 = binding.etPassword1.getText().toString();
        String pw2 = binding.etPassword2.getText().toString();
        String nickname = binding.etNickName.getText().toString();

        binding.btnCheck.setOnClickListener(this::emailCheck);
        binding.btnSignUp.setOnClickListener(this::signup);
        binding.imgX.setOnClickListener(this::backToLogin);

        // textView들 변경관련 기능 추가해주기.

        // 전체 동의 checkBox 기능 넣어주기


    }

    private void backToLogin(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void signup(View view) {

        // 이메일 중복확인 확인. if
        // 비밀번호 일치 확인 if
        // 닉네임 2글자 이상 확인 if
        // checkBox 확인 if

        //
    }

    private void emailCheck(View view) {
        //email 중복확인 기능 넣어주기.
    }
}
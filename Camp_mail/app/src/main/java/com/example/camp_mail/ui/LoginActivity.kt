package com.example.camp_mail.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.example.camp_mail.R
import com.example.camp_mail.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private var isValidateNickName = false
    private var isValidateEmail = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNickNameEditText()
        initEmailEditText()
    }

    private fun initNickNameEditText() {
        val etvNickName = binding.etvInputNickName.editText
        etvNickName?.addTextChangedListener {
            if (isValidateNickName(etvNickName?.text.toString())) {
                isValidateNickName = true
                binding.etvInputNickName.error = null
                binding.etvInputNickName.isErrorEnabled = false
            } else {
                isValidateNickName= false
                binding.etvInputNickName.error = "닉네임은 영어,숫자를 결합한 4~12자로 입력해주세요"
            }
            if(isValidateEmail && isValidateNickName){
                binding.btnGoNext.setBackgroundColor(resources.getColor(R.color.purple_500))
            }
            else{
                binding.btnGoNext.setBackgroundColor(resources.getColor(R.color.btnNotClickable))
            }
        }
    }

    private fun isValidateNickName(text: String): Boolean {
        if(text.isEmpty()) return true
        return text.length in 4..12 && Regex("^[a-zA-Z0-9]*\$").matches(text)
    }


    private fun initEmailEditText() {
        val etvEmail = binding.etvInputEmail.editText
        etvEmail?.addTextChangedListener {
            if (isValidateEmail(binding.etvInputEmail.editText?.text.toString())) {
                isValidateEmail = true
                binding.etvInputEmail.error = null
                binding.etvInputEmail.isErrorEnabled = false
            } else {
                isValidateEmail = false
                binding.etvInputEmail.error = "이메일은 @woowahan.com 으로 끝나야합니다."
            }
            if(isValidateEmail && isValidateNickName){
                binding.btnGoNext.setBackgroundColor(resources.getColor(R.color.purple_500))
            }
            else{
                binding.btnGoNext.setBackgroundColor(resources.getColor(R.color.btnNotClickable))
            }
        }
    }

    private fun isValidateEmail(text: String): Boolean {
        if(text.isEmpty()) return true
        return Regex("[0-9|a-z]+@woowahan.com").matches(text)
    }


}
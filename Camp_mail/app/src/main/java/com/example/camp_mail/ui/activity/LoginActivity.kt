package com.example.camp_mail.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.camp_mail.databinding.ActivityLoginBinding
import com.example.camp_mail.model.UserInfoViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding


    private val userInfoViewModel : UserInfoViewModel by viewModels() // 입력 정보 저장할 viewModel

    // 입력된 닉네임, 이메일 타당성 저장할 변수
    private var isValidateNickName = false
    private var isValidateEmail = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.userInfo = userInfoViewModel
        initNickNameEditText()
        initEmailEditText()
        initGoNextButton()
    }

    // Next 버튼
    private fun initGoNextButton() {
        binding.btnGoNext.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userNickName", userInfoViewModel.userNickName.value.toString())
            intent.putExtra("userEmail", userInfoViewModel.userEmail.value.toString())
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    private fun initNickNameEditText() {
        val etvNickName = binding.etvInputNickName.editText
        etvNickName?.addTextChangedListener {
            if (isValidateNickName(etvNickName?.text.toString())) {
                isValidateNickName = etvNickName?.text.toString().isNotEmpty()
                binding.etvInputNickName.error = null
                binding.etvInputNickName.isErrorEnabled = false

            } else {
                isValidateNickName= false
                binding.etvInputNickName.error = "닉네임은 영어,숫자를 결합한 4~12자로 입력해주세요"
            }
            binding.userInfo!!.setUserNickName(etvNickName?.text.toString())
            binding.btnGoNext.isEnabled = isValidateEmail && isValidateNickName
        }
    }


    // 닉네임 타당성 검증
    private fun isValidateNickName(text: String): Boolean {
        if(text.isEmpty()) return true
        return text.length in 4..12 && Regex("^[a-zA-Z0-9]*\$").matches(text)
    }


    private fun initEmailEditText() {
        val etvEmail = binding.etvInputEmail.editText
        etvEmail?.addTextChangedListener {
            if (isValidateEmail(binding.etvInputEmail.editText?.text.toString())) {
                isValidateEmail = etvEmail?.text.toString().isNotEmpty()
                binding.etvInputEmail.error = null
                binding.etvInputEmail.isErrorEnabled = false
            } else {
                isValidateEmail = false
                binding.etvInputEmail.error = "이메일은 @woowahan.com 으로 끝나야합니다."
            }
            binding.userInfo!!.setUserEmail(etvEmail?.text.toString())
            binding.btnGoNext.isEnabled = isValidateEmail && isValidateNickName
        }
    }

    // 이메일 타당성 검증
    private fun isValidateEmail(text: String): Boolean {
        if(text.isEmpty()) return true
        return Regex("[0-9|a-z]+@woowahan.com").matches(text)
    }


}
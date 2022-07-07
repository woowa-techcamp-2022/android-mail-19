package com.example.camp_mail.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserInfoViewModel : ViewModel(){
    val userNickName = MutableLiveData<String>()
    val userEmail = MutableLiveData<String>()

    fun setUserNickName(nickname : String){
        userNickName.value = nickname
    }
    fun setUserEmail(email : String){
        userEmail.value = email
    }

}
package com.example.camp_mail.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.camp_mail.R
import com.example.camp_mail.data.Mail

class MailViewModel : ViewModel() {
    private val everyMailList = mutableListOf(
        Mail("Primary", "2022.05.03", "우아한형제들", "안녕하세요 ?", "우아한형제들 최종합격 !축하드립니다.", null),
        Mail("Social", "2022.02.03", "Alice", "안녕하세요 ?", "저랑 같이 코딩해요 !", null),
        Mail("Social", "2022.04.23", "Bob", "안녕하세요 ?", "저랑 같이 코딩해요 !", null),
        Mail("Promotions", "2022.02.13", "Attacker", "안녕하세요 ?", "당신의 이메일을 해킹하는 중", null),
        Mail("Social", "2022.06.03", "King", "안녕하세요 ?", "저랑 같이 코딩해요 !", null),
        Mail("Social", "2022.12.03", "Lion", "안녕하세요 ?", "저랑 같이 코딩해요 !", null),
        Mail("Primary", "2022.02.14", "Google", "안녕하세요 ?", "Google 최종합격 !축하드립니다.", null),
        Mail("Primary", "2022.04.03", "토스", "안녕하세요 ?", "토스 최종합격 !축하드립니다. !", null),
        Mail("Primary", "2022.02.26", "네이버", "안녕하세요 ?", "네이버 최종합격 !축하드립니다.", null),
        Mail("Social", "2022.02.03", "엄마", "안녕하세요 ?", "이거 보면 전화줘 ~", null),
        Mail("Social", "2022.07.23", "아빠", "안녕하세요 ?", "아들 연락줘 ~~", null),
        Mail("Social", "2022.04.03", "누나", "안녕하세요 ?", "저랑 같이 코딩해요 !", null),
        Mail("Promotions", "2022.07.31", "John", "안녕하세요 ?", "Hi, how are you?", null),
        Mail("Promotions", "2022.02.03", "Tiger", "안녕하세요 ?", "I am a Tiger ", null),
        Mail("Primary", "2022.09.21", "카카오", "안녕하세요 ?", "카카오 최종합격 !축하드립니다.", null),
        Mail("Primary", "2022.02.24", "Nexon", "안녕하세요 ?", "넥슨 최종합격 !축하드립니다.", null),
        Mail("Primary", "2022.09.01", "Liot", "안녕하세요 ?", "LoL 다이아 승급 !", null)
    )

    var mailList = MutableLiveData<MutableList<Mail>>()
    var curState = ""

    init {
        val iconColorList = listOf(
            R.drawable.frame_mail_icon_teal,
            R.drawable.frame_mail_icon_purple,
            R.drawable.frame_mail_icon_yellow
        )
        for (i in 0 until everyMailList.size) {
            everyMailList[i].color = iconColorList[(Math.random() * iconColorList.size).toInt()]
        }

        mailList.value = mutableListOf()
        changeMailListSet("Primary")
    }

    fun changeMailListSet(type: String) {
        curState = type
        Log.d("mail ", everyMailList.toString())
        val newMailList = mutableListOf<Mail>()
        for (mail in everyMailList) {
            if (mail.type == type) {
                newMailList.add(mail)
            }
        }
        mailList.value = newMailList
    }
}
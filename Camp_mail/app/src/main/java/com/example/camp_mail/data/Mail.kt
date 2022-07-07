package com.example.camp_mail.data

data class Mail (
    val type : String,
    val date : String,
    val sender :String,
    val title : String,
    val message : String,
    var color : Int?
)

package com.example.ktapp

open class todoListClass (text: String, date: String){
    var text = text
    var date = date

    val fullData: String
        get() = "$text $date"


}
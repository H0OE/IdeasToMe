package com.jarho.ideasme.model

class FeedModel(){

    var urlImage:String = ""
    var userNick:String = ""
    var userMail:String = ""
    var description:String=""
    var chipNamea:ArrayList<String> = arrayListOf()

    constructor( chipNamea:ArrayList<String>,urlImage:String, userNick:String, userMail:String,   description:String):this() {

        this.chipNamea =chipNamea
        this.urlImage = urlImage
        this.userNick = userNick
        this.userMail = userMail
        this.description = description

    }
}


package com.jarho.ideasme.model

class FeedModel(){

    var urlImage:String = ""
    var userNick:String = ""
    var userMail:String = ""
    var description:String=""

    constructor( urlImage:String, userNick:String, userMail:String,   description:String):this() {

        this.urlImage = urlImage
        this.userNick = userNick
        this.userMail = userMail
        this.description = description

    }
}


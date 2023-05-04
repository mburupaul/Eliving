package com.example.theapplication

class Product {
    var productname:String = ""
    var phoneNumber:String = ""
    var productprice:String = ""
    var productlocation:String = ""
    var id:String = ""
    var image:String = ""

    constructor()
    constructor(
        productname: String,
        phoneNumber: String,
        productprice: String,
        productlocation: String,
        id: String,
        image: String
    ) {
        this.productname = productname
        this.phoneNumber = phoneNumber
        this.productprice = productprice
        this.productlocation = productlocation
        this.id = id
        this.image = image
    }
}
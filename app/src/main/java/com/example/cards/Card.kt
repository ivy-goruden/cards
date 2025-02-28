package com.example.cards

class Card(rotate:Int, color:Int, mast:String, outer:String, inner:String) {

    public var rotate: Int = 0
    public var color = 0
    public var mast = ""
    public var outer = ""
    public var inner = ""


    init {
        this.rotate = rotate
        this.color = color
        this.mast = mast
        this.outer = outer
        this.inner = inner
    }

}
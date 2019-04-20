package com.sperotti.alessandro.beerapp.models

import com.google.gson.annotations.SerializedName

class Beer (@SerializedName("name") var name : String,
    @SerializedName("tagline") var tagline : String,
    @SerializedName("description") var descr : String,
    @SerializedName("image_url") var imgUrl : String,
    @SerializedName("first_brewed") var firstBrewed : String)
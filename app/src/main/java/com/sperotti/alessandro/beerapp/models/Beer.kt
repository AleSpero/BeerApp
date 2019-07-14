package com.sperotti.alessandro.beerapp.models

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

class Beer (@SerializedName("name") var name : String,
    @SerializedName("tagline") var tagline : String,
    @SerializedName("description") var descr : String,
    @SerializedName("image_url") var imgUrl : String?,
    @SerializedName("abv") var abv : String,
    @SerializedName("first_brewed") var firstBrewed : String) {


    //Dice al pagedlistadapter quali elementi sono uguali e quali no
    class DiffUtility : DiffUtil.ItemCallback<Beer>(){
        override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
            return oldItem.name == newItem.name
        }

    }

}
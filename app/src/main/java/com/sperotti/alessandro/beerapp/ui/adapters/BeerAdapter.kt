package com.sperotti.alessandro.beerapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sperotti.alessandro.beerapp.R
import com.sperotti.alessandro.beerapp.models.Beer

class BeerAdapter(val beerList : List<Beer>) : RecyclerView.Adapter<BeerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.beer_item, parent, false)
        return BeerViewHolder(v)
    }

    override fun getItemCount(): Int {
       return beerList.size
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        beerList[position].let { holder.bindBeer(it) }
    }

}

class BeerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    fun bindBeer(beer : Beer){

        val title = itemView.findViewById<TextView>(R.id.title)
        val tagline = itemView.findViewById<TextView>(R.id.tagline)
        val image = itemView.findViewById<ImageView>(R.id.image)

        title.text = beer.name
        tagline.text = beer.tagline

        beer.imgUrl?.let {

            Glide.with(itemView)
                .asDrawable()
                .load(it)
                .into(image)
        }


    }
}
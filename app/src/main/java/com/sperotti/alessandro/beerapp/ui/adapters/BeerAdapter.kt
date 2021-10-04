package com.sperotti.alessandro.beerapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sperotti.alessandro.beerapp.R
import com.sperotti.alessandro.beerapp.models.Beer
import kotlinx.android.synthetic.main.beer_item.view.*

class BeerAdapter(val beers : MutableList<Beer>, val onClick : (Beer) -> Unit) : RecyclerView.Adapter<BeerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.beer_item, parent, false)
        return BeerViewHolder(v, onClick)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bindBeer(beers[position])
    }

    override fun getItemCount(): Int = beers.size

    fun addItems(additionalBeers : List<Beer>){
        beers.addAll(additionalBeers)
        notifyDataSetChanged()
    }

    fun clear(){
        beers.clear()
    }

}

class BeerViewHolder(itemView : View, val onClick : (Beer) -> Unit) : RecyclerView.ViewHolder(itemView){

    val requestOptions = RequestOptions()

    fun bindBeer(beer : Beer){

        val title = itemView.findViewById<TextView>(R.id.title)
        val tagline = itemView.findViewById<TextView>(R.id.tagline)
        val image = itemView.findViewById<ImageView>(R.id.image)
        val abv = itemView.findViewById<TextView>(R.id.abv)
        val firstBrewed = itemView.findViewById<TextView>(R.id.first_brewed)

        title.text = beer.name
        tagline.text = beer.tagline
        abv.text = String.format("%s%%", beer.abv)
        firstBrewed.text = beer.firstBrewed

        requestOptions.placeholder(R.drawable.ic_drink)

        itemView.card_view.setOnClickListener {
            onClick(beer)
        }

        beer.imgUrl?.let {
            Glide.with(itemView)
                .applyDefaultRequestOptions(requestOptions)
                .asDrawable()
                .load(it)
                .into(image)

        }


    }
}
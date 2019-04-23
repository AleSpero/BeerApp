package com.sperotti.alessandro.beerapp.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.sperotti.alessandro.beerapp.R
import com.sperotti.alessandro.beerapp.models.Beer

class BeerAdapter : PagedListAdapter<Beer, BeerViewHolder>(Beer.DiffUtility()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.beer_item, parent, false)
        return BeerViewHolder(v)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
       holder.bindBeer(getItem(position)!!)
    }

}

class BeerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

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

        beer.imgUrl?.let {
            Glide.with(itemView)
                .applyDefaultRequestOptions(requestOptions)
                .asDrawable()
                .load(it)
                .into(image)

        }


    }
}
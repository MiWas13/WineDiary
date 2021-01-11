package ru.miwas.winediary.homelist

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.miwas.winediary.R
import ru.miwas.winediary.homelist.model.WineItem
import java.io.File

class HomeListAdapter : RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>() {

    private var homeListItems = mutableListOf<WineItem>()

    class HomeListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name)
        val imageView: ImageView = view.findViewById(R.id.image)
        val totalRatingBar: RatingBar = view.findViewById(R.id.totalRatingBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.home_list_item, parent, false)

        return HomeListViewHolder(view)
    }

    override fun getItemCount() = homeListItems.size

    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) {
        holder.nameView.text = homeListItems[position].name
        holder.totalRatingBar.rating = homeListItems[position].rateTotal.toFloat()
        homeListItems[position].imagePath?.let {
            holder.imageView.setImageBitmap(BitmapFactory.decodeFile(File(it).absolutePath))
        }
    }

    fun setItems(homeListItems: MutableList<WineItem>) {
        this.homeListItems = homeListItems
        notifyDataSetChanged()
    }
}
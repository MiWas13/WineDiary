package ru.miwas.winediary.homelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.miwas.winediary.R
import ru.miwas.winediary.homelist.model.WineItem
import java.io.File

class HomeListAdapter(
    private val clickListener: HomeItemClickListener
) : RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>() {

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
        val currentItem = homeListItems[position]
        holder.nameView.text = currentItem.name
        holder.totalRatingBar.rating = currentItem.rateTotal.toFloat()
        configureImage(currentItem, holder)
        holder.itemView.setOnClickListener { clickListener.onClick(currentItem.id) }
    }

    fun setItems(homeListItems: MutableList<WineItem>) {
        this.homeListItems = homeListItems
        notifyDataSetChanged()
    }

    private fun configureImage(currentItem: WineItem, holder: HomeListViewHolder) {
        val imagePath = currentItem.imagePath
        if (!imagePath.isNullOrEmpty()) {
            imagePath.let { validPath ->
                with(holder) {
                    Glide
                        .with(itemView)
                        .load(File(validPath))
                        .placeholder(R.drawable.image_placeholder)
                        .into(imageView)
                }
            }
        }
    }
}
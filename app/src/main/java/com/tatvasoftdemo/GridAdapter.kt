package com.tatvasoftdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.grid_item.view.*

/**
 * Created by bhavesh on 09-07-2021.
 */
class GridAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list = listOf<GridModel>()

    fun setGridList(listOfGrids: List<GridModel>) {
        this.list = listOfGrids
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = viewHolder as MovieListViewHolder
        movieViewHolder.bindView(list[position])
    }

    override fun getItemCount(): Int = list.size

    class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(gridModel: GridModel) {
            var color: Int = R.color.white
            if (gridModel.selectedColor == "w")
                color = R.color.white
            else if (gridModel.selectedColor == "r")
                color = R.color.red
            else if (gridModel.selectedColor == "b")
                color = R.color.blue

            itemView.tvCount.setBackgroundColor(ContextCompat.getColor(itemView.context, color))

            itemView.tvCount.setOnClickListener {
                if (gridModel.selectedColor == "r") {
                    gridModel.selectedColor = "b"
                    
                }
            }

            //itemView.tvCount.text = GridModel.count.toString()

            // Glide.with(itemView.context).load(GridModel.moviePicture!!).into(itemView.imageMovie)
        }

    }
}
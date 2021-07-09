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
    private lateinit var listener: CustomListener

    fun setGridList(listOfGrids: List<GridModel>, listener: CustomListener) {
        this.list = listOfGrids
        this.listener = listener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = viewHolder as MovieListViewHolder
        var gridModel = list[position]

        var color: Int = R.color.white
        if (gridModel.selectedColor == "w")
            color = R.color.white
        else if (gridModel.selectedColor == "r")
            color = R.color.red
        else if (gridModel.selectedColor == "b")
            color = R.color.blue

        movieViewHolder.itemView.tvCount.setBackgroundColor(ContextCompat.getColor(movieViewHolder.itemView.context, color))

        movieViewHolder.itemView.tvCount.setOnClickListener {
            listener.onRedClick(position)
        }
    }

    override fun getItemCount(): Int = list.size

    class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    interface CustomListener {
        fun onRedClick(position: Int)
    }
}
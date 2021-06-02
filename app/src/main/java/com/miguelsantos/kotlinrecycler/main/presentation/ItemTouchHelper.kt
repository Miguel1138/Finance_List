package com.miguelsantos.kotlinrecycler.main.presentation

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.miguelsantos.kotlinrecycler.main.model.Asset
import java.util.*

class ItemTouchHelper(
    dragDirs: Int,
    swipeDirs: Int,
    assetAdapter: AssetAdapter
) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    val assets: MutableList<Asset> = assetAdapter.assets
    val adapter = assetAdapter

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val from = viewHolder.adapterPosition
        val to = target.adapterPosition

        Collections.swap(assets, from, to)
        adapter.notifyItemMoved(from, to)
        return true
    }

    // Remove Item
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        with(adapter) {
            assets.removeAt(viewHolder.adapterPosition)
            notifyItemRemoved(viewHolder.adapterPosition)
        }
    }
}
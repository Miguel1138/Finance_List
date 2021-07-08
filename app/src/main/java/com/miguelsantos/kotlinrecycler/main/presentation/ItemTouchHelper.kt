package com.miguelsantos.kotlinrecycler.main.presentation

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.miguelsantos.kotlinrecycler.main.model.Asset
import java.util.*

class ItemTouchHelper(
    dragDirs: Int,
    swipeDirs: Int,
    private val assetAdapter: AssetAdapter,
    private val toolbar: MaterialToolbar
) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    val assets: MutableList<Asset> = assetAdapter.assets

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val from = viewHolder.adapterPosition
        val to = target.adapterPosition

        Collections.swap(assets, from, to)
        assetAdapter.notifyItemMoved(from, to)
        return true
    }

    // Remove Item
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        with(assetAdapter) {
            assets.removeAt(viewHolder.adapterPosition)
            toolbar.title = showFinalBalance(assetAdapter)

            notifyItemRemoved(viewHolder.adapterPosition)
            // Atualiza os indexes do array.
            notifyItemRangeChanged(0, assets.size)
        }
    }

    fun showFinalBalance(assetAdapter: AssetAdapter): String {
        var balance = "0.0".toBigDecimal()
        for (asset in assetAdapter.assets) {
            with(asset) {
                if (isProfit) balance += value
                else balance -= value
            }
        }
        return "Saldo Final R$ $balance"
    }

}
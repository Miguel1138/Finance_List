package com.miguelsantos.kotlinrecycler.main.presentation

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.util.isNotEmpty
import androidx.recyclerview.widget.RecyclerView
import com.miguelsantos.kotlinrecycler.R
import com.miguelsantos.kotlinrecycler.main.model.Asset


class AssetAdapter(val assets: MutableList<Asset>) :
    RecyclerView.Adapter<AssetAdapter.AssetViewHolder>() {

    val selectedItems = SparseBooleanArray()
    private var currentItemPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_item_asset, parent, false)
        return AssetViewHolder(view)
    }

    override fun getItemCount(): Int = assets.size

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        holder.bind(assets[position])

        // Adicionando Clicklistener nos itens da lista
        holder.itemView.setOnClickListener {
            if (selectedItems.isNotEmpty()) onItemClick?.invoke(position)
        }

        // Adicionando LongClickListener nos itens da lista
        holder.itemView.setOnLongClickListener {
            onItemLongClick?.invoke(position)
            return@setOnLongClickListener true
        }

        if (currentItemPosition == position) currentItemPosition = -1
    }

    fun toggleSelection(position: Int) {
        currentItemPosition = position
        if (selectedItems[position, false]) {
            selectedItems.delete(position)
            assets[position].isSelected = false
        } else {
            selectedItems.put(position, true)
            assets[position].isSelected = true
        }

        notifyItemChanged(position)
    }

    fun deleteAssets() {
        assets.removeAll(
            assets.filter { it.isSelected }
        )
        notifyDataSetChanged()
        currentItemPosition = -1
    }

    // Referências dos listeners dos items da lista.
    var onItemClick: ((Int) -> Unit)? = null
    var onItemLongClick: ((Int) -> Unit)? = null

    inner class AssetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(asset: Asset) {
            with(asset) {
                itemView.findViewById<ImageView>(R.id.item_asset_image).setImageResource(
                    if (isProfit)
                        R.drawable.ic_baseline_arrow_upward_24
                    else
                        R.drawable.ic_baseline_arrow_downward_24
                )
                itemView.findViewById<TextView>(R.id.item_asset_text_name).text = name
                itemView.findViewById<TextView>(R.id.item_asset_text_date).text = date
                itemView.findViewById<TextView>(R.id.item_asset_text_value).text = value
            }

            if (asset.isSelected) {
                itemView.findViewById<ConstraintLayout>(R.id.main_item_asset).background =
                    GradientDrawable().apply {
                        shape = GradientDrawable.RECTANGLE
                        cornerRadius = 32f
                        setColor(Color.rgb(232, 240, 253))
                    }
            } else {
                itemView.findViewById<ConstraintLayout>(R.id.main_item_asset).background =
                    GradientDrawable().apply {
                        shape = GradientDrawable.RECTANGLE
                        cornerRadius = 32f
                        setColor(Color.WHITE)
                    }
            }
        }

    }

}
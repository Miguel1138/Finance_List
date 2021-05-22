package com.miguelsantos.kotlinrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miguelsantos.kotlinrecycler.model.Asset

class AssetAdapter(val assets: MutableList<Asset>) :
    RecyclerView.Adapter<AssetAdapter.AssetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.main_item_asset, parent, false)
        return AssetViewHolder(view)
    }

    override fun getItemCount(): Int = assets.size

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        holder.bind(assets[position])
    }

    inner class AssetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(asset: Asset) {
            with(asset) {
                var txtName: TextView = itemView.findViewById(R.id.item_asset_text_name)
                txtName.text = name

                var txtDate: TextView = itemView.findViewById(R.id.item_asset_text_date)
                txtDate.text = date

                var imageIcon: ImageView = itemView.findViewById(R.id.item_asset_image)
                imageIcon.setImageResource(
                    if (icon)
                        R.drawable.ic_baseline_arrow_upward_24
                    else
                        R.drawable.ic_baseline_arrow_downward_24
                )


                var txtValue: TextView = itemView.findViewById(R.id.item_asset_text_value)
                txtValue.text = value
            }
        }

    }

}
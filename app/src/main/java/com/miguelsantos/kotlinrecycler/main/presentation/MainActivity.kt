package com.miguelsantos.kotlinrecycler.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.miguelsantos.kotlinrecycler.R
import com.miguelsantos.kotlinrecycler.main.model.asset
import com.miguelsantos.kotlinrecycler.main.model.fakeAssets
import com.mooveit.library.Fakeit
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var assetAdapter: AssetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Fakeit.init()

        assetAdapter =
            AssetAdapter(
                fakeAssets()
            )

        main_recycler.adapter = assetAdapter
        main_recycler.layoutManager = LinearLayoutManager(this)

        main_fab_create_asset.setOnClickListener {
            // TODO: 25/05/2021 Criar um layout formulário para o item.
            addAsset()
            // Scroll para o fim do array
            main_recycler.scrollToPosition(assetAdapter.assets.size - 1)
        }

        val helper = androidx.recyclerview.widget.ItemTouchHelper(
            ItemTouchHelper(
                androidx.recyclerview.widget.ItemTouchHelper.UP or androidx.recyclerview.widget.ItemTouchHelper.DOWN,
                androidx.recyclerview.widget.ItemTouchHelper.LEFT,
                assetAdapter
            )
        )

        helper.attachToRecyclerView(main_recycler)
    }


    private fun addAsset() {
        assetAdapter.assets.add(
            asset {
                icon = (0..1).random() == 0
                name = Fakeit.name().title()
                value = "R$ ${(1..100).random()}"
                date = "${(1..31).random()}/${(1..12).random()}/2021"
            }
        )
        assetAdapter.notifyDataSetChanged()
    }


}
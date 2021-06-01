package com.miguelsantos.kotlinrecycler.main.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miguelsantos.kotlinrecycler.R
import com.miguelsantos.kotlinrecycler.main.model.asset
import com.miguelsantos.kotlinrecycler.main.model.fakeAssets
import com.mooveit.library.Fakeit

class MainActivity : AppCompatActivity() {

    private lateinit var mainRecycler: RecyclerView
    private lateinit var fabCreateAsset: FloatingActionButton
    private lateinit var assetAdapter: AssetAdapter

    private var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Fakeit.init()

        assetAdapter = AssetAdapter(fakeAssets())

        mainRecycler = findViewById(R.id.main_recycler)
        mainRecycler.adapter = assetAdapter
        mainRecycler.layoutManager = LinearLayoutManager(this)

        fabCreateAsset = findViewById(R.id.main_fab_create_asset)
        fabCreateAsset.setOnClickListener {
            addAsset()
            // Scroll para o fim do array
            mainRecycler.scrollToPosition(assetAdapter.assets.size - 1)
        }

        val helper = androidx.recyclerview.widget.ItemTouchHelper(
            ItemTouchHelper(0, androidx.recyclerview.widget.ItemTouchHelper.LEFT, assetAdapter)
        )

        helper.attachToRecyclerView(mainRecycler)
        assetAdapter.onItemClick = {
            enableActionMode(it)
        }

        assetAdapter.onItemLongClick = {
            enableActionMode(it)

        }

    }

    private fun enableActionMode(position: Int) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(object : ActionMode.Callback {

                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    mode?.menuInflater?.inflate(R.menu.menu_delete_items, menu)
                    return true
                }

                override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    return false
                }

                override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                    when (item?.itemId) {
                        R.id.menu_delete -> {
                            assetAdapter.deleteAssets()
                            mode?.finish()
                            return true
                        }
                    }
                    return false
                }

                override fun onDestroyActionMode(mode: ActionMode?) {
                    assetAdapter.selectedItems.clear()
                    assetAdapter.assets
                        .filter { it.isSelected }
                        .forEach { it.isSelected = false }

                    assetAdapter.notifyDataSetChanged()
                    actionMode = null
                }

            })
        }

        assetAdapter.toggleSelection(position)
        val size: Int = assetAdapter.selectedItems.size()
        if (size == 0) {
            actionMode?.finish()
        } else {
            actionMode?.title = "Itens selecionados $size"
            actionMode?.invalidate()
        }

    }

    private fun addAsset() {
        assetAdapter.assets.add(
            asset {
                isProfit = (0..1).random() == 0
                name = Fakeit.name().title()
                value = "R$ ${(1..100).random()}"
                date = "${(1..31).random()}/${(1..12).random()}/2021"
            }
        )
        assetAdapter.notifyDataSetChanged()
    }

}
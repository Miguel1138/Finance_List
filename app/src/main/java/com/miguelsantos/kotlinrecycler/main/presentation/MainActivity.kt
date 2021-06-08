package com.miguelsantos.kotlinrecycler.main.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miguelsantos.kotlinrecycler.R
import com.miguelsantos.kotlinrecycler.main.model.asset
import com.miguelsantos.kotlinrecycler.main.model.fakeAssets
import com.mooveit.library.Fakeit

class MainActivity : AppCompatActivity() {

    private lateinit var mainRecycler: RecyclerView
    private lateinit var fabCreateAsset: FloatingActionButton
    private lateinit var assetAdapter: AssetAdapter
    private lateinit var toolbar: MaterialToolbar
    private var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Fakeit.init()
        setRecyclerView()

        fabCreateAsset = findViewById(R.id.main_fab_create_asset)
        fabCreateAsset.setOnClickListener { addAsset() }

        toolbar = findViewById(R.id.main_toolbar)

        val helper = androidx.recyclerview.widget.ItemTouchHelper(
            ItemTouchHelper(
                0,
                androidx.recyclerview.widget.ItemTouchHelper.LEFT,
                assetAdapter,
                toolbar
            )
        )
        helper.attachToRecyclerView(mainRecycler)
    }

    override fun onResume() {
        super.onResume()
        toolbar.title =
            String.format(resources.getString(R.string.final_balance), showFinalBalance())
        setSupportActionBar(toolbar)
    }

    private fun setRecyclerView() {
        mainRecycler = findViewById(R.id.main_recycler)
        assetAdapter = AssetAdapter(fakeAssets(), this)

        with(mainRecycler) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = assetAdapter

        }

        assetAdapter.onItemClick = { enableActionMode(it) }
        assetAdapter.onItemLongClick = { enableActionMode(it) }

    }

    // Action Mode e toolbar de delete
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
                    with(assetAdapter) {
                        selectedItems.clear()
                        assets.filter { it.isSelected }
                            .forEach { it.isSelected = false }
                        notifyDataSetChanged()
                    }
                    actionMode = null
                }
            })
        }

        assetAdapter.toggleSelection(position)
        val size: Int = assetAdapter.selectedItems.size()
        if (size == 0) {
            actionMode?.finish()
        } else {
            actionMode?.title = String.format(resources.getString(R.string.selected_items, size))
            actionMode?.invalidate()
        }
    }

    private fun addAsset() {
        with(assetAdapter) {
            assets.add(
                asset {
                    isProfit = (0..1).random() == 0
                    name = Fakeit.name().title()
                    value = "${(1..100).random()}".toBigDecimal()
                    date = "${(1..31).random()}/${(1..12).random()}/2021"
                }
            )
            notifyDataSetChanged()
            // Scroll to the end of the array
            mainRecycler.scrollToPosition(assetAdapter.assets.size - 1)
        }

        toolbar.title = String.format(
            resources.getString(R.string.final_balance), showFinalBalance()
        )
    }

    // Toolbar title
    fun showFinalBalance(): String {
        var balance = "0.0".toBigDecimal()
        for (asset in assetAdapter.assets) {
            with(asset) {
                if (isProfit)
                    balance += value
                else
                    balance -= value
            }
        }

        return balance.toString()
    }

}
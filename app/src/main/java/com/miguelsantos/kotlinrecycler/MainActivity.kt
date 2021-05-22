package com.miguelsantos.kotlinrecycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miguelsantos.kotlinrecycler.model.fakeAssets

class MainActivity : AppCompatActivity() {

    lateinit var recyclerMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerMain = findViewById(R.id.main_recycler)
        recyclerMain.adapter = AssetAdapter(fakeAssets())
        recyclerMain.layoutManager = LinearLayoutManager(this)
    }


}
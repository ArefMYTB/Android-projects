package com.example.my2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
//
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    private lateinit var myAdapter: AdapterClass
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    lateinit var descList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageList = arrayOf(
            R.drawable.consent,
            R.drawable.creepyoldguy,
            R.drawable.dagger,
            R.drawable.deviate,
            R.drawable.greensoldier
        )

        titleList = resources.getStringArray(R.array.Books)
        descList = resources.getStringArray(R.array.Description)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataClass>()
        getData()
//
        myAdapter = AdapterClass(dataList)
        recyclerView.adapter = myAdapter

        myAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("android", it)
            startActivity(intent)
        }

    }

    private fun getData(){
        for(i in imageList.indices) { //
            val dataClass = DataClass(imageList[i], titleList[i], descList[i], imageList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = AdapterClass(dataList)
    }




//    public fun openDialog() {
//        val builder = AlertDialog.Builder(this)
//        //set title for alert dialog
//        builder.setTitle(R.string.dialogTitle)
//        //set message for alert dialog
//        builder.setMessage(R.string.dialogMessage)
//        // Create the AlertDialog
//        val alertDialog: AlertDialog = builder.create()
//        // Set other dialog properties
//        alertDialog.setCancelable(true)
//        alertDialog.show()
//    }
}
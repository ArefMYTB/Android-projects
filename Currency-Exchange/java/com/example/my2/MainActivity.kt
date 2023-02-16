package com.example.my2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
//
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    private lateinit var myAdapter: AdapterClass
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>

    lateinit var viewm: viewModel


//    private val ac_america = findViewById(R.id.a_america) as EditText
//    private val ac_canada = findViewById(R.id.a_canada) as EditText
//    private val ac_emarat = findViewById(R.id.a_emarat) as EditText
//    private val ac_iran = findViewById(R.id.a_iran) as EditText
//    private val ac_english = findViewById(R.id.a_english) as EditText
//    private val ac_euro = findViewById(R.id.a_euro) as EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewm = ViewModelProvider(this).get(viewModel::class.java)

        imageList = arrayOf(
            R.drawable.america,
            R.drawable.canada,
            R.drawable.emarat,
            R.drawable.iran,
            R.drawable.english,
            R.drawable.euro
        )

        titleList = resources.getStringArray(R.array.Countries)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataClass>()
        getData()
//
        myAdapter = AdapterClass(dataList)
        recyclerView.adapter = myAdapter


//        myAdapter.onItemClick = {
//            val tv = findViewById(R.id.textView2) as TextView
//            tv.setText("ch: ")
//
//        }

        exchange()

    }

    private fun getData(){
        for(i in imageList.indices) { //
            val dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = AdapterClass(dataList)
    }

//    American Dollar is equal to:
//    1.36 Canadian Dollar
//    3.67 Dirham
//    42350 Rial
//    1.34 Pound
//    0.95 Euro

    private fun exchange(){
        val ac_america = findViewById<EditText>(R.id.a_america)
        val ac_canada = findViewById<EditText>(R.id.a_canada)
        val ac_emarat = findViewById<EditText>(R.id.a_emarat)
        val ac_iran = findViewById<EditText>(R.id.a_iran)
        val ac_english = findViewById<EditText>(R.id.a_english)
        val ac_euro = findViewById<EditText>(R.id.a_euro)

        var doll = 0.0

        ac_america.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                doll = s.toString().toDouble()
                val (c,d,r,en,eu) = multi(doll)
                ac_canada.setText(c.toString())
                ac_emarat.setText(d.toString())
                ac_iran.setText(r.toString())
                ac_english.setText(en.toString())
                ac_euro.setText(eu.toString())
            }
            override fun beforeTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {

            }
        })

        ac_canada.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                doll = s.toString().toDouble()*1.36
                val (c1,d1,r1,en1,eu1) = multi(doll)
                ac_america.setText(doll.toString())
                ac_emarat.setText(d1.toString())
                ac_iran.setText(r1.toString())
                ac_english.setText(en1.toString())
                ac_euro.setText(eu1.toString())
            }
            override fun beforeTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {

            }
        })

        ac_emarat.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                doll = s.toString().toDouble()*3.67
                val (c2,d2,r2,en2,eu2) = multi(doll)
                ac_america.setText(doll.toString())
                ac_canada.setText(c2.toString())
                ac_iran.setText(r2.toString())
                ac_english.setText(en2.toString())
                ac_euro.setText(eu2.toString())
            }
            override fun beforeTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {

            }
        })

        ac_iran.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                doll = s.toString().toDouble()*42350
                val (c3,d3,r3,en3,eu3) = multi(doll)
                ac_america.setText(doll.toString())
                ac_emarat.setText(d3.toString())
                ac_canada.setText(c3.toString())
                ac_english.setText(en3.toString())
                ac_euro.setText(eu3.toString())
            }
            override fun beforeTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {

            }
        })

        ac_english.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                doll = s.toString().toDouble()*1.34
                val (c4,d4,r4,en4,eu4) = multi(doll)
                ac_america.setText(doll.toString())
                ac_emarat.setText(d4.toString())
                ac_iran.setText(r4.toString())
                ac_canada.setText(c4.toString())
                ac_euro.setText(eu4.toString())
            }
            override fun beforeTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {

            }
        })

        ac_euro.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                doll = s.toString().toDouble()*0.95
                val (c5,d5,r5,en5,eu5) = multi(doll)
                ac_america.setText(doll.toString())
                ac_emarat.setText(d5.toString())
                ac_iran.setText(r5.toString())
                ac_english.setText(en5.toString())
                ac_canada.setText(c5.toString())
            }
            override fun beforeTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

    private fun multi(doll: Double): Array<Any>{
        val cc = doll*1.36
        val dd = doll*3.67
        val rr = doll*42350
        val enn = doll*1.34
        val euu = doll*0.95
        return arrayOf(cc,dd,rr,enn,euu)
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
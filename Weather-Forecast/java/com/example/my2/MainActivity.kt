package com.example.my2

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    var CITY: String = "Tehran"
    val API: String = "06c921750b9a82d8f5d1294e1586276f" // Use API key
//    var selectedProvince: String = ""
//    var selectedCity: String = ""
    private lateinit var tvProvince: TextView
    private lateinit var tvCity: TextView
    private lateinit var provinceSpinner: Spinner
    private lateinit var citySpinner: Spinner
    private lateinit var provinceAdapter: ArrayAdapter<CharSequence>
    private lateinit var cityAdapter: ArrayAdapter<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvProvince = findViewById(R.id.textView_province)
        tvCity = findViewById(R.id.textView_city)
        provinceSpinner = findViewById(R.id.spinner_province)
        citySpinner = findViewById(R.id.spinner_city)

        provinceAdapter= ArrayAdapter.createFromResource(this,
        R.array.array_Iranian_province, R.layout.spinner_layout)

        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        provinceSpinner.adapter = provinceAdapter

        provinceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedProvince = parent.getItemAtPosition(position).toString()
//
                when(selectedProvince){
                    "Tehran" -> cityAdapter = ArrayAdapter.createFromResource(parent.context,
                        R.array.array_Tehran_city, R.layout.spinner_layout)
                    "Yazd" -> cityAdapter = ArrayAdapter.createFromResource(parent.context,
                        R.array.array_Yazd_city, R.layout.spinner_layout)
                    "Esfahan" -> cityAdapter = ArrayAdapter.createFromResource(parent.context,
                        R.array.array_Esfahan_city, R.layout.spinner_layout)
                    "Kerman" -> cityAdapter = ArrayAdapter.createFromResource(parent.context,
                        R.array.array_Kerman_city, R.layout.spinner_layout)
                    "Fars" -> cityAdapter = ArrayAdapter.createFromResource(parent.context,
                        R.array.array_Fars_city, R.layout.spinner_layout)
                    else -> cityAdapter = ArrayAdapter.createFromResource(parent.context,
                        R.array.array_default_city, R.layout.spinner_layout)
                }
                cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                citySpinner.adapter = cityAdapter

                citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                        val selectedCity = parent.getItemAtPosition(position).toString()
                        CITY = selectedCity


//                        val nwi = weatherTask()
//                        nwi.execute()
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        } // to close the onItemSelected

        val showButton = findViewById<Button>(R.id.showB)
        showButton.setOnClickListener{
            weatherTask().execute()
        }

        val currentLocation = findViewById<Button>(R.id.nextB)
        currentLocation.setOnClickListener {

        }


        weatherTask().execute()

    }



    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            /* Showing the ProgressBar, Making the main design GONE */
            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
            findViewById<TextView>(R.id.errorText).visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY,IR&units=metric&appid=$API").readText(
                    Charsets.UTF_8
                )
            }catch (e: Exception){
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt*1000))
                val temp = main.getString("temp")+"°C"
                val tempMin = "Min Temp: " + main.getString("temp_min")+"°C"
                val tempMax = "Max Temp: " + main.getString("temp_max")+"°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise:Long = sys.getLong("sunrise")
                val sunset:Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")

                val address = jsonObj.getString("name")+", "+sys.getString("country")

                /* Populating extracted data into our views */
                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.updated_at).text =  updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.temp_min).text = tempMin
                findViewById<TextView>(R.id.temp_max).text = tempMax
                findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise*1000))
                findViewById<TextView>(R.id.sunset).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset*1000))
                findViewById<TextView>(R.id.wind).text = windSpeed
                findViewById<TextView>(R.id.pressure).text = pressure
                findViewById<TextView>(R.id.humidity).text = humidity

                /* Views populated, Hiding the loader, Showing the main design */
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE

            } catch (e: Exception) {
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
            }

        }
    }
}
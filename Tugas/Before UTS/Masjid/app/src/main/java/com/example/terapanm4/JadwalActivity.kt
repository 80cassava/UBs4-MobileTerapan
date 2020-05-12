package com.example.terapanm4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_jadwal.*
import org.json.JSONArray
import org.json.JSONObject

class JadwalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        getData()
    }

    fun getData() {
        AndroidNetworking.get("http://$ip/mobter/masjid/jadwal.php")
            .setPriority(Priority.MEDIUM).build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("kotlinResponse", response.toString())
                    val jsonArray: JSONArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                        Log.e("Shubuh", jsonObject.optString("shubuh"))
                        Log.e("Dhuhur", jsonObject.optString("dhuhur"))
                        Log.e("Ashar", jsonObject.optString("ashar"))
                        Log.e("Maghrib", jsonObject.optString("maghrib"))
                        Log.e("Isha", jsonObject.optString("isha"))
                        Log.e("Dhuha", jsonObject.optString("dhuha"))

                        textShubuh.text = jsonObject.optString("shubuh")
                        textDhuhur.text = jsonObject.optString("dhuhur")
                        textAshar.text = jsonObject.optString("ashar")
                        textMaghrib.text = jsonObject.optString("maghrib")
                        textIsha.text = jsonObject.optString("isha")
                        textDhuha.text = jsonObject.optString("dhuha")
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }
}

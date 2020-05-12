package com.example.terapanm4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONArray
import org.json.JSONObject

class SlideshowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slideshow)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        getData()
    }

    @SuppressLint("WrongConstant")
    fun getData() {
        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val slides = ArrayList<Slideshow>()
        AndroidNetworking.get("http://$ip/mobter/masjid/slideshow.php")
            .setPriority(Priority.MEDIUM).build().getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("kotlinResponse", response.toString())
                    val jsonArray: JSONArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
//                        Log.e("Judul", jsonObject.optString("judul_slideshow"))
//                        Log.e("URL", jsonObject.optString("url_slideshow"))
                        var isi1 = jsonObject.optString("judul_slideshow").toString()
                        var isi2 = jsonObject.optString("url_slideshow").toString()

                        slides.add(Slideshow("$isi1", "$isi2"))
//
//                        textNama.setText("Nama Masjid : " + jsonObject.optString("nama_masjid"))
//                        textAlamat.setText("Alamat :\n" + jsonObject.optString("alamat_masjid"))
                    }
                    val adapter = SlideshowAdapter(slides)
                    recyclerView.adapter = adapter
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }
}

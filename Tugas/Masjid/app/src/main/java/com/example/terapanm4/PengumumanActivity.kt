package com.example.terapanm4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_pengumuman.*
import org.json.JSONArray
import org.json.JSONObject

class PengumumanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        getData()

        btnSimPengumuman.setOnClickListener {
            val judul = editJudul.text.toString()
            val isi = editIsi.text.toString()
            updateData(judul, isi)
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun getData() {
        AndroidNetworking.get("http://$ip/mobter/masjid/pengumuman.php")
            .setPriority(Priority.MEDIUM).build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("kotlinResponse", response.toString())
                    val jsonArray: JSONArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                        Log.e("Judul", jsonObject.optString("judul_pengumuman"))
                        Log.e("Isi", jsonObject.optString("isi_pengumuman"))

                        textJudul.setText(jsonObject.optString("judul_pengumuman"))
                        textIsi.setText(jsonObject.optString("isi_pengumuman"))

                        editJudul.setText(jsonObject.optString("judul_pengumuman"))
                        editIsi.setText(jsonObject.optString("isi_pengumuman"))
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun updateData(data1: String, data2: String) {
        AndroidNetworking.post("http://$ip/mobter/masjid/pengumuman_update.php")
            .addBodyParameter("judul_pengumuman", data1)
            .addBodyParameter("isi_pengumuman", data2)
            .setPriority(Priority.MEDIUM).build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }
}

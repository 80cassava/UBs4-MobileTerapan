package com.example.terapanm6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_berita.*
import kotlinx.android.synthetic.main.list_berita.*
import org.json.JSONArray
import org.json.JSONObject

class BeritaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_berita)

        btn_save.setOnClickListener {
            val nama = editTextJudul.text.toString()
            val nomor = editTextWaktu.text.toString()
            val alamat = editTextPenulis.text.toString()
            val isi = editTextIsi.text.toString()
            postServer(nama, nomor, alamat, isi)
            startActivity(Intent(this, DashboardActivity::class.java))
        }
    }

    fun postServer(data1: String, data2: String, data3: String, data4: String) {
        AndroidNetworking.post("http://$ip/mobter/berita/berita_create.php")
            .addBodyParameter("judul_berita", data1)
            .addBodyParameter("waktu_berita", data2)
            .addBodyParameter("penulis_berita", data3)
            .addBodyParameter("isi_berita", data4)
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

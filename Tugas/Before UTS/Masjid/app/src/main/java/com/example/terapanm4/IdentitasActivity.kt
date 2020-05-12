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
import kotlinx.android.synthetic.main.activity_identitas.*
import org.json.JSONArray
import org.json.JSONObject

class IdentitasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identitas)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        getData()

        btnSimIdentitas.setOnClickListener {
            val nama = editNama.text.toString()
            val alamat = editAlamat.text.toString()
            updateData(nama, alamat)
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun getData() {
        AndroidNetworking.get("http://$ip/mobter/masjid/identitas.php")
            .setPriority(Priority.MEDIUM).build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("kotlinResponse", response.toString())
                    val jsonArray: JSONArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                        Log.e("Nama", jsonObject.optString("nama_masjid"))
                        Log.e("Alamat", jsonObject.optString("alamat_masjid"))

                        textNama.text = jsonObject.optString("nama_masjid")
                        textAlamat.text = jsonObject.optString("alamat_masjid")
                        editNama.setText(jsonObject.optString("nama_masjid"))
                        editAlamat.setText(jsonObject.optString("alamat_masjid"))
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun updateData(data1: String, data2: String) {
        AndroidNetworking.post("http://$ip/mobter/masjid/identitas_update.php")
            .addBodyParameter("nama_masjid", data1)
            .addBodyParameter("alamat_masjid", data2)
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

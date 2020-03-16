package com.example.terapanm6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_mahasiswa.*
import org.json.JSONArray

val ip = "192.168.1.10"

class MahasiswaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mahasiswa)

        btn_save.setOnClickListener {
            val nama = editTextNama.text.toString()
            val nomor = editTextNomor.text.toString()
            val alamat = editTextAlamat.text.toString()
            postServer(nama, nomor, alamat)
            Log.i("kontol",nama+nomor+alamat)
            startActivity(Intent(this, DashboardActivity::class.java))
        }
    }

    fun postServer(data1: String, data2: String, data3: String) {
        Log.i("kontol",data1+data2+data3)
        AndroidNetworking.post("http://$ip/mobter/mahasiswa_proses.php")
            .addBodyParameter("nama_mahasiswa", data1)
            .addBodyParameter("nomer_mahasiswa", data2)
            .addBodyParameter("alamat_mahasiswa", data3)
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

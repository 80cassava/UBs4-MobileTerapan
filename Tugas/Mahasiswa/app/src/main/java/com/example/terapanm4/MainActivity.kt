package com.example.terapanm4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

val ip = "192.168.1.8"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context: MainActivity = this

        cardJadwal.setOnClickListener {
            startActivity(Intent(context, JadwalActivity::class.java))
        }
        cardIdentitas.setOnClickListener {
            startActivity(Intent(this, IdentitasActivity::class.java))
        }
        cardMarquee.setOnClickListener {
            startActivity(Intent(this, MarqueeActivity::class.java))
        }
        cardPengumuman.setOnClickListener {
            startActivity(Intent(this, PengumumanActivity::class.java))
        }
        cardSlideshow.setOnClickListener {
            startActivity(Intent(this, SlideshowActivity::class.java))
        }
        cardTagline.setOnClickListener {
            startActivity(Intent(this, TaglineActivity::class.java))
        }
    }
}

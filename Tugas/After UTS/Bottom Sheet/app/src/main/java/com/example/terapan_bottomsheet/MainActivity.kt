package com.example.terapan_bottomsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    var mSheetDialog: BottomSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showSheetDialog(view: View) {
        val sheetLayout = layoutInflater.inflate(R.layout.sheet_dialog, null)
        mSheetDialog= BottomSheetDialog(this)
        mSheetDialog!!.setContentView(sheetLayout)
        mSheetDialog!!.show()
    }
}

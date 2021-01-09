package com.example.whatsappopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var num : String = "0";
        if(intent.action == Intent.ACTION_PROCESS_TEXT) {
            num = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
            if (num.isDigitsOnly()) {
                startWhatsapp(num)
            } else {
                Toast.makeText(this, "Not a Valid Number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startWhatsapp(num:String) {
        val i = Intent()
        i.action = Intent.ACTION_VIEW
        i.setPackage("com.whatsapp")
        val data:String =  if(num[0] == '+'){
                        num.substring(1)
                    }
                    else if(num.length == 10){
                        "91"+num
                    }
                    else {
                        num
                    }
        i.data = Uri.parse("https://wa.me/$data")
        if(packageManager.resolveActivity(i,0) != null){
            startActivity(i)
        }
        else{
            Toast.makeText(this,"WhatsApp Not Installed on the Device",Toast.LENGTH_SHORT).show()
        }
        finish()
    }
}


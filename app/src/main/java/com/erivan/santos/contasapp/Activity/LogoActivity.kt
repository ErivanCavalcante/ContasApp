package com.erivan.santos.contasapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.erivan.santos.contasapp.R

class LogoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)

        var handler = Handler()
        handler.postDelayed({
            var it = Intent(this, LoginActivity::class.java);
            startActivity(it)

            finish()

        }, 2000)
    }
}

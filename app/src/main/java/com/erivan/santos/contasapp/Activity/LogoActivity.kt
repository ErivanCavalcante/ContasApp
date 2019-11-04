package com.erivan.santos.contasapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.erivan.santos.contasapp.R
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_logo)
open class LogoActivity : AppCompatActivity() {

    @AfterViews
    fun setupViews() {
        var handler = Handler()

        handler.postDelayed({
            LoginActivity_.intent(this).start()

            finish()

        }, 2000)
    }
}

package com.erivan.santos.contasapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erivan.santos.contasapp.POJO.Conta
import com.erivan.santos.contasapp.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_lista)
open class ListaActivity : AppCompatActivity() {
    val lista = ItemAdapter<Conta>()
    val adapter = FastAdapter.with(lista)

    @Click(R.id.fabAdd)
    fun addConta() {
        AddContaActivity_.intent(this).start()
        finish()
    }
}

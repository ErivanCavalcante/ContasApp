package com.erivan.santos.contasapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.erivan.santos.contasapp.R
import kotlinx.android.synthetic.main.activity_add_conta.*
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.OptionsItem
import org.androidannotations.annotations.OptionsMenu

@EActivity(R.layout.activity_add_conta)
@OptionsMenu(R.menu.menu_ok)
open class AddContaActivity : AppCompatActivity() {

    override fun onBackPressed() {
        ListaActivity_.intent(this).start()
        finish()
    }

    @AfterViews
    fun setupViews() {
        edtPeriodicidade.isEnabled = false
        edtQtdParcelas.isEnabled = false

        cbRecorrente.setOnCheckedChangeListener{view, isChecked ->
            edtPeriodicidade.isEnabled = isChecked
            edtQtdParcelas.isEnabled = isChecked

            //Mostra os campos q dependem desse check
            /*if (!isChecked) {
                edtPeriodicidade.text = ""
                edtQtdParcelas.text = ""
            }*/
        }
    }

    @OptionsItem(R.id.menu_check)
    fun salvar() {

    }
}

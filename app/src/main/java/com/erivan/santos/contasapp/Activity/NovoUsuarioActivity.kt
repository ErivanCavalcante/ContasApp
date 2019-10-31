package com.erivan.santos.contasapp.Activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.erivan.santos.contasapp.Presenter.UsuarioPresenter
import com.erivan.santos.contasapp.R
import com.erivan.santos.contasapp.View.UsuarioView
import net.grandcentrix.thirtyinch.TiActivity
import kotlinx.android.synthetic.main.activity_novo_usuario.*

class NovoUsuarioActivity : TiActivity<UsuarioPresenter, UsuarioView>(), UsuarioView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_usuario)

        var tipos = arrayOf("Masculino", "Feminino")
        var adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tipos)

        spSexo.adapter = adapter
    }

    override fun providePresenter(): UsuarioPresenter {
        return UsuarioPresenter()
    }

    override fun loginOk() {

    }

    override fun adicionado() {

        finish()
    }

    override fun erro(code: Int, erro: String) {
        Toast.makeText(this, erro, Toast.LENGTH_SHORT).show()
    }



    override fun onBackPressed() {
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ok, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_check -> presenter.adicionar()
            else -> super.onOptionsItemSelected(item)
        }

        return true
    }
}

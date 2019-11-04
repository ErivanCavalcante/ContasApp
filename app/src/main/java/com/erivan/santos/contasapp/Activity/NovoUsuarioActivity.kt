package com.erivan.santos.contasapp.Activity

import android.widget.ArrayAdapter
import android.widget.Toast
import com.erivan.santos.contasapp.POJO.Usuario
import com.erivan.santos.contasapp.Presenter.UsuarioPresenter
import com.erivan.santos.contasapp.R
import com.erivan.santos.contasapp.View.UsuarioView
import net.grandcentrix.thirtyinch.TiActivity
import kotlinx.android.synthetic.main.activity_novo_usuario.*
import org.androidannotations.annotations.*

@EActivity(R.layout.activity_novo_usuario)
@OptionsMenu(R.menu.menu_ok)
open class NovoUsuarioActivity : TiActivity<UsuarioPresenter, UsuarioView>(), UsuarioView {

    @AfterViews
    fun setupViews() {
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
        LoginActivity_.intent(this).start()
        finish()
    }

    override fun erro(code: Int, erro: String) {
        Toast.makeText(this, erro, Toast.LENGTH_SHORT).show()
    }



    override fun onBackPressed() {
        LoginActivity_.intent(this).start()
        finish()
    }

    @OptionsItem(R.id.menu_check)
    fun menuOk() {
        //Valida td

        //Cria o usuario
        val usuario = Usuario(edtNome.text.toString(), edtEmail.text.toString(), edtSenha.text.toString(), edtTelefone.text.toString(), spSexo.selectedItemPosition)

        //Adiciona
        presenter.adicionar(usuario)
    }
}

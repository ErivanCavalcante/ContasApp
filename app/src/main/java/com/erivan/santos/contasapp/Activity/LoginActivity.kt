package com.erivan.santos.contasapp.Activity

import android.content.Intent
import android.widget.Toast
import com.erivan.santos.contasapp.MainActivity
import com.erivan.santos.contasapp.Presenter.UsuarioPresenter
import com.erivan.santos.contasapp.R
import com.erivan.santos.contasapp.View.UsuarioView
import kotlinx.android.synthetic.main.activity_login.*;
import net.grandcentrix.thirtyinch.TiActivity
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_login)
open class LoginActivity : TiActivity<UsuarioPresenter, UsuarioView>(), UsuarioView {

    @Click(R.id.btLogin)
    fun clickLoginBotao() {
        presenter.logar(edtEmail.text.toString(), edtSenha.text.toString())
    }

    @Click(R.id.btNovaConta)
    fun clickLoginNovaConta() {
        NovoUsuarioActivity_.intent(this).start()
        finish()
    }

    override fun loginOk() {
        val it = Intent(this, MainActivity::class.java)
        startActivity(it)
    }

    override fun adicionado() {

    }

    override fun erro(code: Int, erro: String) {
        Toast.makeText(this, erro, Toast.LENGTH_SHORT).show()
    }


    override fun providePresenter(): UsuarioPresenter {
        return UsuarioPresenter()
    }

}

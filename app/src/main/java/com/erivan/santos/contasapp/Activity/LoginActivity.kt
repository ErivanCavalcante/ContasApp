package com.erivan.santos.contasapp.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.erivan.santos.contasapp.MainActivity
import com.erivan.santos.contasapp.Presenter.LoginPresenter
import com.erivan.santos.contasapp.Presenter.UsuarioPresenter
import com.erivan.santos.contasapp.R
import com.erivan.santos.contasapp.View.BaseView
import com.erivan.santos.contasapp.View.UsuarioView
import kotlinx.android.synthetic.main.activity_login.*;
import net.grandcentrix.thirtyinch.TiActivity

@SuppressLint("MissingTiViewImplementation")
class LoginActivity : TiActivity<UsuarioPresenter, UsuarioView>(), UsuarioView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Clique vai para nova conta
        btNovaConta.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val it = Intent(this@LoginActivity, NovoUsuarioActivity::class.java)
                startActivity(it)
            }
        })

        btLogin.setOnClickListener { {v : View ->
            presenter.logar(edtEmail.text.toString(), edtSenha.text.toString())
        } }
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

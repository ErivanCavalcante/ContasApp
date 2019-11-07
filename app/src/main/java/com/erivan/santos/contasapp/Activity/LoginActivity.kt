package com.erivan.santos.contasapp.Activity

import android.widget.Toast
import com.erivan.santos.contasapp.MainActivity_
import com.erivan.santos.contasapp.Presenter.UsuarioPresenter
import com.erivan.santos.contasapp.R
import com.erivan.santos.contasapp.View.UsuarioView
import com.google.android.material.textfield.TextInputEditText
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.Email
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import com.mobsandgeeks.saripaar.annotation.Password
import net.grandcentrix.thirtyinch.TiActivity
import org.androidannotations.annotations.*

@EActivity(R.layout.activity_login)
@Fullscreen
open class LoginActivity : TiActivity<UsuarioPresenter, UsuarioView>(), UsuarioView, Validator.ValidationListener {

    @NotEmpty
    @Email
    @ViewById
    lateinit var edtEmail: TextInputEditText

    @NotEmpty
    @Password(min = 6)
    @ViewById
    lateinit var edtSenha: TextInputEditText

    lateinit var validator: Validator

    @AfterViews
    fun setupViews() {
        validator = Validator(this)
        validator.setValidationListener(this)
    }

    @Click(R.id.btLogin)
    fun clickLoginBotao() {
        validator.validate()
    }

    @Click(R.id.btNovaConta)
    fun clickLoginNovaConta() {
        NovoUsuarioActivity_.intent(this).start()
        finish()
    }

    override fun loginOk() {
        MainActivity_.intent(this).start()
        finish()
    }

    override fun adicionado() {

    }

    override fun erro(code: Int, erro: String) {
        Toast.makeText(this, erro, Toast.LENGTH_SHORT).show()
    }


    override fun providePresenter(): UsuarioPresenter {
        return UsuarioPresenter()
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        for (erro: ValidationError in errors!!) {
            val view = erro.view
            val msg = erro.getCollatedErrorMessage(this)

            if (view is TextInputEditText) {
                view.setError(msg)
            }
        }
    }

    override fun onValidationSucceeded() {
        presenter.logar(edtEmail.text.toString(), edtSenha.text.toString())
    }

    override fun viewCriada() {

    }
}

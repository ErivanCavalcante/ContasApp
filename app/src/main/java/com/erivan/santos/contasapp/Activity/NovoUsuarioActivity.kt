package com.erivan.santos.contasapp.Activity

import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.erivan.santos.contasapp.POJO.Usuario
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

@EActivity(R.layout.activity_novo_usuario)
@OptionsMenu(R.menu.menu_ok)
open class NovoUsuarioActivity : TiActivity<UsuarioPresenter, UsuarioView>(), UsuarioView, Validator.ValidationListener  {

    @ViewById
    lateinit var spSexo: Spinner

    @NotEmpty
    @ViewById
    lateinit var edtNome: TextInputEditText

    @NotEmpty
    @Email
    @ViewById
    lateinit var edtEmail: TextInputEditText

    @NotEmpty
    @Password
    @ViewById
    lateinit var edtSenha: TextInputEditText

    @NotEmpty
    @ViewById
    lateinit var edtTelefone: TextInputEditText

    lateinit var validator: Validator

    @AfterViews
    fun setupViews() {
        var tipos = arrayOf("Masculino", "Feminino")
        var adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tipos)

        spSexo.adapter = adapter

        validator = Validator(this)
        validator.setValidationListener(this)
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
        validator.validate()
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
    }

    override fun onValidationSucceeded() {
        //Cria o usuario
        val usuario = Usuario(edtNome.text.toString(), edtEmail.text.toString(), edtSenha.text.toString(), edtTelefone.text.toString(), spSexo.selectedItemPosition)

        //Adiciona
        presenter.adicionar(usuario)
    }
}

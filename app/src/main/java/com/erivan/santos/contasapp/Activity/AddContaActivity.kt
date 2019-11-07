package com.erivan.santos.contasapp.Activity

import android.widget.CheckBox
import android.widget.Toast
import com.erivan.santos.contasapp.POJO.Conta
import com.erivan.santos.contasapp.Presenter.ContaPresenter
import com.erivan.santos.contasapp.R
import com.erivan.santos.contasapp.View.ContaView
import com.google.android.material.textfield.TextInputEditText
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.Max
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import net.grandcentrix.thirtyinch.TiActivity
import org.androidannotations.annotations.*
import java.text.SimpleDateFormat

@EActivity(R.layout.activity_add_conta)
@OptionsMenu(R.menu.menu_ok)
open class AddContaActivity : TiActivity<ContaPresenter, ContaView>(), ContaView, Validator.ValidationListener {

    @NotEmpty
    @ViewById
    lateinit var edtNome: TextInputEditText

    @NotEmpty
    @ViewById
    lateinit var edtDescricao: TextInputEditText

    @NotEmpty
    @ViewById
    lateinit var edtValor: TextInputEditText

    @ViewById
    lateinit var cbRecorrente: CheckBox

    @Max(360)
    @ViewById
    lateinit var edtQtdParcelas: TextInputEditText

    @Max(60)
    @ViewById
    lateinit var edtPeriodicidade: TextInputEditText

    @NotEmpty
    @ViewById
    lateinit var edtDataVencimento: TextInputEditText

    @ViewById
    lateinit var cbAvisarVencimento: CheckBox

    lateinit var validator: Validator

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

        //Cria as validacoes
        validator = Validator(this)
        validator.setValidationListener(this)
    }

    @OptionsItem(R.id.menu_check)
    fun salvar() {
        //Valida td antes d salvar
        validator.validate()
    }

    override fun providePresenter(): ContaPresenter {
        return ContaPresenter()
    }

    override fun adicionou() {
        onBackPressed()
    }

    override fun lista(tipo: Int, contas: List<Conta>) {

    }

    override fun erro(code: Int, erro: String) {
        Toast.makeText(this, erro, Toast.LENGTH_SHORT).show()
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
    }

    override fun onValidationSucceeded() {
        //Coloca no arquivo conta
        var conta = Conta()

        conta.titulo = edtNome.text.toString()
        conta.descricao = edtDescricao.text.toString()
        conta.valor = edtValor.text.toString().toFloat()
        conta.avisarVencimento = cbAvisarVencimento.isChecked
        conta.dataVencimento = SimpleDateFormat("dd/MM/YYYY").parse(edtDataVencimento.text.toString())

        //Se eh recorrente cria o numero de contas necessarios
        if (cbRecorrente.isChecked) {
            //Adiciona todas as contas aqui dentro
            presenter!!.adicionarConta(conta, edtQtdParcelas.text.toString().toInt(), edtPeriodicidade.text.toString().toInt())
        }
        else {
            presenter!!.adicionarConta(conta, null, null)
        }

    }
}

package com.erivan.santos.contasapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.erivan.santos.contasapp.POJO.Conta
import com.erivan.santos.contasapp.Presenter.ContaPresenter
import com.erivan.santos.contasapp.R
import com.erivan.santos.contasapp.View.ContaView
import kotlinx.android.synthetic.main.activity_add_conta.*
import net.grandcentrix.thirtyinch.TiActivity
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.OptionsItem
import org.androidannotations.annotations.OptionsMenu
import java.text.SimpleDateFormat

@EActivity(R.layout.activity_add_conta)
@OptionsMenu(R.menu.menu_ok)
open class AddContaActivity : TiActivity<ContaPresenter, ContaView>(), ContaView {

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
        //Valida td antes d salvar

        if (cbRecorrente.isChecked) {
            //Adiciona todas as contas aqui dentro
        }

        //Coloca no arquivo conta
        var conta = Conta()

        conta.titulo = edtNome.text.toString()
        conta.descricao = edtDescricao.text.toString()
        conta.valor = edtValor.text.toString().toFloat()
        conta.avisarVencimento = cbAvisarVencimento.isChecked
        conta.dataVencimento = SimpleDateFormat("dd/MM/YYYY").parse(edtDataVencimento.text.toString())


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
}

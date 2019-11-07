package com.erivan.santos.contasapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.erivan.santos.contasapp.MainActivity_
import com.erivan.santos.contasapp.POJO.Conta
import com.erivan.santos.contasapp.Presenter.ContaPresenter
import com.erivan.santos.contasapp.R
import com.erivan.santos.contasapp.View.ContaView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.activity_lista.*
import net.grandcentrix.thirtyinch.TiActivity
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_lista)
open class ListaActivity : TiActivity<ContaPresenter, ContaView>(), ContaView {
    val lista = ItemAdapter<Conta>()
    val adapter = FastAdapter.with(lista)

    @AfterViews
    fun setupViews() {
        rvLista.setHasFixedSize(true)

        val mng = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvLista.layoutManager = mng

        rvLista.adapter = adapter

        adapter.onClickListener = { view, adapter, item, position ->
            var conta = lista.getAdapterItem(position)

            ContaDetalheActivity_.intent(this).extra("conta", conta).start();

            true
        }

        //Carrega td
        presenter!!.carregarTodas()
    }

    override fun providePresenter(): ContaPresenter {
        return ContaPresenter()
    }

    override fun adicionou() {

    }

    override fun lista(tipo: Int, contas: List<Conta>) {
        if (contas.size > 0) {
            lista.clear()
            lista.add(contas)
        }
    }

    override fun erro(code: Int, erro: String) {
        Toast.makeText(this, erro, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        MainActivity_.intent(this).start()
        finish()
    }

    @Click(R.id.fabAdd)
    fun addConta() {
        AddContaActivity_.intent(this).start()
        finish()
    }
}

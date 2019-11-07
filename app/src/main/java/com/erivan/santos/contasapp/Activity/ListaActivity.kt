package com.erivan.santos.contasapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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
open class ListaActivity : TiActivity<ContaPresenter, ContaView>(), ContaView, SearchView.OnQueryTextListener {

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

            ContaDetalheActivity_.intent(this)
                                .extra("conta", conta)
                                .start();

            finish()

            true
        }
    }

    override fun providePresenter(): ContaPresenter {
        return ContaPresenter()
    }

    override fun viewCriada() {
        if (lista.adapterItemCount == 0)
            presenter!!.carregarTodas()
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
        //MainActivity_.intent(this).start()
        LoginActivity_.intent(this).start()
        finish()
    }

    @Click(R.id.fabAdd)
    fun addConta() {
        AddContaActivity_.intent(this).start()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_pesquisa, menu)

        val search = menu!!.findItem(R.id.menu_pesquisa)
        val searchView: SearchView = search.actionView as SearchView

        searchView.queryHint = "Descrição"

        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        //Faz a pesquisa
        presenter!!.carregarComFiltro(query!!)

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}

package com.erivan.santos.contasapp.Activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
open class ListaActivity : TiActivity<ContaPresenter, ContaView>(), ContaView, SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    val lista = ItemAdapter<Conta>()
    val adapter = FastAdapter.with(lista)

    internal var pesquisou = false

    @AfterViews
    fun setupViews() {
        rvLista.setHasFixedSize(true)

        val mng = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvLista.layoutManager = mng

        rvLista.adapter = adapter

        //Mostra os detalhes
        adapter.onClickListener = { view, adapter, item, position ->
            var conta = lista.getAdapterItem(position)

            ContaDetalheActivity_.intent(this)
                                .extra("conta", conta)
                                .start();

            finish()

            true
        }

        adapter.onLongClickListener = { view, adapter, item, position ->
            var conta = lista.getAdapterItem(position)

            AlertDialog.Builder(ListaActivity_@this)
                .setTitle("Confirmar ação")
                .setMessage("Deseja remover essa conta?")
                .setPositiveButton("Sim", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, p1: Int) {
                        //Remove td em seguida carrega a lista
                        presenter.removerConta(conta)
                        presenter.carregarTodas()

                        dialog?.dismiss()
                    }
                })
                .setNegativeButton("Não", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, p1: Int) {
                        dialog?.dismiss()
                    }
                }).show()

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

    override fun removeu() {

    }

    override fun lista(tipo: Int, contas: List<Conta>) {
        if (contas.size == 0)  txtNullState.visibility = View.VISIBLE
        else txtNullState.visibility = View.GONE

        lista.clear()
        lista.add(contas)
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
        pesquisou = if (newText!!.isEmpty()) false else true;

        return true
    }

    override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
        pesquisou = false

        return true
    }

    override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
        if (!pesquisou) {
            presenter!!.carregarTodas()
        }

        return true
    }
}

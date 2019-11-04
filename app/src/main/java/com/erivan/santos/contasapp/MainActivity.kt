package com.erivan.santos.contasapp

import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erivan.santos.contasapp.Activity.ListaActivity_
import com.erivan.santos.contasapp.POJO.GenericItem
import com.erivan.santos.contasapp.POJO.TituloListItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById

@EActivity(R.layout.activity_main)
open class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    @ViewById
    lateinit var rvLista: RecyclerView

    @ViewById
    lateinit var fabAdd: FloatingActionButton

    val lista = ItemAdapter<GenericItem>()
    val adapter = FastAdapter.with(lista)

    @AfterViews
    fun setupViews() {
        //fabAdd.visibility = View.GONE

        val layout = navView.getHeaderView(0)
        val txtNome = layout.findViewById<TextView>(R.id.txtNome)
        val txtEmail = layout.findViewById<TextView>(R.id.txtEmail)

        txtEmail.text = ApplicationCustom.getInstance().sessaoAtual.email
        txtNome.text = ApplicationCustom.getInstance().sessaoAtual.nome

        rvLista.setHasFixedSize(true)

        val lm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvLista.layoutManager = lm

        rvLista.adapter = adapter

        navView.setNavigationItemSelectedListener(this)

        addOpcoes()
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_gerir_contas -> {
                ListaActivity_.intent(this).start()
                finish()
            }

            R.id.menu_sair -> finish()
        }

        return true
    }

    fun addOpcoes() {
        lista.add(TituloListItem("Contas do mês"))

        lista.add(TituloListItem("Próximas a vencer"))

        lista.add(TituloListItem("Contas vencidas"))
    }
}

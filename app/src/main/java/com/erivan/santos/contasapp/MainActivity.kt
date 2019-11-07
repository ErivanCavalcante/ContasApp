package com.erivan.santos.contasapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erivan.santos.contasapp.Activity.ListaActivity_
import com.erivan.santos.contasapp.Activity.LoginActivity_
import com.erivan.santos.contasapp.POJO.GenericItem
import com.erivan.santos.contasapp.POJO.TituloListItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById

@EActivity(R.layout.activity_main)
open class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @ViewById
    lateinit var rvLista: RecyclerView

    @ViewById
    lateinit var fabAdd: FloatingActionButton

    @ViewById
    lateinit var navView: NavigationView

    @ViewById
    lateinit var layDrawer: DrawerLayout

    lateinit var toogle: ActionBarDrawerToggle

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

        //Seta o botao de menu
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        toogle = ActionBarDrawerToggle(this, layDrawer, R.string.open_drawer, R.string.close_drawer)
        toogle.drawerArrowDrawable.color = Color.WHITE
        layDrawer.addDrawerListener(toogle)

        toogle.syncState()
    }

    override fun onBackPressed() {
        LoginActivity_.intent(this).start()
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

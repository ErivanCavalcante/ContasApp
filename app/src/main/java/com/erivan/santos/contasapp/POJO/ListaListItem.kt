package com.erivan.santos.contasapp.POJO

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erivan.santos.contasapp.ApplicationCustom
import com.erivan.santos.contasapp.R
import com.erivan.santos.contasapp.Repository.ContaDao
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.items.AbstractItem

open class ListaListItem(var tipo: TipoLista) : AbstractItem<ListaListItem.ListaListItemViewHolder>() {

    enum class TipoLista(tipo: Int) {
        MES_ATUAL(0),
        PROXIMAS_VENCER(1),
        VENCIDAS(2)
    }

    override val layoutRes: Int
        get() = R.layout.list_item_lista

    override val type: Int
        get() = R.id.rv

    override fun getViewHolder(v: View): ListaListItemViewHolder {
        return ListaListItemViewHolder(v)
    }


    class ListaListItemViewHolder(view: View) : FastAdapter.ViewHolder<ListaListItem>(view) {
        val lista = view.findViewById<RecyclerView>(R.id.rv)

        val itemAdapter = ItemAdapter<ListaItemListItem>()
        val fastAdapter = FastAdapter.with(itemAdapter)

        init {
            lista.setHasFixedSize(true)

            val lm = LinearLayoutManager(ApplicationCustom.getInstance().applicationContext,
                                                                LinearLayoutManager.HORIZONTAL, false)
            lista.layoutManager = lm
        }

        override fun bindView(item: ListaListItem, payloads: MutableList<Any>) {
            val lista = carregar(item.tipo)

            val listaFinal = ArrayList<ListaItemListItem>()

            for (c : Conta in lista) {
                listaFinal.add(ListaItemListItem(c.valor, c.dataVencimento))
            }

            itemAdapter.add(listaFinal)

            this.lista.adapter = fastAdapter
        }

        override fun unbindView(item: ListaListItem) {

        }

        private fun carregar(tipo: TipoLista) : List<Conta> {
            val dao = ContaDao()
            lateinit var lista: List<Conta>;

            if (tipo == TipoLista.MES_ATUAL) {
                lista = dao.pesquisarPorMes(ApplicationCustom.getInstance().sessaoAtual);
            }
            else if (tipo == TipoLista.PROXIMAS_VENCER) {
                lista = dao.pesquisarPorProximasVencer(ApplicationCustom.getInstance().sessaoAtual);
            }
            else if (tipo == TipoLista.VENCIDAS) {
                lista = dao.pesquisarPorVencidas(ApplicationCustom.getInstance().sessaoAtual);
            }
            else {
                lista = ArrayList<Conta>()
            }

            return lista
        }
    }
}
package com.erivan.santos.contasapp.POJO

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erivan.santos.contasapp.ApplicationCustom
import com.erivan.santos.contasapp.R
import com.erivan.santos.contasapp.Repository.ContaDao
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

open class ListaListItem(var tipo: TipoLista) : GenericItem() {

    enum class TipoLista(tipo: Int) {
        MES_ATUAL(0),
        PROXIMAS_VENCER(1),
        VENCIDAS(2)
    }

    override val layoutRes: Int
        get() = R.layout.list_item_lista

    override val type: Int
        get() = R.id.rv

    override fun getCustomViewHolder(v: View): GenericItemViewHolder {
        return ListaListItemViewHolder(v)
    }

    class ListaListItemViewHolder(view: View) : GenericItemViewHolder(view) {

        var lista: RecyclerView

        init {
            lista = view.findViewById(R.id.rv)

            lista.setHasFixedSize(true)

            val lm = LinearLayoutManager(ApplicationCustom.getInstance().applicationContext,
                                            LinearLayoutManager.HORIZONTAL, false)

            lista.layoutManager = lm
        }

        val itemAdapter = ItemAdapter<ListaItemListItem>()
        val fastAdapter = FastAdapter.with(itemAdapter)

        override fun setupViews(view: View) {

        }

        override fun bind(item: GenericItem?) {
            item as ListaListItem

            val lista = carregar(item.tipo)

            val listaFinal = ArrayList<ListaItemListItem>()

            for (c : Conta in lista) {
                listaFinal.add(ListaItemListItem(c.titulo, c.descricao, c.valor, c.dataVencimento, c.pago))
            }

            itemAdapter.add(listaFinal)

            this.lista.adapter = fastAdapter
        }

        override fun unbind(item: GenericItem?) {

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
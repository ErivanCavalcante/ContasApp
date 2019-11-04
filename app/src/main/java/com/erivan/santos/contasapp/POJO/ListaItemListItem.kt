package com.erivan.santos.contasapp.POJO

import android.view.View
import android.widget.TextView
import com.erivan.santos.contasapp.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import java.text.SimpleDateFormat
import java.util.*

open class ListaItemListItem(var valor: Float, var dataVencimento: Date) : AbstractItem<ListaListItem.ListaListItemViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.list_item_lista_item

    override val type: Int
        get() = R.id.txtValor

    override fun getViewHolder(v: View): ListaListItem.ListaListItemViewHolder {
        return ListaListItem.ListaListItemViewHolder(v)
    }


    class ListaItemListItemViewHolder(view: View) : FastAdapter.ViewHolder<ListaItemListItem>(view) {
        val txtValor = view.findViewById<TextView>(R.id.txtValor)
        val txtDataVencimento = view.findViewById<TextView>(R.id.txtDataVencimento)

        override fun bindView(item: ListaItemListItem, payloads: MutableList<Any>) {
            txtValor.text = item.valor.toString()
            txtDataVencimento.text = SimpleDateFormat("dd/MM/yyyy").format(item.dataVencimento)
        }

        override fun unbindView(item: ListaItemListItem) {
            txtValor.text = ""
            txtDataVencimento.text = ""
        }

    }
}
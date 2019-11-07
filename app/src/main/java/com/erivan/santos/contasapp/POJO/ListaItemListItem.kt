package com.erivan.santos.contasapp.POJO

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.erivan.santos.contasapp.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import java.text.SimpleDateFormat
import java.util.*

open class ListaItemListItem(var nome: String, var descricao: String, var valor: Float, var dataVencimento: Date, var pago: Boolean) : AbstractItem<ListaItemListItem.ListaItemListItemViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.list_item_lista_item

    override val type: Int
        get() = R.id.txtValor

    override fun getViewHolder(v: View): ListaItemListItemViewHolder {
        return ListaItemListItemViewHolder(v)
    }


    class ListaItemListItemViewHolder(view: View) : FastAdapter.ViewHolder<ListaItemListItem>(view) {
        val txtNome = view.findViewById<TextView>(R.id.txtNome)
        val txtDescricao = view.findViewById<TextView>(R.id.txtDescricao)
        val txtValor = view.findViewById<TextView>(R.id.txtValor)
        val txtDataVencimento = view.findViewById<TextView>(R.id.txtDataVencimento)

        override fun bindView(item: ListaItemListItem, payloads: MutableList<Any>) {
            txtNome.text = item.nome
            txtDescricao.text = item.descricao
            txtValor.text = "R$ " + item.valor.toString()
            txtDataVencimento.text = SimpleDateFormat("dd/MM/yyyy").format(item.dataVencimento)

            if (item.pago == false)
                txtValor.setTextColor(Color.RED)
        }

        override fun unbindView(item: ListaItemListItem) {
            txtValor.text = ""
            txtDataVencimento.text = ""
            txtNome.text = ""
            txtDescricao.text = ""
        }

    }
}
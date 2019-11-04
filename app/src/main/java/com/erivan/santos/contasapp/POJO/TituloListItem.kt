package com.erivan.santos.contasapp.POJO

import android.view.View
import android.widget.TextView
import com.erivan.santos.contasapp.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

open class TituloListItem(var titulo: String) : AbstractItem<TituloListItem.TituloListItemViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.list_item_titulo
    override val type: Int
        get() = R.id.txtTitulo;

    override fun getViewHolder(v: View): TituloListItemViewHolder {
        return TituloListItemViewHolder(v)
    }

    class TituloListItemViewHolder(view: View) : FastAdapter.ViewHolder<TituloListItem>(view) {
        var txtTitulo = view.findViewById<TextView>(R.id.txtTitulo)

        override fun bindView(item: TituloListItem, payloads: MutableList<Any>) {
            txtTitulo.text = item.titulo
        }

        override fun unbindView(item: TituloListItem) {
            txtTitulo.text = ""
        }

    }
}
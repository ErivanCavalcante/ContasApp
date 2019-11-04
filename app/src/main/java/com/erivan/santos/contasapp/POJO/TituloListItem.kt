package com.erivan.santos.contasapp.POJO

import android.view.View
import android.widget.TextView
import com.erivan.santos.contasapp.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

open class TituloListItem(var titulo: String) : GenericItem() {
    override fun getCustomViewHolder(v: View): GenericItemViewHolder {
        return TituloListItemViewHolder(v)
    }

    override val layoutRes: Int
        get() = R.layout.list_item_titulo
    override val type: Int
        get() = R.id.txtTitulo;

    class TituloListItemViewHolder(view: View) : GenericItemViewHolder(view) {
        var txtTitulo = view.findViewById<TextView>(R.id.txtTitulo)

        override fun setupViews(view: View?) {

        }

        override fun bind(item: GenericItem?) {
            item as TituloListItem

            txtTitulo.text = item.titulo
        }

        override fun unbind(item: GenericItem?) {
            txtTitulo.text = ""
        }
    }
}
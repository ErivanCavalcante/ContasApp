package com.erivan.santos.contasapp.POJO

import android.view.View
import android.widget.TextView
import com.erivan.santos.contasapp.R
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import java.text.SimpleDateFormat
import java.util.*

@DatabaseTable(tableName = "Contas")
open class Conta : AbstractItem<Conta.ContaViewHolder> {

    @DatabaseField(generatedId = true)
    var id: Int = 0

    @DatabaseField
    var titulo = ""

    @DatabaseField
    var descricao = ""

    @DatabaseField
    var valor = 0.0f

    @DatabaseField(canBeNull = true)
    var parcelas: Int? = null

    @DatabaseField(canBeNull = true)
    var periodo_dias: Int?  = null

    @DatabaseField
    var dataVencimento: Date = Date()

    @DatabaseField
    var avisarVencimento: Boolean = false

    @DatabaseField(foreign = true)
    var usuario: Usuario? = null

    @DatabaseField
    var pago = false

    override val layoutRes: Int
        get() = R.layout.list_item_conta

    override val type: Int
        get() = 0

    override fun getViewHolder(v: View): ContaViewHolder {
        return ContaViewHolder(v)
    }

    constructor()

    constructor(
        titulo: String,
        descricao: String,
        valor: Float,
        parcelas: Int?,
        periodo_dias: Int?,
        dataVencimento: Date,
        avisarVencimento: Boolean,
        usuario: Usuario
    ) {
        this.titulo = titulo
        this.descricao = descricao
        this.valor = valor
        this.parcelas = parcelas
        this.periodo_dias = periodo_dias
        this.dataVencimento = dataVencimento
        this.avisarVencimento = avisarVencimento
        this.usuario = usuario
    }

    class ContaViewHolder(view: View) : FastAdapter.ViewHolder<Conta>(view) {
        var txtStatus = view.findViewById<TextView>(R.id.txtStatus)
        var txtNome = view.findViewById<TextView>(R.id.txtNome)
        var txtDescricao = view.findViewById<TextView>(R.id.txtDescricao)
        var txtValor = view.findViewById<TextView>(R.id.txtValor)
        var txtDataVencimento = view.findViewById<TextView>(R.id.txtDataVencimento)

        override fun bindView(item: Conta, payloads: MutableList<Any>) {
            val f = SimpleDateFormat("dd/MM/yyyy")

            txtNome.text = item.titulo
            txtDescricao.text = item.descricao
            txtValor.text = "R$ " + item.valor
            txtDataVencimento.text = f.format(item.dataVencimento)
        }

        override fun unbindView(item: Conta) {
            txtNome.text = ""
            txtDescricao.text = ""
            txtValor.text = ""
            txtDataVencimento.text = ""
        }

    }
}
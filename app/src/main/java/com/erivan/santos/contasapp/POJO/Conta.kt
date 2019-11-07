package com.erivan.santos.contasapp.POJO

import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.TextView
import com.erivan.santos.contasapp.ApplicationCustom_
import com.erivan.santos.contasapp.R
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import java.text.SimpleDateFormat
import java.util.*

@DatabaseTable(tableName = "Contas")
open class Conta : AbstractItem<Conta.ContaViewHolder>, Parcelable {

    @DatabaseField(generatedId = true)
    var id: Int = 0

    @DatabaseField
    var titulo = ""

    @DatabaseField
    var descricao = ""

    @DatabaseField
    var valor = 0.0f

    @DatabaseField
    var dataVencimento: Date = Date()

    @DatabaseField
    var avisarVencimento: Boolean = false

    @DatabaseField(foreign = true)
    lateinit var usuario: Usuario

    @DatabaseField
    var pago = false

    override val layoutRes: Int
        get() = R.layout.list_item_conta

    override val type: Int
        get() = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        titulo = parcel.readString()!!
        descricao = parcel.readString()!!
        valor = parcel.readFloat()
        avisarVencimento = if (parcel.readInt() == 1) true else false
        pago = if (parcel.readInt() == 1) true else false
        dataVencimento = parcel.readSerializable() as Date
    }

    override fun getViewHolder(v: View): ContaViewHolder {
        return ContaViewHolder(v)
    }

    constructor()

    constructor(
        titulo: String,
        descricao: String,
        valor: Float,
        dataVencimento: Date,
        avisarVencimento: Boolean,
        usuario: Usuario
    ) {
        this.titulo = titulo
        this.descricao = descricao
        this.valor = valor
        this.dataVencimento = dataVencimento
        this.avisarVencimento = avisarVencimento
        this.usuario = usuario
    }

    //Copia
    constructor(conta: Conta) {
        this.titulo = conta.titulo
        this.descricao = conta.descricao
        this.valor = conta.valor
        this.dataVencimento = conta.dataVencimento
        this.avisarVencimento = conta.avisarVencimento
        this.usuario = conta.usuario
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeInt(id)
        dest!!.writeString(titulo)
        dest!!.writeString(descricao)
        dest!!.writeFloat(valor)
        dest!!.writeInt(if (avisarVencimento) 1 else 0)
        dest!!.writeInt(if (pago) 1 else 0)
        dest!!.writeSerializable(dataVencimento)
    }

    override fun describeContents(): Int {
        return 0
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

            if (item.pago == true)
                txtStatus.setBackgroundColor(ApplicationCustom_.getInstance().resources.getColor(R.color.colorPrimary))
            else
                txtStatus.setBackgroundColor(Color.RED)
        }

        override fun unbindView(item: Conta) {
            txtNome.text = ""
            txtDescricao.text = ""
            txtValor.text = ""
            txtDataVencimento.text = ""
        }

    }

    companion object CREATOR : Parcelable.Creator<Conta> {
        override fun createFromParcel(parcel: Parcel): Conta {
            return Conta(parcel)
        }

        override fun newArray(size: Int): Array<Conta?> {
            return arrayOfNulls(size)
        }
    }
}
package com.erivan.santos.contasapp.POJO

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.util.*

@DatabaseTable(tableName = "Contas")
class Conta {
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


}
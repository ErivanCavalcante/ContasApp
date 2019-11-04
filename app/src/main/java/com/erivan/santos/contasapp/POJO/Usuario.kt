package com.erivan.santos.contasapp.POJO

import com.j256.ormlite.dao.ForeignCollection
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.field.ForeignCollectionField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "Usuarios")
open class Usuario {
    @DatabaseField(generatedId = true)
    var id: Int = 0

    @DatabaseField
    var nome: String = "";

    @DatabaseField
    var email: String = "";

    @DatabaseField
    var senha: String = "";

    @DatabaseField
    var fone: String = ""

    @DatabaseField
    var sexo: Int = 0;

    @ForeignCollectionField
    lateinit var contas: ForeignCollection<Conta>

    constructor()

    constructor(nome: String, email: String, senha: String, fone: String, sexo: Int) {
        this.nome = nome
        this.email = email
        this.senha = senha
        this.sexo = sexo
        this.fone = fone
    }
}
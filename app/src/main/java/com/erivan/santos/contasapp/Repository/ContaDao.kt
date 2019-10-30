package com.erivan.santos.contasapp.Repository

import com.erivan.santos.contasapp.POJO.Conta
import com.github.thunder413.datetimeutils.DateTimeUnits
import com.github.thunder413.datetimeutils.DateTimeUtils
import com.j256.ormlite.dao.BaseDaoImpl
import org.jetbrains.annotations.NotNull
import java.util.*

class ContaDao : BaseDaoImpl<Conta, Int>
{
    constructor() : super(OrmliteHelper.getInstance().connectionSource, Conta::class.java)

    fun adicionar(conta: Conta) : Boolean {
        return create(conta) > 0
    }

    fun atualizar(conta: Conta) : Boolean {
        return update(conta) > 0
    }

    fun remover(conta: Conta) : Boolean {
        return delete(conta) > 0
    }

    fun pesquisarPorMes() : List<Conta> {
        var hoje = Date()

        var sql = "SELECT * FROM contas WHERE pago = 0 AND strftime('%m', dataVencimento) = " + "'" + hoje.month + "'";
        queryRaw(sql)
    }

    fun pesquisarPorVencidas() : List<Conta> {
        var dataHoje = Date()

        return queryBuilder().where().eq("pago", false).and().gt("dataVencimento", dataHoje).query()
    }

    //Pesquisam todas q faltam ate 8 dias para vencer ou ja venceram
    fun pesquisarPorProximasVencer() : List<Conta> {
        var dataHoje = Date()

        queryBuilder()
            .orderBy("dataVencimento", false)
            .where()
            .ge("dataVencimento", dataHoje)
            .and()
            .
    }

    //Pesquisa todas q estao abertas
    fun pesquisarTodasAberto() : List<Conta> {
        return queryBuilder().orderBy("pago", false).where().eq("pago", false).query()
    }

    //Pesquisa todas ordenadas por data de vencmento
    fun pesquisarTodas(data : Date) : List<Conta> {
        return queryBuilder().orderBy("dataVencimento", false).query()
    }

    fun pesquisarAvancado(data : Date) : List<Conta> {

    }
}
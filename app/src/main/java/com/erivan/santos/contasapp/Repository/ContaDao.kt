package com.erivan.santos.contasapp.Repository

import android.util.Log
import com.erivan.santos.contasapp.POJO.Conta
import com.erivan.santos.contasapp.POJO.Usuario
import com.github.thunder413.datetimeutils.DateTimeUnits
import com.github.thunder413.datetimeutils.DateTimeUtils
import com.j256.ormlite.dao.BaseDaoImpl
import com.j256.ormlite.dao.GenericRawResults
import com.j256.ormlite.dao.RawRowMapper
import org.jetbrains.annotations.NotNull
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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

    fun pesquisarPorId(contaId: Int) : Conta {
        return queryForId(contaId)
    }

    //Pega todas as contas do mes pagas ou nao
    fun pesquisarPorMes(usuario: Usuario) : List<Conta> {
        var hoje = Date()
        var f = SimpleDateFormat("MM")

        var mes: String = f.format(hoje)

        //Pega as contas do mes atual
        var sql = "SELECT id, titulo, descricao, valor, dataVencimento, avisarVencimento, pago FROM contas " +
                "WHERE usuario_id = ${usuario.id} " +
                "AND strftime('%m', dataVencimento) = '$mes' ORDER BY pago = 0 ASC";



        var result : GenericRawResults<Conta>  = queryRaw(sql, object : RawRowMapper<Conta> {
            override fun mapRow(columnNames: Array<out String>?, resultColumns: Array<out String>?): Conta {
                var conta = Conta()

                var f = SimpleDateFormat("yyyy-MM-dd HH:mm")

                conta.usuario = usuario
                conta.id = resultColumns!!.get(0).toInt()
                conta.titulo = resultColumns!!.get(1)
                conta.descricao = resultColumns!!.get(2)
                conta.valor = resultColumns!!.get(3).toFloat()
                conta.dataVencimento = f.parse(resultColumns!!.get(4))
                conta.avisarVencimento = if (resultColumns!!.get(5).toInt() == 0) false else true
                conta.pago = if (resultColumns!!.get(6).toInt() == 0) false else true

                return conta
            }
        })

        return result.results
    }

    fun pesquisarPorVencidas(usuario: Usuario) : List<Conta> {
        //Pega as contas do mes atual
        var sql = "SELECT id, titulo, descricao, valor, dataVencimento, avisarVencimento, pago FROM contas " +
                "WHERE usuario_id = ${usuario.id} AND " +
                "pago = 0 " +
                "AND date('now') > dataVencimento";

        var result : GenericRawResults<Conta>  = queryRaw(sql, object : RawRowMapper<Conta> {
            override fun mapRow(columnNames: Array<out String>?, resultColumns: Array<out String>?): Conta {
                var conta : Conta = Conta()

                var f = SimpleDateFormat("yyyy-MM-dd HH:mm")

                conta.usuario = usuario
                conta.id = resultColumns!!.get(0).toInt()
                conta.titulo = resultColumns!!.get(1)
                conta.descricao = resultColumns!!.get(2)
                conta.valor = resultColumns!!.get(3).toFloat()
                conta.dataVencimento = f.parse(resultColumns!!.get(4))
                conta.avisarVencimento = if (resultColumns!!.get(5).toInt() == 0) false else true
                conta.pago = if (resultColumns!!.get(6).toInt() == 0) false else true

                return conta
            }
        })

        return result.results
    }

    //Pesquisam todas q faltam ate 8 dias para vencer ou ja venceram
    fun pesquisarPorProximasVencer(usuario: Usuario) : List<Conta> {
        var hoje = Date()
        var f = SimpleDateFormat("yyyy-MM-dd")

        var dataFormatada: String = f.format(hoje)

        //Pega as contas do mes atual
        var sql = "SELECT id, titulo, descricao, valor, dataVencimento, avisarVencimento, pago FROM contas " +
                "WHERE usuario_id = ${usuario.id} AND " +
                "pago = 0 " +
                "AND '$dataFormatada' > date(dataVencimento, '-8 days') AND '$dataFormatada' < dataVencimento";

        var result : GenericRawResults<Conta>  = queryRaw(sql, object : RawRowMapper<Conta> {
            override fun mapRow(columnNames: Array<out String>?, resultColumns: Array<out String>?): Conta {
                var conta : Conta = Conta()

                var f = SimpleDateFormat("yyyy-MM-dd HH:mm")

                conta.usuario = usuario
                conta.id = resultColumns!!.get(0).toInt()
                conta.titulo = resultColumns!!.get(1)
                conta.descricao = resultColumns!!.get(2)
                conta.valor = resultColumns!!.get(3).toFloat()
                conta.dataVencimento = f.parse(resultColumns!!.get(4))
                conta.avisarVencimento = if (resultColumns!!.get(5).toInt() == 0) false else true
                conta.pago = if (resultColumns!!.get(6).toInt() == 0) false else true

                return conta
            }
        })

        return result.results
    }

    //Pesquisa todas q estao abertas
    fun pesquisarTodasAberto(usuario: Usuario) : List<Conta> {
        return queryBuilder()
            .orderBy("pago", false)
            .where()
            .eq("pago", false)
            .and()
            .eq("usuario_id", usuario.id)
            .query()
    }

    //Pesquisa todas ordenadas por data de vencmento
    fun pesquisarTodas(usuario: Usuario) : List<Conta> {
        return queryBuilder()
            .orderBy("dataVencimento", false)
            .orderByRaw("pago = 0 ASC")
            .where()
            .eq("usuario_id", usuario.id)
            .query()
    }

    fun pesquisarAvancado(usuario: Usuario, descricao: String) : List<Conta> {

        return queryBuilder().where()
            .eq("usuario_id", usuario.id)
            .and()
            .eq("descricao", descricao)
            .query()
    }
}
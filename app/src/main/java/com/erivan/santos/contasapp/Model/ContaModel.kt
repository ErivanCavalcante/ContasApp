package com.erivan.santos.contasapp.Model

import com.erivan.santos.contasapp.ApplicationCustom_
import com.erivan.santos.contasapp.POJO.Conta
import com.erivan.santos.contasapp.Repository.ContaDao
import java.util.*
import kotlin.collections.ArrayList

class ContaModel {
    var contaDao = ContaDao()

    fun pegarTodas() : List<Conta> {
        if (ApplicationCustom_.getInstance().sessaoAtual == null)
            return ArrayList<Conta>()

        return contaDao.pesquisarTodasAberto(ApplicationCustom_.getInstance().sessaoAtual)
    }

    fun adicionar(conta: Conta, numParcelas: Int?, periodo: Int?) : Boolean {
        if (ApplicationCustom_.getInstance().sessaoAtual == null)
            return false

        //Ajusta a conta base
        conta.usuario = ApplicationCustom_.getInstance().sessaoAtual

        //Se nao tem parcelas salva e td certo
        if (numParcelas == null)
            return contaDao.adicionar(conta)

        var erro = false
        var calendario = Calendar.getInstance();
        //calendario.se
        calendario.set(Calendar.YEAR, conta.dataVencimento.year)
        calendario.set(Calendar.MONTH, conta.dataVencimento.month)
        calendario.set(Calendar.DAY_OF_MONTH, conta.dataVencimento.day)

        //Tem parcelas entao cria as novas conta
        for (i in 0 until numParcelas) {
            //Cria a conta
            var contaLocal = Conta(conta)

            //Pega a data de vencimento
            contaLocal.dataVencimento = calendario.time

            //Salva a conta
            if (!contaDao.adicionar(contaLocal)) {
                erro = true
            }

            //No final aumenta o numero de dias para a nova conta
            calendario.add(Calendar.DAY_OF_YEAR, periodo!!)
        }

        return !erro
    }

    fun pegarTodasMes() : List<Conta> {
        if (ApplicationCustom_.getInstance().sessaoAtual == null)
            return ArrayList<Conta>()

        return contaDao.pesquisarPorMes(ApplicationCustom_.getInstance().sessaoAtual)
    }

    fun pegarTodasVencidas() : List<Conta> {
        if (ApplicationCustom_.getInstance().sessaoAtual == null)
            return ArrayList<Conta>()

        return contaDao.pesquisarPorVencidas(ApplicationCustom_.getInstance().sessaoAtual)
    }

    fun pegarProximasVencer() : List<Conta> {
        if (ApplicationCustom_.getInstance().sessaoAtual == null)
            return ArrayList<Conta>()

        return contaDao.pesquisarPorProximasVencer(ApplicationCustom_.getInstance().sessaoAtual)
    }

    fun pegarComFiltro() : List<Conta> {
        if (ApplicationCustom_.getInstance().sessaoAtual == null)
            return ArrayList<Conta>()

        return contaDao.pesquisarTodasAberto(ApplicationCustom_.getInstance().sessaoAtual)
    }
}
package com.erivan.santos.contasapp.Model

import com.erivan.santos.contasapp.ApplicationCustom_
import com.erivan.santos.contasapp.POJO.Conta
import com.erivan.santos.contasapp.Repository.ContaDao

class ContaModel {
    var contaDao = ContaDao()

    fun pegarTodas() : List<Conta> {
        if (ApplicationCustom_.getInstance().sessaoAtual == null)
            return ArrayList<Conta>()

        return contaDao.pesquisarTodasAberto(ApplicationCustom_.getInstance().sessaoAtual)
    }

    fun adicionar(conta: Conta) : Boolean {
        if (ApplicationCustom_.getInstance().sessaoAtual == null)
            return false

        conta.usuario = ApplicationCustom_.getInstance().sessaoAtual

        return contaDao.adicionar(conta)
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
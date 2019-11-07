package com.erivan.santos.contasapp.Presenter

import com.erivan.santos.contasapp.Model.ContaModel
import com.erivan.santos.contasapp.POJO.Conta
import com.erivan.santos.contasapp.View.ContaView
import net.grandcentrix.thirtyinch.TiPresenter

class ContaPresenter : TiPresenter<ContaView>() {
    val contaModel = ContaModel()

    fun adicionarConta(conta: Conta, numParcelas: Int?, periodo: Int?) {
        val ret = contaModel.adicionar(conta, numParcelas, periodo)

        if (ret)
            view!!.adicionou()
        else
            view!!.erro(500, "Erro ao adicionar a conta")
    }

    fun carregarTodas() {
        view!!.lista(0, contaModel.pegarTodas())
    }

    fun carregarTodasMes() {
        view!!.lista(1, contaModel.pegarTodas())
    }

    fun carregarVencidas() {
        view!!.lista(2, contaModel.pegarTodas())
    }

    fun carregarProximasVencer() {
        view!!.lista(3, contaModel.pegarTodas())
    }

    fun carregarComFiltro() {
        view!!.lista(4, contaModel.pegarTodas())
    }
}
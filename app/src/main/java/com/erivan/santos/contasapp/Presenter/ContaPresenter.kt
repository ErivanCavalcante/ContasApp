package com.erivan.santos.contasapp.Presenter

import com.erivan.santos.contasapp.Model.ContaModel
import com.erivan.santos.contasapp.POJO.Conta
import com.erivan.santos.contasapp.View.ContaView
import net.grandcentrix.thirtyinch.TiPresenter

class ContaPresenter : TiPresenter<ContaView>() {
    val contaModel = ContaModel()

    override fun onAttachView(view: ContaView) {
        super.onAttachView(view)

        view.viewCriada()
    }

    fun adicionarConta(conta: Conta, numParcelas: Int?, periodo: Int?) {
        val ret = contaModel.adicionar(conta, numParcelas, periodo)

        if (ret)
            view!!.adicionou()
        else
            view!!.erro(500, "Erro ao adicionar a conta")
    }

    fun removerConta(conta: Conta) {
        val ret = contaModel.remover(conta)

        if (ret)
            view!!.removeu()
        else
            view!!.erro(500, "Erro ao remover a conta")
    }

    fun carregarTodas() {
        view!!.lista(0, contaModel.pegarTodas(0))
    }

    fun carregarTodasMes() {
        view!!.lista(1, contaModel.pegarTodas(1))
    }

    fun carregarVencidas() {
        view!!.lista(2, contaModel.pegarTodas(2))
    }

    fun carregarProximasVencer() {
        view!!.lista(3, contaModel.pegarTodas(3))
    }

    fun carregarComFiltro(descricao: String) {
        view!!.lista(4, contaModel.pegarTodasFiltro(descricao))
    }
}
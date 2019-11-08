package com.erivan.santos.contasapp.View

import com.erivan.santos.contasapp.POJO.Conta

interface ContaView : BaseView {
    fun adicionou()
    fun removeu()
    fun lista(tipo: Int ,contas: List<Conta>)
}
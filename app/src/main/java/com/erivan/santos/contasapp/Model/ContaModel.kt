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
}
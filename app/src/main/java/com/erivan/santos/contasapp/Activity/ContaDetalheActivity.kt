package com.erivan.santos.contasapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erivan.santos.contasapp.POJO.Conta
import com.erivan.santos.contasapp.R
import com.erivan.santos.contasapp.Repository.ContaDao
import kotlinx.android.synthetic.main.activity_conta_detalhe.*
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.Extra
import java.text.SimpleDateFormat

@EActivity(R.layout.activity_conta_detalhe)
open class ContaDetalheActivity : AppCompatActivity() {

    @Extra("conta")
    lateinit var conta: Conta

    override fun onBackPressed() {
        ListaActivity_.intent(this)
                        .start();

        finish()
    }

    @AfterViews
    fun carrega() {
        txtTitulo.text = "Nome: ${conta.titulo}"
        txtDescricao.text = "Descrição: " + conta.descricao
        txtValor.text = "R$ ${conta.valor}"
        txtDataVencimento.text = "Data de vencimento: " + SimpleDateFormat("dd/MM/yyyy").format(conta.dataVencimento)

        cbAvisarVencimento.setChecked(conta.avisarVencimento)
        cbPago.setChecked(conta.pago)

        //Para nao ter q criar uma funcao no presenter a modficacao do banco de dados sera feita aqui mesmo
        cbAvisarVencimento.setOnCheckedChangeListener{view, isChecked ->
            val dao = ContaDao()
            val c = dao.pesquisarPorId(conta.id)

            c.avisarVencimento = isChecked

            dao.atualizar(c)
        }

        cbPago.setOnCheckedChangeListener{view, isChecked ->
            val dao = ContaDao()
            val c = dao.pesquisarPorId(conta.id)

            c.pago = isChecked

            dao.atualizar(c)
        }
    }

}

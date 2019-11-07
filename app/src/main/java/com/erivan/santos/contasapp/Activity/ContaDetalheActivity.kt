package com.erivan.santos.contasapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erivan.santos.contasapp.POJO.Conta
import com.erivan.santos.contasapp.R
import kotlinx.android.synthetic.main.activity_conta_detalhe.*
import org.androidannotations.annotations.AfterExtras
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.Extra
import java.text.SimpleDateFormat

@EActivity(R.layout.activity_conta_detalhe)
open class ContaDetalheActivity : AppCompatActivity() {

    @Extra("conta")
    lateinit var conta: Conta

    override fun onBackPressed() {
        finish()
    }

    @AfterExtras
    fun carrega() {
        txtTitulo.text = conta.titulo
        txtDescricao.text = conta.descricao
        txtValor.text = "R$ ${conta.valor}"
        txtDataVencimento.text = SimpleDateFormat("dd/MM/yyyy").format(conta.dataVencimento)
        cbAvisarVencimento.setChecked(conta.avisarVencimento)
        cbPago.setChecked(conta.pago)
    }

}

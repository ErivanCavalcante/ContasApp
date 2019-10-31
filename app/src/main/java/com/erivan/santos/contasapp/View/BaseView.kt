package com.erivan.santos.contasapp.View

import net.grandcentrix.thirtyinch.TiView

interface BaseView : TiView {
    fun erro(code: Int, erro: String)
}
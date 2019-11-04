package com.erivan.santos.contasapp.Presenter

import com.erivan.santos.contasapp.Model.UsuarioModel
import com.erivan.santos.contasapp.POJO.Usuario
import com.erivan.santos.contasapp.View.UsuarioView
import net.grandcentrix.thirtyinch.TiPresenter

class UsuarioPresenter : TiPresenter<UsuarioView>() {
    val model = UsuarioModel()

    //Tenta fazer o login
    fun logar(email: String, senha: String) {
        val usuario: Usuario? = model.tentaLogar(email, senha)

        if (usuario == null) {
            view!!.erro(500, "Usuario não exsite")
            return
        }

        view!!.loginOk()
    }

    fun adicionar(user: Usuario) {
        val ok = model.adicionaUsuario(user)

        if (!ok) {
            view!!.erro(500, "Erro ao adicionar o usuario")
            return
        }

        view!!.adicionado()
    }
}
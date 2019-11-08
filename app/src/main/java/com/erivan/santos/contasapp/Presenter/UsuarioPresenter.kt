package com.erivan.santos.contasapp.Presenter

import com.erivan.santos.contasapp.ApplicationCustom
import com.erivan.santos.contasapp.Model.UsuarioModel
import com.erivan.santos.contasapp.POJO.Usuario
import com.erivan.santos.contasapp.View.UsuarioView
import net.grandcentrix.thirtyinch.TiPresenter

class UsuarioPresenter : TiPresenter<UsuarioView>() {
    val model = UsuarioModel()

    //Ao iniciar a view tenta logar por preference
    override fun onAttachView(view: UsuarioView) {
        super.onAttachView(view)

        val ok = model.tentaLogarPreferencia()

        //Se achou algum usuario logado manda pra tela inical
        if (ok) {
            view?.loginOk()
        }
    }

    //Tenta fazer o login
    fun logar(email: String, senha: String) {
        val ok = model.tentaLogar(email, senha)

        if (!ok) {
            view?.erro(500, "Usuario não exsite")
            return
        }

        view?.loginOk()
    }

    fun adicionar(user: Usuario) {
        val ok = model.adicionaUsuario(user)

        if (!ok) {
            view?.erro(500, "Erro ao adicionar o usuario")
            return
        }

        view?.adicionado()
    }
}
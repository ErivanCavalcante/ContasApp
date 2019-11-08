package com.erivan.santos.contasapp.Model

import android.content.Context
import com.erivan.santos.contasapp.ApplicationCustom
import com.erivan.santos.contasapp.POJO.Usuario
import com.erivan.santos.contasapp.Repository.UserDao

class UsuarioModel {

    var userDao: UserDao = UserDao()

    //Tenta logar por aruqivo de preferencia
    fun tentaLogarPreferencia() : Boolean {
        val shared = ApplicationCustom.getInstance().getSharedPreferences("com.erivan.santos.contasapp.shared", Context.MODE_PRIVATE)

        val userId = shared.getInt("user_id", 0)

        //Nenhum usuario logado
        if (userId == 0)
            return false

        //Procura no banco de dados o usuario
        val user = userDao.queryForId(userId);

        //Se achou algum usuario logado manda pra tela inical
        if (user != null) {
            //Salva a secao atual
            ApplicationCustom.getInstance().sessaoAtual = user

            return true
        }

        return false
    }

    fun tentaLogar(email: String, senha: String) : Boolean {
        //Valida as entradas
        if (email.isEmpty() || senha.isEmpty())
            return false

        //Procura no banco de dados pelo usuario
        val user = userDao.tentaLogar(email, senha)

        //Se achou algum usuario logado manda pra tela inical
        if (user != null) {
            //Salva a secao atual
            ApplicationCustom.getInstance().sessaoAtual = user

            return true
        }

        return false
    }

    fun adicionaUsuario(user: Usuario) : Boolean {
        val ret = userDao.adicionar(user)

        return ret
    }
}
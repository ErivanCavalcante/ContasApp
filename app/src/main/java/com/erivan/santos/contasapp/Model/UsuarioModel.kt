package com.erivan.santos.contasapp.Model

import com.erivan.santos.contasapp.POJO.Usuario
import com.erivan.santos.contasapp.Repository.UserDao

class UsuarioModel {

    var userDao: UserDao = UserDao()

    fun tentaLogar(email: String, senha: String) : Usuario? {
        //Valida as entradas
        if (email.isEmpty() || senha.isEmpty())
            return null

        return userDao.tentaLogar(email, senha)
    }

    fun adicionaUsuario(user: Usuario) : Boolean {
        val ret = userDao.adicionar(user)

        return ret
    }
}
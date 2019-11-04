package com.erivan.santos.contasapp.Repository;

import com.erivan.santos.contasapp.POJO.Usuario;
import com.j256.ormlite.dao.BaseDaoImpl;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class UserDao extends BaseDaoImpl<Usuario, Integer> {
    public UserDao() throws SQLException {
        super(OrmliteHelper.getInstance().getConnectionSource(), Usuario.class);
    }

    public boolean adicionar(@NotNull Usuario usuario) {
        try {
            return create(usuario) > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean usuarioJaExiste(@NotNull Usuario usuario) {
        try {
            return queryForEq("emial", usuario.getEmail()).size() > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Usuario tentaLogar(String email, String senha) {
        try {
            return queryBuilder().where().eq("email", email).and().eq("senha", senha).queryForFirst();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

package com.erivan.santos.contasapp;

import android.app.Application;
import android.content.SharedPreferences;

import com.erivan.santos.contasapp.POJO.Usuario;

import org.androidannotations.annotations.EApplication;

@EApplication
public class ApplicationCustom extends Application {

    private static ApplicationCustom app = null;

    private Usuario sessaoAtual = null;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
    }

    public synchronized static ApplicationCustom getInstance() {
        if (app == null)
            app = new ApplicationCustom();

        return app;
    }

    public Usuario getSessaoAtual() {
        return sessaoAtual;
    }

    public void setSessaoAtual(Usuario usuario) {
        sessaoAtual = usuario;

        SharedPreferences shared = getSharedPreferences("com.erivan.santos.contasapp.shared", MODE_PRIVATE);

        if (usuario == null) {
            shared.edit().putInt("user_id", 0)
                        .apply();
        }
        else {
            shared.edit().putInt("user_id", usuario.getId())
                        .apply();
        }
    }
}

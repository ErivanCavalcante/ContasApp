package com.erivan.santos.contasapp;

import android.app.Application;

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
    }
}

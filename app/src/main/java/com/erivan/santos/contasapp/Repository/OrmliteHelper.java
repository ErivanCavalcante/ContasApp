package com.erivan.santos.contasapp.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.erivan.santos.contasapp.ApplicationCustom;
import com.erivan.santos.contasapp.POJO.Conta;
import com.erivan.santos.contasapp.POJO.Usuario;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class OrmliteHelper extends OrmLiteSqliteOpenHelper {
    private static OrmliteHelper instance = null;

    public OrmliteHelper(Context context, String databaseName, int databaseVersion) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Usuario.class);
            TableUtils.createTableIfNotExists(connectionSource, Conta.class);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Usuario.class, true);
            TableUtils.dropTable(connectionSource, Conta.class, true);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        onCreate(database, connectionSource);
    }

    //Singleton
    public synchronized static OrmliteHelper getInstance() {
        if (instance == null)
            instance = new OrmliteHelper(ApplicationCustom.getInstance(), "conta-app.db", 1);

        return instance;
    }
}

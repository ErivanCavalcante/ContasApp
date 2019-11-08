package com.erivan.santos.contasapp.Receiver

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.erivan.santos.contasapp.Repository.ContaDao
import com.erivan.santos.contasapp.Repository.UserDao

class AvisarVencimentoWorker(appContext: Context, params: WorkerParameters) : Worker(appContext, params) {
    override fun doWork(): Result {
        //Pega o id do usuario logado
        val userId = applicationContext.getSharedPreferences("com.erivan.santos.contasapp.shared", Context.MODE_PRIVATE)
                                        .getInt("user_id", 0)

        //Se o id for valido
        if (userId > 0) {
            val userDao = UserDao()

            //Pega o usuario
            var usuario = userDao.queryForId(userId)

            if(usuario != null) {
                val contaDao = ContaDao()

                //Procura as contas q estao proximas de vencer
                var contas = contaDao.pesquisarPorProximasVencer(usuario)

                val nmn = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                //Loop em todas as contas para mostrar as notificacoes
                for (conta in contas) {
                    //So avisa se tiver a permissao
                    if (conta.avisarVencimento)
                        criaNotificacao(nmn, "Conta perto de vencer", "${conta.titulo} \nR$ ${conta.valor}")
                }
            }
        }

        return Result.success()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    internal fun criaCanalNotificacao(id: String, nome: String, prioridade: Int) {
        val canal = NotificationChannel(id, nome, prioridade)

        val nmn = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        nmn.createNotificationChannel(canal)
    }

    internal fun criaNotificacao(nmn: NotificationManager?, titulo: String, texto: String) {
        //Se for o android o entao cria o canal
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            criaCanalNotificacao(
                "CANAL_ENVIO_NS",
                "Envio NS",
                NotificationManager.IMPORTANCE_DEFAULT
            )
        }

        //Cria o builder da notificiacao
        val notificacao = NotificationCompat.Builder(applicationContext, "CANAL_ENVIO_NS")

        notificacao
            .setContentTitle(titulo)
            .setContentText(texto)
            .setDefaults(Notification.DEFAULT_ALL)
            .setDeleteIntent(null)
            .setGroup("com.erivan.santos.contasapp.notificacao")

        //Testa se o manager existe
        assert(nmn != null)

        nmn!!.notify(2, notificacao.build())
    }
}
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
import com.erivan.santos.contasapp.R
import com.erivan.santos.contasapp.Repository.ContaDao
import com.erivan.santos.contasapp.Repository.UserDao
import kotlin.random.Random
import kotlin.random.nextUInt

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

                if (contas?.size > 0) {
                    val nmn = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                    //Se for o android o entao cria o canal
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        criaCanalNotificacao(nmn,"CANAL_LEMBRETE_CONTA", "Lembrete pagamento", NotificationManager.IMPORTANCE_DEFAULT)
                    }

                    //Sorteia uma conta a vencer para ser mostrada no momento
                    val random = Random(System.currentTimeMillis())
                    var pos = random.nextInt(contas.size)

                    if (pos < 0)
                        pos = 0

                    val contaAtual = contas[pos]

                    //So avisa se tiver a permissao
                    if (contaAtual.avisarVencimento)
                        criaNotificacao(nmn, "Conta perto de vencer", "${contaAtual.titulo} (${contaAtual.descricao}) R$ ${contaAtual.valor}")
                }
            }
        }

        return Result.success()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    internal fun criaCanalNotificacao(nmn: NotificationManager?, id: String, nome: String, prioridade: Int) {
        val canal = NotificationChannel(id, nome, prioridade)

        nmn!!.createNotificationChannel(canal)
    }

    internal fun criaNotificacao(nmn: NotificationManager?, titulo: String, texto: String) {
        //Cria o builder da notificiacao
        val notificacao = NotificationCompat.Builder(applicationContext, "CANAL_LEMBRETE_CONTA")

        notificacao
            .setSmallIcon(R.drawable.ic_payment)
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
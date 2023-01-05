package id.ac.unand.uas_ptb_2007

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.unand.klp_7_ptb_tb.Network.NetworkConfig
import id.ac.unand.uas_ptb_2007.databinding.ActivityIsiResponBinding
import id.ac.unand.uas_ptb_2007.models.IsiResponResponse
import id.ac.unand.uas_ptb_2007.network.KpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IsiRespon : AppCompatActivity() {
    lateinit var binding: ActivityIsiResponBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIsiResponBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getRespon = intent.getStringExtra("note")
        binding.editresponbro.setText(getRespon)

        val backrespon = binding.backBtn

        backrespon.setOnClickListener{
            onBackPressed()
        }
//        fun createNotificationChannel() {
//            val CHANNEL_ID = "Isi Respon"
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                val name = "Isi Respon"
//                val descriptionText = "Notifikasi Dosen Mengisi Respon"
//                val importance = NotificationManager.IMPORTANCE_HIGH
//                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                    description = descriptionText
//                }
//                // Register the channel with the system
//                val notificationManager: NotificationManager =
//                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//                notificationManager.createNotificationChannel(channel)
//            }
//            // Create an explicit intent for an Activity in your app
//            val intent = Intent(this, DetailLogbook::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            }
//            val pendingIntent: PendingIntent =
//                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//
//            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_baseline_done_all_24)
//                .setContentTitle("Respon Terkirim")
//                .setContentText("Respon Anda Berhasil Terkirim")
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                // Set the intent that will fire when the user taps the notification
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true)
//            with(NotificationManagerCompat.from(this)) {
//                // notificationId is a unique int for each notification that you must define
//                notify(1234, builder.build())
//            }
//        }
//        val btntambahrespon = binding.tambahrespon
//        btntambahrespon.setOnClickListener{
//            val pindah = Intent(this,DashboardKp::class.java)
//            startActivity(pindah)
//            createNotificationChannel()
//        }
    }
    fun buttonOnclicked(view: View) {
        val sharedTokennya = getSharedPreferences("sharedpref",Context.MODE_PRIVATE)?:return
        val logbookpref = getSharedPreferences("logbookpref",Context.MODE_PRIVATE)?:return
        val token = sharedTokennya.getString("token",null)
        val id = sharedTokennya.getInt("id",2)
        val idl = logbookpref.getString("id_logbook",null)
        val responn = binding.editresponbro.text.toString()
        Log.d("respon-debug","$responn|Bearer $token | $id | $idl")
        val client: KpClient = NetworkConfig().getService()
        val call: Call<IsiResponResponse> = client.isirespon(token = "Bearer"+token,id,idl,1,responn)
        call.enqueue(object :Callback<IsiResponResponse>{
            override fun onResponse(
                call: Call<IsiResponResponse>,
                response: Response<IsiResponResponse>
            ) {
                Log.d("isi-debug","respon : $responn")
                val respon : IsiResponResponse? = response.body()
                if (respon!=null && respon.status =="success"){
                    Log.d("isi-respon","$token|$respon")
                    intent = Intent(applicationContext,DetailLogbook::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    Toast.makeText(this@IsiRespon, "Berhasil DiRespon", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this@IsiRespon, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<IsiResponResponse>, t: Throwable) {
                Log.d("update-debug",t.localizedMessage)
            }

        })
    }
}
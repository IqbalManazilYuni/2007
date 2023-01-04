package id.ac.unand.uas_ptb_2007

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.unand.uas_ptb_2007.databinding.ActivityIsiResponBinding

class IsiRespon : AppCompatActivity() {
    lateinit var binding: ActivityIsiResponBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIsiResponBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backrespon = binding.backBtn

        backrespon.setOnClickListener{
            onBackPressed()
        }
        fun createNotificationChannel() {
            val CHANNEL_ID = "Isi Respon"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "Isi Respon"
                val descriptionText = "Notifikasi Dosen Mengisi Respon"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
            // Create an explicit intent for an Activity in your app
            val intent = Intent(this, DetailLogbook::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_done_all_24)
                .setContentTitle("Respon Terkirim")
                .setContentText("Respon Anda Berhasil Terkirim")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(1234, builder.build())
            }
        }
        val btntambahrespon = binding.tambahrespon
        btntambahrespon.setOnClickListener{
            val pindah = Intent(this,DashboardKp::class.java)
            startActivity(pindah)
            createNotificationChannel()
        }
    }
}
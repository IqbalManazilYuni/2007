package id.ac.unand.uas_ptb_2007

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ac.unand.uas_ptb_2007.databinding.ActivityDetailLogbookBinding

class DetailLogbook : AppCompatActivity() {
    lateinit var binding: ActivityDetailLogbookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailLogbookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val back = binding.backBtn
        back.setOnClickListener{
            val intent = Intent(this@DetailLogbook,DashboardKp::class.java)
            startActivity(intent)
        }
        val isirespon = binding.isiRespon
        isirespon.setOnClickListener{
            val intent = Intent(this@DetailLogbook,IsiRespon::class.java)
            startActivity(intent)
        }
    }
}
package id.ac.unand.uas_ptb_2007

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.ac.unand.klp_7_ptb_tb.Network.NetworkConfig
import id.ac.unand.uas_ptb_2007.databinding.ActivityDetailLogbookBinding
import id.ac.unand.uas_ptb_2007.models.DetailLogBookResponse
import id.ac.unand.uas_ptb_2007.network.KpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            val respon = binding.respon.text.toString()
            intent.putExtra("note",respon)
            startActivity(intent)
        }
        val sharedTokennya = getSharedPreferences("sharedpref",Context.MODE_PRIVATE)?: return
        val logbookpref = getSharedPreferences("logbookpref",Context.MODE_PRIVATE)?:return

        val token = sharedTokennya.getString("token",null)
        val id = sharedTokennya.getInt("id",2)
        val idl = logbookpref.getString("id_logbook",null)
        Log.d("Detail-logbook-debug","respon "+idl.toString())

        val client: KpClient=NetworkConfig().getService()
        val call:Call<DetailLogBookResponse> = client.detailLogbookMaha(token = "Bearer"+token,id,idl)
        Log.d("Detail-logbook-debug","respon "+token.toString())
        call.enqueue(object :Callback<DetailLogBookResponse>{
            override fun onResponse(
                call: Call<DetailLogBookResponse>,
                response: Response<DetailLogBookResponse>
            ) {
                val respon = response.body()
                Log.d("Detail-logbook-debug-selalu-saja",respon.toString())
                if(respon!=null){
                    Log.d("Detail-logbook-debug-selalu",respon.toString())
                    val report = respon.reportTitle
                    binding.valueDosenDetail.text = report.toString()
                    val studentid = respon.studentId
                    binding.valueNimDetail.text = studentid.toString()
                    val tanggal = respon.logbook?.date
                    binding.tanggallogbok.text = tanggal.toString()
                    val kegiatan = respon.logbook?.activities
                    binding.kegiatanku.text = kegiatan.toString()
                    val note = respon.logbook?.note
                    binding.respon.text = note.toString()
                }
            }

            override fun onFailure(call: Call<DetailLogBookResponse>, t: Throwable) {
                Log.d("detail-debug", t.localizedMessage)
            }

        })
    }
}
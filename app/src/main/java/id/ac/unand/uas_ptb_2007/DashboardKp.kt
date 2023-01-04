package id.ac.unand.uas_ptb_2007

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.unand.klp_7_ptb_tb.Network.NetworkConfig
import id.ac.unand.uas_ptb_2007.adapter.LogBookAdapter
import id.ac.unand.uas_ptb_2007.data.datalogbook
import id.ac.unand.uas_ptb_2007.databinding.ActivityDashboardKpBinding
import id.ac.unand.uas_ptb_2007.models.ListLogBookResponse
import id.ac.unand.uas_ptb_2007.models.LogbooksItem
import id.ac.unand.uas_ptb_2007.models.LogoutResponse
import id.ac.unand.uas_ptb_2007.network.KpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardKp : AppCompatActivity() {
    lateinit var adapter: LogBookAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var binding:ActivityDashboardKpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardKpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter : LogBookAdapter = LogBookAdapter()

        val sharedPrefToken = getSharedPreferences("sharedpref",Context.MODE_PRIVATE)?:return
        val token = sharedPrefToken.getString("token",null)
        val sharedPref = getSharedPreferences("mahapref",Context.MODE_PRIVATE)?:return
        val id = sharedPref.getInt("id",5)

        val data = ArrayList<LogbooksItem>()
        recyclerView = findViewById(R.id.recycler_view)

        Log.d("list-book-debug",token.toString())
        val client: KpClient = NetworkConfig().getService()
        val call: Call<ListLogBookResponse> = client.listlogbook(token = "Bearer " + token, id)

        call.enqueue(object : Callback<ListLogBookResponse>{
            override fun onResponse(
                call: Call<ListLogBookResponse>,
                response: Response<ListLogBookResponse>
            ) {
                val respon = response.body()
                if(respon != null){
                    val list : List<LogbooksItem> = respon.logbooks as List<LogbooksItem>
                    adapter.setlistLogbook(list)
                    Log.d("list-book-debug", list.toString())
                }
                Log.d("list-book-debug", respon?.logbooks?.size.toString())
                Log.d("list-book-debug", "respon : " + respon?.logbooks.toString())
            }

            override fun onFailure(call: Call<ListLogBookResponse>, t: Throwable) {
                Log.d("list-book-debug", t.localizedMessage)
            }

        })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : LogBookAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@DashboardKp,DetailLogbook::class.java)
//
                startActivity(intent)
            }
        })

        val btnlogout = binding.btnlogout
        btnlogout.setOnClickListener {
            val sharedPref = getSharedPreferences("sharedpref", Context.MODE_PRIVATE)
            val token = sharedPref.getString("token", "")
            if( token != null) {
                val client: KpClient = NetworkConfig().getService()
                val call: Call<LogoutResponse> = client.logout(token = "Bearer "+token)

                call.enqueue(object: Callback<LogoutResponse> {
                    override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                        Log.d("logout-debug",t.localizedMessage)
//                Toast.makeText(this@LoginActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                    override fun onResponse(call: Call<LogoutResponse>, response: Response<LogoutResponse>) {

                        val respon = response.body()

                        val sharedPref = getSharedPreferences("sharedpref", Context.MODE_PRIVATE) ?:return
                        with (sharedPref.edit()) {
                            putString("TOKEN", null)
                            apply()
                        }
                        Log.d("logout-debug",  "respon : "+ respon )
                        Toast.makeText(this@DashboardKp, "Logout Anda Berhasil", Toast.LENGTH_SHORT).show()
                        val logout = Intent(this@DashboardKp, MainActivity::class.java)
                        logout.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(logout)
                        finish()
                    }

                })
            }

        }
    }


}
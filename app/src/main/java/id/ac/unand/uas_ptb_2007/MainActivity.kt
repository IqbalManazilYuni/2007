package id.ac.unand.uas_ptb_2007

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import id.ac.unand.klp_7_ptb_tb.Network.NetworkConfig
import id.ac.unand.uas_ptb_2007.databinding.ActivityMainBinding
import id.ac.unand.uas_ptb_2007.models.LoginResponse
import id.ac.unand.uas_ptb_2007.network.KpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            val TAG="FireBase-Debug"
//            if (!task.isSuccessful) {
//                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//            // Get new FCM registration token
//            val token = task.result
//            // Log and toast
//            Log.d(TAG, token)
////            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
//        })
        val btnlogin = binding.btnlogin

        //cek ada token atau ndak, kalo ada langsung ke MainActivity
        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
        val ada = sharedPref.getString("token",null)

        if (ada!=null){
            intent = Intent(applicationContext, DashboardKp::class.java)
            startActivity(intent)
            finish()
        }

        btnlogin.setOnClickListener{
            val username = binding.teksUsername.getText().toString()
            val password = binding.teksPassword.getText().toString()
            Log.d("login debug", username + " " + password)

            val client: KpClient = NetworkConfig().getService()

            val call: Call<LoginResponse> = client.login(username, password)

            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    //ambil respon login
                    val respon: LoginResponse? = response.body();
                    if (respon != null && respon.status == "success" ) {

                        //ambil Token
                        val token = respon.authorisation?.token

                        Log.d("login-debug",username +":"+ password +"|"+ token + "|" + respon)

                        //Shared Preference
                        val sharedPref = getSharedPreferences("sharedpref", Context.MODE_PRIVATE) ?:return
                        with (sharedPref.edit()) {
                            putString("token", token)
                            apply()
                        }

                        Toast.makeText(this@MainActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()

                        intent = Intent(applicationContext, DashboardKp::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(this@MainActivity, "Username atau Password yang anda masukkan salah", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}
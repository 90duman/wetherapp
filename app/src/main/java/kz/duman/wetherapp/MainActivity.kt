package kz.duman.wetherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kz.duman.wetherapp.databinding.ActivityMainBinding
import org.json.JSONObject

const val API_KEY = "1fd90f74ae8947ba813105827231701&q"
class MainActivity : AppCompatActivity() {
    lateinit var resultField: TextView

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        resultField = findViewById(R.id.textView)
        binding.bGet.setOnClickListener {
            getResult("Almaty")
        }
    }

    private fun getResult(name: String) {
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$API_KEY&q=Almaty&aqi=no"

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val obj  = JSONObject(response)
                val temp = obj.getJSONObject("current").get("temp_c").toString()
                resultField.text = temp
                Log.d("MyLog", "Response: $temp")
            },
            {
                Log.d("MyLog", "Volley error: $it")
            }
        )
        queue.add(stringRequest)
    }
}
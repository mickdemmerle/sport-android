package michael.demmerle.sport

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun loginAction(view: View) {
        executeApiRequest()
    }

    private fun executeApiRequest() {

        val queue = Volley.newRequestQueue(this)

        val email = editText_email.text.toString()
        val password = editText_password.text.toString()

        val params = HashMap<String, String>()
        params.put("email", email)
        params.put("password", password)

        val JsonRequest = object : JsonObjectRequest(Endpoints.URL_POST_LOGIN, JSONObject(params),
                Response.Listener<JSONObject> { response ->
                    try {
                        executeApiResponse(response)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                object : Response.ErrorListener {
                    override fun onErrorResponse(volleyError: VolleyError) {
                        //@todo: gérer les erreurs API REST
                    }
                }) {}

        queue.add(JsonRequest)
    }

    private fun executeApiResponse(response: JSONObject) {

        val homepage = Intent(this, HomepageActivity::class.java)

        homepage.putExtra(HomepageActivity.OBJECT_USER_NAME, response.toString())

        startActivity(homepage)
    }
}

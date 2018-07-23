package michael.demmerle.sport

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_homepage.*
import michael.demmerle.sport.Global.Model.Member
import org.json.JSONObject

class HomepageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        displayHomepage()
    }

    companion object {
        const val OBJECT_USER_NAME = "user"
    }

    fun displayHomepage() {

        val json: String = intent.getStringExtra(OBJECT_USER_NAME)

        val moshi: Moshi = Moshi.Builder().build();

        val jsonAdapter: JsonAdapter<Member> = moshi.adapter(Member::class.java)
        val member: Member? = jsonAdapter.fromJson(json)

        textView_title.text = member?.firstName ?: ""
    }
}

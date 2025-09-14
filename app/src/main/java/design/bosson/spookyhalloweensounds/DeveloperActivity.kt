package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import design.bosson.spookyhalloweensounds.BuildConfig

class DeveloperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)

        val versionText: TextView = findViewById(R.id.versionText)
        val appVersion = BuildConfig.VERSION_NAME
        val buildDate = BuildConfig.BUILD_DATE
        versionText.text = "App Version $appVersion $buildDate"

        val privacyText: TextView = findViewById(R.id.privacypolicy)
        privacyText.movementMethod = android.text.method.LinkMovementMethod.getInstance()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        //fab.setBackgroundDrawable(ColorDrawable(Color.parseColor("#784579")))
        fab.setOnClickListener {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "message/rfc822"
            i.putExtra(Intent.EXTRA_EMAIL, arrayOf("sean@bosson.design"))
            i.putExtra(Intent.EXTRA_SUBJECT, "")
            i.putExtra(Intent.EXTRA_TEXT, "")
            try {
                startActivity(Intent.createChooser(i, "Email Bosson.Design"))
            } catch (ex: android.content.ActivityNotFoundException) {
                Toast.makeText(this@DeveloperActivity, "There are no email clients installed.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
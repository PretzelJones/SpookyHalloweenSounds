package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class DeveloperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setBackgroundDrawable(ColorDrawable(Color.parseColor("#784579")))
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

    //prevent reload on orientation change
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

}
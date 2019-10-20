package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast

class DeveloperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)

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
/*
    //prevent reload on orientation change
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

    /*
        if (id == R.id.payment){
            val intent = Intent(this, PaymentActivity::class.java)
            this.startActivity(intent)

        } else if
                */
    if (id == R.id.action_settings) {
        val intent = Intent(this, DeveloperActivity::class.java)
        this.startActivity(intent)

    } else if (id == R.id.share) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.google_play_store))
        startActivity(Intent.createChooser(sharingIntent, "Share via"))

    } else if (id == R.id.secret) {
        val intent = Intent(this, SecretActivity::class.java)
        this.startActivity(intent)
    }

        return true

    }
}
package design.bosson.spookyhalloweensounds

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DeveloperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)

        val privacypolicy = findViewById<TextView>(R.id.privacypolicy)
        val textViewSite = findViewById<TextView>(R.id.textViewSite)
        val textViewEmail = findViewById<TextView>(R.id.textViewEmail)
        // Set an OnClickListener to open the web link
        privacypolicy.setOnClickListener {
            // Define the web link URL
            val webLink = "https://docs.google.com/document/d/1GSII3CwCZ5sRwkRRzcBKNSAdhpMipyY0ltgSEivydt4/edit?usp=sharing"

            // Create an Intent to open the web link
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webLink))
            startActivity(intent)
        }
        textViewSite.setOnClickListener {
            // Define the web link URL
            val webLink = "https://facebook.com/bossondesign/"

            // Create an Intent to open the web link
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webLink))
            startActivity(intent)
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
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

        textViewEmail.setOnClickListener {
            // Get the email address from the string resource
            val emailAddress = resources.getString(R.string.email)

            // Create an Intent to send an email
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:$emailAddress")
            emailIntent.putExtra(
                Intent.EXTRA_SUBJECT,
                "Subject of the Email"
            ) // You can set a default subject here if needed

            // Check if there's an email app available to handle the intent
            if (emailIntent.resolveActivity(packageManager) != null) {
                startActivity(emailIntent)
            } else {
                // Handle the case where no email app is available
                // You can show a toast message or an error dialog
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

    when (item.itemId) {
        R.id.action_settings -> {
            val intent = Intent(this, DeveloperActivity::class.java)
            this.startActivity(intent)
        }
        R.id.share -> {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.google_play_store))
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }
    }
        return true
    }
}
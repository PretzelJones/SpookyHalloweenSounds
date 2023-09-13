package design.bosson.spookyhalloweensounds

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteConstraintException
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import java.io.*


@RequiresApi(Build.VERSION_CODES.Q)
fun saveRingtone(context: Context, title: String, rawResourceId: Int) {
    if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        return
    }

    // Check if the ringtone file already exists
    val ringtoneFilePath = "${Environment.getExternalStorageDirectory()}/${Environment.DIRECTORY_RINGTONES}/$title.mp3"
    val ringtoneFile = File(ringtoneFilePath)
    if (ringtoneFile.exists()) {
        // Display a notification to the user
        Toast.makeText(context, "$title is already saved!", Toast.LENGTH_LONG).show()
        return
    }

    val resolver = context.contentResolver

    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "$title.mp3")
        put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_RINGTONES)
        put(MediaStore.Audio.Media.IS_RINGTONE, true)
        put(MediaStore.Audio.Media.IS_NOTIFICATION, true)
        put(MediaStore.Audio.Media.IS_ALARM, true)
        put(MediaStore.Audio.Media.IS_MUSIC, false)
        put(MediaStore.MediaColumns.DATA, ringtoneFilePath)
    }
    var ringtoneUri: Uri? = null
    try {
        val ringtoneFileUri = resolver.insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, contentValues)
                ?: throw IOException("Failed to create new MediaStore record.")
        ringtoneUri = ringtoneFileUri
        val outputStream: OutputStream = (resolver.openOutputStream(ringtoneFileUri)
                ?: throw IOException("Failed to get output stream.")) as FileOutputStream
        val inputStream: InputStream = context.resources.openRawResource(rawResourceId)
        val buffer = ByteArray(1024)
        var read: Int
        while (inputStream.read(buffer).also { read = it } > 0) {
            outputStream.write(buffer, 0, read)
        }
        outputStream.close()
        inputStream.close()

            // Show a confirmation dialog to the user
            AlertDialog.Builder(context, R.style.RoundedAlertDialog)
                    .setMessage("$title Sound Saved!!")
                    .setPositiveButton("OK", null)
                    .show()

    } catch (e: IOException) {
        Toast.makeText(context, "Error saving sound: ${e.message}", Toast.LENGTH_LONG).show()
        if (ringtoneUri != null) {
            resolver.delete(ringtoneUri, null, null)
        }
    } catch (e: SQLiteConstraintException) {
        Toast.makeText(context, "Error saving sound: ${e.message}", Toast.LENGTH_LONG).show()
        if (ringtoneUri != null) {
            resolver.delete(ringtoneUri, null, null)
        }
    } catch (e: Exception) {
        Toast.makeText(context, "Error saving sound: ${e.message}", Toast.LENGTH_LONG).show()
        if (ringtoneUri != null) {
            resolver.delete(ringtoneUri, null, null)
        }
        } catch (e: Exception) {
            Toast.makeText(context, "Error saving sound: ${e.message}", Toast.LENGTH_LONG).show()
            if (ringtoneUri != null) {
                resolver.delete(ringtoneUri, null, null)
            }
        }
    }
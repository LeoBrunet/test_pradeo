package com.example.test_pradeo

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileInputStream
import java.security.MessageDigest
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtenir la ListView
        val listView = findViewById<ListView>(R.id.listView)

        // Obtenir la liste de toutes les applications installées
        val packageManager = packageManager
        val installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        installedApps.sortBy { it.name?.toString() }
        val apps = ArrayList<ApplicationInfo>()

        // Créer une liste d'objets ApplicationInfo à partir de la liste des applications installées
        for (app in installedApps) {
            val appInfo = ApplicationInfo(
                app.loadLabel(packageManager).toString(),
                app.loadIcon(packageManager),
                calculateHash(File(app.sourceDir))
            )
            apps.add(appInfo)
        }
        // Créer un ArrayAdapter à partir de la liste d'objets ApplicationInfo
        val adapter = ApplicationAdapter(this, apps)
        listView.adapter = adapter
    }

    fun calculateHash(file: File): String {
        val digest = MessageDigest.getInstance("SHA1")
        val inputStream = FileInputStream(file)
        val buffer = ByteArray(1024)
        var read = inputStream.read(buffer)
        while (read != -1) {
            digest.update(buffer, 0, read)
            read = inputStream.read(buffer)
        }
        inputStream.close()
        val hash = digest.digest()
        return hash.joinToString("") { String.format("%02x", it) }
    }

    fun submitHashToVirusTotal(hash: String): Response {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://www.virustotal.com/api/v3/files")
            .addHeader("x-apikey", "6922e0a636d4cea8324785f3f658460c1a2836204e31dd0974b09bb17d66913b")
            .post("""{"hash": "$hash"}""".toRequestBody("application/json".toMediaTypeOrNull()))
            .build()
        return client.newCall(request).execute()
    }

}

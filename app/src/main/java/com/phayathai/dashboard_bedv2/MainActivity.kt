package com.phayathai.dashboard_bedv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            savedInstanceState ?: supportFragmentManager.beginTransaction()
                .replace(R.id.container, RoomFragment())
                .commit()
        }
    }


}

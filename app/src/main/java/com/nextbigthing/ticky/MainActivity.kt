package com.nextbigthing.ticky

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.nextbigthing.ticky.databinding.ActivityMainBinding
import com.nextbigthing.ticky.room.AppDatabase
import com.nextbigthing.ticky.room.AppModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.submitButton.setOnClickListener {
            val firstName = binding.firstNameEditText.text.toString()
            val lastName = binding.lastNameEditText.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                val database = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "database-name"
                ).build()
                database.appDao().insertUser(AppModel(0, firstName = firstName, lastName = lastName))
            }
            refreshData()
        }
        refreshData()
    }

    private fun refreshData() {
        CoroutineScope(Dispatchers.IO).launch {
            val database = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()
            val users: List<AppModel> = database.appDao().getAll()
            Log.d("TAGGER", "onCreate: $users")
        }
    }
}
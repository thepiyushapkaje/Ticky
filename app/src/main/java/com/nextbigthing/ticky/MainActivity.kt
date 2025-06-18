package com.nextbigthing.ticky

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.nextbigthing.ticky.databinding.ActivityMainBinding
import com.nextbigthing.ticky.room.AppDatabase
import com.nextbigthing.ticky.room.AppModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), DeleteUser {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoListRecyclerAdapter: TodoListRecyclerAdapter

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
        refreshData()


        binding.floatingActionButton.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.add_task_layout, null)
            val taskTitleEditText = dialogView.findViewById<EditText>(R.id.taskTitleEditText)
            val submitButton = dialogView.findViewById<Button>(R.id.submitButton)
            dialogView.setBackgroundColor(Color.TRANSPARENT)
            val dialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .create()

            submitButton.setOnClickListener {
                val taskTitle = taskTitleEditText.text.toString()
                Log.d("TAGGER", "onCreate: $taskTitle")
                CoroutineScope(Dispatchers.IO).launch {
                    val database = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java, "database-name"
                    ).build()
                    database.appDao().insertUser(AppModel(0, task = taskTitle, false))
                    refreshData()
                }
                dialog.dismiss() // Optional: dismiss dialog after submitting
            }

            dialog.show()
        }


        binding.circularProgressBar.apply {
// Set Progress
            progress = 65f
            // or with animation
            setProgressWithAnimation(65f, 1000) // =1s

            // Set Progress Max
            progressMax = 100f

            // Set ProgressBar Color
            progressBarColor = Color.BLACK
            // or with gradient
            progressBarColorStart = Color.YELLOW
            progressBarColorEnd = Color.GREEN
            progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

            // Set background ProgressBar Color
            backgroundProgressBarColor = Color.GRAY

            // Set Width
            progressBarWidth = 7f // in DP
            backgroundProgressBarWidth = 3f // in DP

            // Other
            roundBorder = true
            startAngle = 0f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
        }
    }

    private fun refreshData() {
        CoroutineScope(Dispatchers.IO).launch {
            val database = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()
            val users: MutableList<AppModel> = database.appDao().getAll().toMutableList()

            runOnUiThread {
                todoListRecyclerAdapter = TodoListRecyclerAdapter(users, this@MainActivity) {
                    updateProgressBar() // gets called on checkbox change
                }
                binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.recyclerView.adapter = todoListRecyclerAdapter
                todoListRecyclerAdapter.notifyDataSetChanged()
                updateProgressBar() // Initial set
            }
        }
    }

    override fun deleteUserFromDb(user: AppModel) {
        CoroutineScope(Dispatchers.IO).launch {
            val database = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()
            database.appDao().deleteUser(user)
            refreshData()
        }
    }

    private fun updateProgressBar() {
        val total = todoListRecyclerAdapter.itemCount
        val checked = todoListRecyclerAdapter.getCheckedItemCount()
        val progress = if (total > 0) (checked.toFloat() / total) * 100 else 0f
        binding.circularProgressBar.setProgressWithAnimation(progress, 500)

        val formattedProgress = String.format("%.1f", progress)
        binding.progressTextView.text = "$formattedProgress %"
    }
}
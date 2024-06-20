package com.starkindustries.kotlincouroutines
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.starkindustries.kotlincouroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    var count:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.updateButton.setOnClickListener()
        {
            binding.countView.setText("${count++}")
        }
        binding.executeTask.setOnClickListener()
        {
//            longOperation()
        }
//        MainScope().launch(Dispatchers.Main)
//        {
//            task1()
//        }
//        MainScope().launch(Dispatchers.Main)
//        {
//            task2()
//        }
        MainScope().launch(Dispatchers.Main)
        {
            printFollower()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
//    fun longOperation()
//    {
//        CoroutineScope(Dispatchers.IO).launch()
//        {
//            for(i in 0..100000)
//                Log.d("threadName","The name of the Thread is: "+Thread.currentThread().name)
//        }
//
//    }
    suspend fun task1()
    {
        Log.d("tag","Starting of Task1")
        yield()
        Log.d("tag","Ending of Task1")
    }
    suspend fun task2()
    {
        Log.d("tag","Starting of Task2")
        yield()
        Log.d("tag","Ending of Task2")
    }
//    suspend fun printFollower()
//    {
//        var followers:Int =0
//       var job = CoroutineScope(Dispatchers.Main).launch()
//        {
//           followers=getFollowers()
//        }
//        job.join()
//        Log.d("Followers","You have Followers "+followers)
//    }
     suspend fun printFollower()
    {
        var followers=0
        var job = CoroutineScope(Dispatchers.Main).async()
        {
            getFollowers()
        }
        Log.d("Followers","you have "+job.await().toString()+" Followers")
    }
    suspend fun getFollowers():Int
    {
        delay(1000)
        return 1000
    }
}

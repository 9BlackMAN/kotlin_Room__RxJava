package com.ckc.kotlin_room.view.View.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.ckc.kotlin_room.databinding.ActivityMain2Binding
import com.ckc.kotlin_room.view.View.model.User
import com.ckc.kotlin_room.view.View.roomDb.UserDao
import com.ckc.kotlin_room.view.View.roomDb.UserDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    private val mDisposable = CompositeDisposable()
    private lateinit var placeDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,"User"
        ).allowMainThreadQueries()
            .build()
        placeDao = db.userDao()






        val intent = intent

        val data = intent.getSerializableExtra("key") as User

        binding.textView2.text = data.name


        binding.delete.setOnClickListener {

            mDisposable.add(placeDao.delete(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse))

        }

    }
    fun handleResponse(){

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)


    }
}
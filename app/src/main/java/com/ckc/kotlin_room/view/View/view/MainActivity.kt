package com.ckc.kotlin_room.view.View.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.ckc.kotlin_room.databinding.ActivityMainBinding
import com.ckc.kotlin_room.view.View.adapter.adapter
import com.ckc.kotlin_room.view.View.model.User
import com.ckc.kotlin_room.view.View.roomDb.UserDao
import com.ckc.kotlin_room.view.View.roomDb.UserDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val mDisposable = CompositeDisposable()
    private lateinit var placeDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,"User"
        ).allowMainThreadQueries()
            .build()
        placeDao = db.userDao()


        binding.save.setOnClickListener {
            val userDao = db.userDao()
            val veri = binding.editTextTextPersonName.text.toString()
            val user = User(veri)

            mDisposable.add(userDao.insertAll(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse))

        }

        mDisposable.add(placeDao.getall()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponsee))




    }
    fun handleResponse(){

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)


    }
    private fun handleResponsee(placeList: List<User>) {

        binding.recyler.layoutManager = LinearLayoutManager(this@MainActivity)
        val adapter = adapter(placeList)
        binding.recyler.adapter = adapter

    }
}
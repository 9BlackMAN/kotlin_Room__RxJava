package com.ckc.kotlin_room.view.View.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(@ColumnInfo(name = "name") var name: String ) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id = 0

}
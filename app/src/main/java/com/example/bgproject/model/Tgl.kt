package com.example.bgproject.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("tgl_table")
data class Tgl(

    @PrimaryKey
    var tglId: String,
    var fullName: String,
    var number: String,
    var sex: String,
    var dob: String,
    var bvn: String,
    var nin: String,
    var state: String,
    var lga: String,
    var hub: String,
    var govType: String,
    var govId: String,
    var govImage: String,
    var officerId: String ="",
    var testFlag : Int,

): Parcelable

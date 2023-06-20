package com.example.bgproject.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.bgproject.model.Response
import com.example.bgproject.model.Result
import com.example.bgproject.model.Tgl
import com.example.bgproject.model.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTgl(tgl: Tgl)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult( response: Response,)

    @Update
    suspend fun updateTgl(tgl: Tgl)

    @Query("SELECT * FROM tgl_table")
    fun readAllTglData(): LiveData<List<Tgl>>


    @Query("SELECT * FROM users_table WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM users_table WHERE email = :email AND password = :password")
    suspend fun getUserByEmailAndPassword(email: String, password: String): User?

    @Query("SELECT * FROM tgl_table WHERE officerId = :officerId")
    fun getTglByUser(officerId: String): LiveData<List<Tgl>>

    @Query("SELECT * FROM response_table WHERE tglId = :tglId")
    fun getResultByTgl(tglId: String): LiveData<Response>

    @Query("SELECT * FROM tgl_table WHERE testFlag != 0")
    fun scheduleTgl(): LiveData<List<Tgl>>

}

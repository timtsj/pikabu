package com.tsdreamdeveloper.app.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tsdreamdeveloper.app.data.database.model.PostDbModel

/**
 * @author Timur Seisembayev
 * @since 28.12.2019
 */

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(data: PostDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(data: List<PostDbModel>)

    @Query("SELECT * FROM post_table  ORDER BY id DESC")
    fun getAllPost(): List<PostDbModel>

    @Query("SELECT * FROM post_table  WHERE :idPost = id")
    fun getPost(idPost: Int): PostDbModel

    @Query("DELETE  FROM post_table WHERE :idPost = id")
    fun deletePost(idPost: Int)

    @Query("DELETE FROM post_table")
    fun clear()
}
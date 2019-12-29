package com.tsdreamdeveloper.app.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * @author Timur Seisembayev
 * @since 28.12.2019
 */

@Entity(tableName = "post_table")
data class PostDbModel(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val postId: Int,
        val likesCount: Int,
        val previewText: String,
        val timeshamp: Int,
        val title: String) {

    @Ignore
    constructor() : this(0, 0, "", 0, "")
}

@Entity(tableName = "post_detail_table")
data class PostDetailDbModel(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val postId: Int,
        val images: List<String>,
        val likesCount: Int,
        val text: String,
        val timeshamp: Int,
        val title: String) {

    @Ignore
    constructor() : this(0, emptyList(), 0, "", 0, "")
}
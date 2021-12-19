package com.example.data.data.repository

import com.example.data.db.DbTransaction


class FakedDbTransaction : DbTransaction {
    override suspend fun <R> exec(block: suspend () -> R): R {
        return block()
    }
}

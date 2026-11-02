package com.example.loginappmvvm.data.repository

import com.example.loginappmvvm.data.local.dao.UserDao
import com.example.loginappmvvm.data.local.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {
    suspend fun login(username: String, password: String): User? {
        return withContext(Dispatchers.IO) {
            userDao.login(username, password)
        }
    }
}

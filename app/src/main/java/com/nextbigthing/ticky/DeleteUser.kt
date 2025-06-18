package com.nextbigthing.ticky

import com.nextbigthing.ticky.room.AppModel

interface DeleteUser {
    fun deleteUserFromDb(user: AppModel)
}
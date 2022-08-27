package com.sw.sw_api_kotlin_project.repository

import com.sw.sw_api_kotlin_project.data.dao.PeopleSWAPIDao
import com.sw.sw_api_kotlin_project.data.model.People

class PeopleRepository private constructor(private val peopleDao: PeopleSWAPIDao) {
/*
    companion object {
        @Volatile
        private var _instance: PeopleRepository? = null

        fun getInstance(peopleDao: PeopleSWAPIDao) =
            _instance ?: synchronized(this) {
                _instance ?: PeopleRepository(peopleDao).also { _instance = it }
            }
    }

    suspend fun getPeople(page: Int) = peopleDao.fetchPeople(page)

    suspend fun searchPeople(page: Int, searchString: String) =
        peopleDao.searchPeople(page, searchString)

    private val peopleMap = mutableMapOf<String, People>()

    suspend fun getPeopleByUrl(peopleUrl: String): People? {
        if (peopleMap[peopleUrl] == null) {
            peopleMap[peopleUrl] = peopleDao.fetchPeopleByUrl(peopleUrl)
        }
        return peopleMap[peopleUrl]
    }

*/
}
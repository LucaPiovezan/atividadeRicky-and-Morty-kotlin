package com.luca.myapplication.data.remote

import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): CharacterPageResponse
}

package com.luca.myapplication.data.remote

data class CharacterPageResponse(
    val results: List<CharacterResponse>
)

data class CharacterResponse(
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val image: String
)

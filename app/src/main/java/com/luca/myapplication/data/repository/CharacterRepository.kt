package com.luca.myapplication.data.repository

import com.luca.myapplication.data.local.CharacterDao
import com.luca.myapplication.data.local.CharacterEntity
import com.luca.myapplication.data.remote.CharacterResponse
import com.luca.myapplication.data.remote.RickAndMortyApi
import kotlinx.coroutines.flow.Flow

class CharacterRepository(
    private val api: RickAndMortyApi,
    private val dao: CharacterDao
) {

    val characters: Flow<List<CharacterEntity>> = dao.observeAll()

    suspend fun syncCharacters() {
        val response = api.getCharacters()
        val entities = response.results.map { it.toEntity() }
        dao.upsertAll(entities)
    }

    private fun CharacterResponse.toEntity(): CharacterEntity {
        return CharacterEntity(
            id = id,
            name = name,
            species = species,
            imageUrl = image
        )
    }
}

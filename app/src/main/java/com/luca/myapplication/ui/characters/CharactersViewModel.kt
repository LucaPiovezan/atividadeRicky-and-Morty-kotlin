package com.luca.myapplication.ui.characters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.luca.myapplication.data.local.AppDatabase
import com.luca.myapplication.data.remote.NetworkModule
import com.luca.myapplication.data.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CharacterRepository(
        api = NetworkModule.api,
        dao = AppDatabase.getInstance(application).characterDao()
    )

    val characters = repository.characters

    private val _syncState = MutableStateFlow<SyncState>(SyncState.Idle)
    val syncState: StateFlow<SyncState> = _syncState.asStateFlow()

    fun sync() {
        viewModelScope.launch {
            _syncState.value = SyncState.Loading
            try {
                repository.syncCharacters()
                _syncState.value = SyncState.Success
            } catch (e: Exception) {
                _syncState.value = SyncState.Error(
                    e.message ?: "Falha na conexão. Verifique sua internet."
                )
            }
        }
    }
}

package com.dev.mvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.dev.mvvm.model.DataMahasiswa  // Pastikan ini adalah yang benar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MahasiswaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DataMahasiswa())
    val uiState: StateFlow<DataMahasiswa> = _uiState.asStateFlow()

    fun saveDataMahasiswa(listDM: List<String>) {
        _uiState.update { dataMhs ->
            dataMhs.copy(
                nama = listDM[0],
                gender = listDM[1],
                alamat = listDM[2],
                nim = listDM[3],
                email = listDM[4],
                nohp = listDM[5],
            )
        }
    }
}
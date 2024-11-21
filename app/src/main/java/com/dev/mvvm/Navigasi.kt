package com.dev.mvvm

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.mvvm.model.DataJenisKelamin
import com.dev.mvvm.ui.view.DetailMahasiswaView
import com.dev.mvvm.ui.view.FormMahasiswaView
import com.dev.mvvm.ui.viewmodel.MahasiswaViewModel

enum class Halaman {
    Formulir,
    Detail,
}

@Composable
fun Navigasi(
    modifier: Modifier = Modifier,
    viewModel: MahasiswaViewModel = viewModel(),
    navHost: NavHostController = rememberNavController()
) {
    Scaffold { isipadding ->
        val stateUI by viewModel.uiState.collectAsState()
        NavHost(
            modifier = modifier.padding(isipadding),
            navController = navHost, startDestination = Halaman.Formulir.name
        ) {
            composable(route = Halaman.Formulir.name) {
                val konteks = LocalContext.current
                FormMahasiswaView(
                    listJK = DataJenisKelamin.listJK.map { isi ->
                        konteks.resources.getString(isi)
                    },
                    onSubmitClicked = {
                        viewModel.saveDataMahasiswa(it)
                        navHost.navigate(Halaman.Detail.name)
                    }
                )
            }
            composable(route = Halaman.Detail.name) {
                DetailMahasiswaView(
                    uiStateMahasiswa = stateUI,
                    onClickButton = {
                        navHost.popBackStack()
                    }
                )
            }
        }
    }
}
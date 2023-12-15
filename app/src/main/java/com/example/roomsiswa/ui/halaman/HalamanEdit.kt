package com.example.roomsiswa.ui.halaman

import PenyediaViewModel
import android.text.Spannable.Factory
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomsiswa.R
import com.example.roomsiswa.model.EditViewModel
import com.example.roomsiswa.navigasi.DestinasiNavigasi
import com.example.roomsiswa.navigasi.SiswaTopAppBar
import kotlinx.coroutines.launch

object ItemEditDestination : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_siswa
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    Scaffold (
        topBar = {
            SiswaTopAppBar(
                title = stringResource(ItemEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ){ innerPadding ->
        EntrySiswaBody(
            uiStateSiswa = viewModel.siswaUiState,
            onSiswaValueChange = viewModel::updateUiState,
            onSaveClick = {
                //Note: If the user rotates the screen very fast, the operation may get cancelled
                //and the item may not be update in the ddatabase. This is because when config
                //change occurs, the activity will be recreated and the rememberCoroutineScope will
                //be cancelled - since the scope is bound to composition..
                coroutineScope.launch {
                    viewModel.updateSiswa()
                    navigateBack()
                }
            },
            modifier = Modifier. padding(innerPadding)
        )

    }
}
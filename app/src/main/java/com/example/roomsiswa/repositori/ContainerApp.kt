package com.example.roomsiswa.repositori

import android.content.Context
import com.example.roomsiswa.data.DatabaseSiswa


interface ContainerApp{
    val repositoriSiswa : RepositoriSiswa
}
class ContainerDataApp(private val context: Context): ContainerApp {
    override val repositoriSiswa: RepositoriSiswa by lazy {
        OfflineRepositoriSiswa(DatabaseSiswa.getDatabase(context).siswaDao())
    }

}
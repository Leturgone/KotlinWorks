package com.example.work12

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.work12.ui.theme.Work12Theme
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import java.io.File
import java.net.URL
import java.util.Date

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Work12Theme {
                Menu()
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Menu(){
        var currentScreen by remember { mutableIntStateOf(1) }
        var imageList = remember { mutableStateListOf<String>() }
        var title by remember { mutableStateOf("Главная") }
        val scope = rememberCoroutineScope()

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                // Содержимое бокового меню
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                ) {
                    IconButton(onClick = {currentScreen = 1
                        title = "Главная"}){
                        Icon(Icons.Filled.Home, contentDescription =null, tint = Color.White)
                    }
                    Spacer(Modifier.padding(60.dp))
                    IconButton(onClick = {currentScreen = 2
                        title = "Список"}){
                        Icon(Icons.Filled.List, contentDescription =null, tint = Color.White)
                    }
                }
            }
        ){
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                        title = { Text(title) },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }) {
                                Icon(Icons.Filled.Menu, contentDescription = "Menu")
                            }
                        }
                    )
                }, bottomBar = {
//                NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
//                    NavigationBarItem(colors = NavigationBarItemDefaults.colors(
//                        unselectedIconColor = Color.White),
//                        selected = false, onClick = {currentScreen = 1
//                                                                  title = "Главная"}, icon = {Icon(Icons.Filled.Home, contentDescription = null)})
//                    NavigationBarItem(colors = NavigationBarItemDefaults.colors( unselectedIconColor = Color.White),
//                        selected = false, onClick = {currentScreen = 2
//                                                                  title = "Список"}, icon = { Icon(Icons.Filled.List,contentDescription = null)})
//                }
                    BottomAppBar(containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        IconButton(onClick = {currentScreen = 1
                            title = "Главная"}){
                            Icon(Icons.Filled.Home, contentDescription =null, tint = Color.White)
                        }
                        Spacer(Modifier.weight(1f, true))
                        IconButton(onClick = {currentScreen = 2
                            title = "Список"}){
                            Icon(Icons.Filled.List, contentDescription =null, tint = Color.White)
                        }

                    }
                }
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary)
                    ) {
                        Row {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                when(currentScreen){
                                    1 -> Greeting(imageList = imageList)
                                    2 -> ListScreen(imageList = imageList)
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    @SuppressLint("MutableCollectionMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun Greeting(imageList:MutableList<String>) {
        var text by remember { mutableStateOf("") }
        var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }


        Column(
            Modifier.padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (imageBitmap != null) {
                Image(
                    bitmap = imageBitmap!!.asImageBitmap(),
                    contentDescription = "Загруженное изображение",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Ведите ссылку") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                val image_url = text

                if (image_url.isNotEmpty()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val downloadResult = setDownloadImage(image_url)
                        imageBitmap = downloadResult?.image
                        downloadResult?.path?.let { imageList.add(it) }

                    }
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        val image_url_df =
                            "https://kinopoisk-ru.clstorage.net/1u5Ui2190/3f3e39suom/72APuHhsN-Rw-LMDW37vxxFf28ZjF3On64PVw8j6xyCI4NEsRFvzoXxL4fyzlAqE7beb10_iRHy9wPSc4_z2aq0wTb0nfi_sdaavT1kh6pNeE4KbJlk79K-expOWpegQoNL4XmJk24FQKqC59ItV3bL3jogfoEpk-Y2GofvIrDLtWB6Pcl138Xzn8hhzVMDPPyXy2l1AcqftxNP-0vfNBrbB8kN8d_qpSBWgmOLOct-b4oaCOrRuPsW9kLGK1qoU83UPkV5eSO5nzM8-eVbg0AUc-8xcfGKS8-vT8OzH8QKz6fFgS3jhr0EHjbjG81nOvv6zsBSgQjjylbyKje-cBPFwH5tzdn3YYcraO11J58MgF8DofHt3ut7s9Mng3_EBlsfeV0NM_q9fC5269cdQ_qXMho4aoWwpxq-Nto_ahyH0UD2_Rk5o-UX81DxfQv7JJhTjwWFyY6T6983p1fjyIqb_w0lkUvqTTxeWqNbvUO2o2YmOFIV4EOOWnbKy8I006201iERcdPBl0u4_T3fY2BMF58tScFiWx_jz4M_g-Rqu2fxFWX3hjnQJv6fBynH7uO-xoxi9TC3jqIGnns-sKc1XN6xjU2DzZvzkGUBf5_gnNNvLVlJCjvbJ1fD01MUkhtfUYFx94aRwAoGe1vFW0pP5pKsDmHE8zpW0mKnIlyD3UjWdUnNrz07d5w5zRc3zMTPg0mJJe6zQ5dT6yfL_GaXQy216c8Kffgy9iNbxWtWf8by1AJRGAMyavpOc6aID8kkOqnxFW9hb_ckNVUP74QQC5cxtfVOJ3unc6-nT8AKo_OJCT2_wkV8LkJ7U7G3nseiVgA-gcDLKr4u3vcepHf9NEalmUV7rUs7PE1hk6u8RJfrCc29Rr-rf2-jT_OU7sfHyTkxD4bRRN4K79uJK1qTduogMhHMvwK6Wmoj6iALgcDG2S29a7VvM-y53ZN_mLSbXwXpubor19Pj5wf_4PaXW8mtGc8azYR60hu7iTduq2r-NHbxzPMe6s5id5bcT9m4CnW1ob-Bi6dYVT0Tz2gMY0tpRfnut_OD55Orb9TyG4MlUa2fchWEvvLrH5nTRivCKtzGabRvRrZSPt8mSJNVlCZNDXHDHev7LF3d_-_AvPtLkb0p5ocjZ9dn0_sMAsOnvUUtv3INxCoGh1sxU-rnUi4E5nGk93rWavJT7hgLbaRG6Q1ZX_WXG-wNKd9_uAh3FwHt6Z5To5szfyOH-GKz59U55XvaPaQCHn9LwddCs6quOI51GD_G9r5K-2Zct3nEjqGV8QfNjxccDdErdzggk38xVWVez98Dw7Nzg5TuR9fdUSkf2jGkjlrvg0HrNquyEkhmaSSHhuY-5kOSRHchIHbRBWWnuQNLFDHxK8dcINPnHWG93sPPE_fDT5f45pt7Ve2tOxZB6IJ6q68N_8obrq6wUn0wC3JOziKPJtTXgTCKSbGlI-WbB8Bh8V8bSKD_G9nNZVY3J3vbt48TCPInc_EBufuSCVA2HmvPiTtedy5OdFYlDPN2Uq4SI-aIR6VUsrWZ1UttGyf8TcXTp9x4bz-RGSXuz997J6PbR9Tab4PJVRm7_vnwgupnM8FbumfKogBiaeTz4io-4lfu8AMxyPrNBe3flcMDVL1F88tIRJ8HnZHx3hdnH9ejx2OYIhNbxVGde6Jd4Hbmd88d14r37rJ47oVky0rCprbLQrBbNcTysYmZ63lvDzRdeffvGDDnn9U15UJvz5Ozux-DCNaLS70xmZca3VCiGoNLiW_eo9bmNLoNkPPiTmJ-K6IECy28Qjk9LVN1Q5PofW1LJzAwl8ftze2aD2MnHyd73xwe25sxxS13al2ErnKDMykj_suytpRWgYzPYsKu0s8iGEtJPDZRnfmPTZ-H3G3ZcyOscEtbGeXFDm-zC9fj39MMMsOP_cWpS6IxrF6WL7PF284vKs6EAplQN85K9m4rxijLsZwyLUmZ-2V3_zBVSXuHDAij66XlUV7PdytzF7MH7JIrgw3tGc9eBcQKDm9DMd_Ob4bOePKRDAd-Bu4ix75AU4GQdi25Rat560tQ7WUPE7TEZ3tNDfl-Rx_vWzfbB0hap-PB1RH3ZsHoxupfux3DlqfKEvjS2ez_xrpWuuNCQH9pmI41IVlD9Y-7OEGl07dcoH-XPVGxCtPHh9OzD_NAloMPxb1t_xKFXHJCAzM5K0K3rupIOv3coz6GYl5fMkgP_biuwX0t9znrJ7jtSVuf6DynZ53xtRY37zND58e3HOaPAwmBATuWiSDySuMvEXc2u8I-pEJxJGPuorZaN6JQR7XQ9oUNqYOBO_PwyQlj95SQW4PlyQE6h_MPH6Ofl9ju52eJOYUP1kHosvavz9lPXivycjDOyTwPlkZOpsNmHINVULJRbcnrFce3xO3RDyPU6MOPiZnthsM3W39vs0sIchfbzdEFc1ZVWIpGYxOR10on5sa4UnFII57SqmLblkxTPaSm4XXNc8FfO5CN8YOXkGRv350F-epj3_PPd1t3hJqjw1HhifMWMUiiSiuvAUdKv7I2EFaVyJvy6kIy8yJUg-GQji31OSsZYwt4bYEPf6gQdxe9edF2DztzU2cfYwC2Q9Ot0YHj_rVUHgKLK5k7_quKrjiyWYyPYmbONmfuiNvhUMoxbZWrvU_PvO2lBxO4nNsHLWUJjmObs0cPj4_YNoMDbbX5E86NYKZSt1OJx16rekI8SgHA4xqCKlZT8tyTfYAKXZkhrwlbF1T9pRM_pGDf3xVxdUKHsz8TS_tzPLIX832VLdeSuURKPoOrycdO10JSuFZtIJc6Xvpqz-o4T_lAUqVB0cOVB4c40aFPn2gsY3sV9Xmeh3tfN3-3ExwyQ0e9WR1r9rG8-paHl9VnShumJqDGdVzreorG2ttijCuhlMqpOT2jZW9nlNHxI-vYvPu3Tc0JFqMbH2uP0wc4AoN3Ue0B5xY1oLoSE6M9f3ZDfsqglmVUzzYk"
                        val downloadResult = setDownloadImage(image_url_df)
                        imageBitmap = downloadResult?.image
                        downloadResult?.path?.let { imageList.add(it) }
                    }
                }
            }) {
                Text("Загрузть изображение")
            }
        }
    }
    @Composable
    fun ListScreen(imageList:MutableList<String>){
        Column(Modifier.padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            LazyColumn {
                items(imageList) { item ->
                    Row {
                        Text(
                            text = item, fontSize = 20.sp, modifier = Modifier
                                .weight(1f)
                                .wrapContentWidth(Alignment.Start)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            bitmap = BitmapFactory.decodeFile(item).asImageBitmap(),
                            contentDescription = "часть списка",
                            modifier = Modifier.height(120.dp)
                        )
                    }
                }
            }

        }
    }
    fun downloadImageByURl(imageUrl: String): Bitmap? {
        return try {
            val inputStream = URL(imageUrl).openStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            Log.e("Network", "Ошибка при скачивании")
            return null
        }
    }

    fun saveImageToDisk(bitmap: Bitmap): String {
        val file = File(getOutputDirectory(), "${Date()}.jpg")
        file.outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
        Log.i("Disk", "Фото сохранено: ${file.absolutePath}")
        return file.absolutePath
    }

    fun getOutputDirectory(): File {
        val mediaDir = this.externalMediaDirs.firstOrNull()?.let {
            File(this.filesDir, "photos").apply { mkdirs() }
        }
        return mediaDir ?: this.filesDir
    }

    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    suspend fun setDownloadImage(image_url: String):DoubleReturn? {
        var bitmap: Bitmap? = null
        val deferred = CompletableDeferred<DoubleReturn?>()
        val NetworkTread = newSingleThreadContext("Network")
        val DiskTead = newSingleThreadContext("Disk")
        GlobalScope.launch(NetworkTread) {
            bitmap = downloadImageByURl(image_url)
            if (bitmap == null) {
                Log.i("Network", "Ошибка при скачивании")
                launch(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Ошибка при скачивании", Toast.LENGTH_SHORT).show()
                }.join()
                deferred.complete(null)
            }
            else {
                Log.i("Network", "Изображение скачано")

                launch(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Изображение скачано", Toast.LENGTH_SHORT).show()
                }.join()
                launch(DiskTead) {
                    val path = saveImageToDisk(bitmap!!)
                    val doublereturn = DoubleReturn(bitmap,path)
                    deferred.complete(doublereturn)
                }
            }
        }
        return deferred.await()
    }

}
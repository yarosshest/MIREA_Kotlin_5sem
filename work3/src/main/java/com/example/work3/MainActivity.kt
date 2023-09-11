package com.example.work3

import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Build
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.work3.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date

class CameraRepository{
    suspend fun savePhoto(data:  ByteArray, filesDir :File) {
        coroutineScope {
            try {
                val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                val date = sdf.format(Date())

                val path = filesDir
                val letDirectory = File(path, "Photos")
                letDirectory.mkdirs()
                val photoFile = File(letDirectory, "$date.txt")
                FileOutputStream(photoFile).use {
                    it.write(data)
                }
                val file = File(path, "date.txt")
                var text = "$date;"
                file.appendText(text)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }
}


class CameraViewModel: ViewModel(){
    private val cameraRepository = CameraRepository()
    fun savePhoto(data:  ByteArray, filesDir :File){
        viewModelScope.launch(Dispatchers.IO) {
            cameraRepository.savePhoto(data, filesDir)
        }
    }
}

class MainActivity : AppCompatActivity(), SurfaceHolder.Callback {
    private lateinit var viewModel: CameraViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var camera: Camera
    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var surfaceView: SurfaceView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CameraViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Проверка на наличие разрешения на использование камеры
        if (checkCameraPermission()) {
            // Открываем камеру
            camera = Camera.open()
            surfaceView = findViewById(R.id.surfaceView)
            surfaceHolder = surfaceView.holder
            surfaceHolder.addCallback(this)
        }

        binding.capture.setOnClickListener {
            camera.takePicture(null, null) { data, camera ->
                this.getExternalFilesDir(null)?.let { it1 -> viewModel.savePhoto(data, it1) }
                camera.startPreview()
            }
        }

        binding.next.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        try {
            // Подключаем предпросмотр к SurfaceView
            camera.setPreviewDisplay(holder)
            camera.startPreview()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        try {
            // Подключаем предпросмотр к SurfaceView
            camera.setPreviewDisplay(holder)
            camera.startPreview()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            // Подключаем предпросмотр к SurfaceView
            camera.setPreviewDisplay(surfaceHolder)
            camera.startPreview()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        camera.release()
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        // Освобождаем ресурсы камеры
        camera.stopPreview()
    }

    private fun checkCameraPermission(): Boolean {
        return if (checkSelfPermission(android.Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED) {
            true
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 1)
            false
        }
    }
}
package com.example.work4

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.Manifest
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.work4.databinding.FragmentCameraBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraFragment : Fragment() {
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var viewFinder: PreviewView
    private lateinit var binding: FragmentCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCameraBinding.inflate(inflater, container,false)

        cameraExecutor = Executors.newSingleThreadExecutor()
        viewFinder = binding.previewView

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(requireActivity(),
                REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        binding.imageView.setOnClickListener {
            val imageCapture = ImageCapture.Builder().build()


            val photoFile = File(
                requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "photos/${SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())}.jpg"
            )
            val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

            imageCapture.takePicture(
                outputOptions, cameraExecutor, object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        Toast.makeText(requireActivity(),"Фото сохранено: ${photoFile.absolutePath}", Toast.LENGTH_LONG).show()
                    }

                    override fun onError(exception: ImageCaptureException) {
                        Toast.makeText(requireActivity(),"Ошибка сохранения", Toast.LENGTH_LONG).show()
                        Log.e("CameraFragment", "Photo capture failed: ${exception.message}", exception)
                    }
                }
            )
//            val currentDateTime = LocalDateTime.now()
//            val formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//            // Затем создадим строку с данными для сохранения
//            val dataToSave = "Date: $formattedDateTime, Text: $date"
//            // Путь к файлу для сохранения данных
//            val filePath = File(context.getExternalFilesDir("photos"), "data.txt")
//
//            try {
//                // Создаем новый файл или перезаписываем существующий
//                filePath.writeText(dataToSave)
//            } catch (e: Exception) {
//                Log.e("Error", "Failed to save data to file: ${e.message}")
//            }
        }


        return binding.root
    }

    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(viewFinder.surfaceProvider)
            }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this,
                    cameraSelector, preview)
                Log.e("CAMERA", "Starting camera")
            } catch (exc: Exception) {
                Log.e("CAMERA", "Error starting camera", exc)
                exc.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(requireContext()))



    }
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {it:String ->
        ContextCompat.checkSelfPermission(requireContext(),it) == PackageManager.PERMISSION_GRANTED
    }
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions,
            grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                parentFragmentManager.beginTransaction().remove(this).commit()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        @JvmStatic
        fun newInstance() =
            CameraFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
package com.example.tipping.view

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.presentation.event.observeEvent
import com.example.tipping.databinding.FragmentTipjarBinding
import com.example.tipping.viewmodel.TipJarViewModel
import com.example.tipping.viewmodel.TipJarViewModelImpl
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class TipJarFragment : Fragment() {

    private lateinit var binding: FragmentTipjarBinding
    private val viewModel: TipJarViewModel by viewModel<TipJarViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentTipjarBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        // findNavController().navigate(TipJarFragmentDirections.actionTipjarToReceiptList())

        viewModel.navigateToReceiptList.observeEvent(viewLifecycleOwner) {
            findNavController().navigate(TipJarFragmentDirections.actionTipjarToReceiptList())
        }
        viewModel.navigateToCamera.observeEvent(viewLifecycleOwner){
            dispatchTakePictureIntent()
        }
        return binding.root
    }

    lateinit var currentPhotoPath: String

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private val launchCamera =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            viewModel.onReturnFromCamera(it.resultCode == RESULT_OK)
        }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            activity?.packageManager?.let {
                takePictureIntent.resolveActivity(it)?.also {
                    // Create the File where the photo should go
                    val photoFile: File? = try {
                        createImageFile()
                    } catch (ex: IOException) {
                        // Error occurred while creating the File
                        Timber.e(ex)
                        null
                    }
                    // Continue only if the File was successfully created
                    photoFile?.also {
                        val photoURI: Uri = FileProvider.getUriForFile(
                            requireContext(),
                            "com.example.tipjar",
                            it
                        )
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        viewModel.imagePath.postValue(currentPhotoPath)
                        launchCamera.launch(takePictureIntent)
                    }
                }
            }
        }
    }

}

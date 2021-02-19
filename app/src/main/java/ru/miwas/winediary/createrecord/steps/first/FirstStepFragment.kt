package ru.miwas.winediary.createrecord.steps.first

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import ru.miwas.winediary.R
import ru.miwas.winediary.core.viewbinding.viewBinding
import ru.miwas.winediary.createrecord.CreateRecordViewModel
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.AlcoholPercentage
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Color
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Country
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Name
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Price
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Year
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.NextStepClicked
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.OnEditTextChanged
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.OnImageClicked
import ru.miwas.winediary.databinding.CreateRecordStepFirstFragmentBinding
import ru.miwas.winediary.utils.file.getFilePath

class FirstStepFragment(
    private val viewModel: CreateRecordViewModel
) : Fragment(R.layout.create_record_step_first_fragment) {

    private val binding by viewBinding(CreateRecordStepFirstFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView()
        observeViewModel()
    }

    private fun prepareView() {
        binding.nextButton.setOnClickListener {
            viewModel.dispatchEvent(NextStepClicked)
        }

        binding.imagePlaceholder.setOnClickListener {
            viewModel.dispatchEvent(OnImageClicked)
        }

        binding.nameInputEditText.doAfterTextChanged {
            sendEditTextEvent(Name(it?.toString()))
        }

        binding.countryInputEditText.doAfterTextChanged {
            sendEditTextEvent(Country(it?.toString()))
        }

        binding.yearInputEditText.doAfterTextChanged {
            sendEditTextEvent(Year(it?.toString()))
        }

        binding.alcoholPercentageInputEditText.doAfterTextChanged {
            sendEditTextEvent(AlcoholPercentage(it?.toString()))
        }

        binding.colorInputEditText.doAfterTextChanged {
            sendEditTextEvent(Color(it?.toString()))
        }

        binding.priceInputEditText.doAfterTextChanged {
            sendEditTextEvent(Price(it?.toString()))
        }
    }

    private fun observeViewModel() {
        viewModel.choosePhoto.observe(viewLifecycleOwner, Observer {
            choosePhoto()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            context?.let {
                viewModel.dispatchEvent(
                    CreateRecordViewModel.Event.OnImageSelected(data?.data.getFilePath(it))
                )
            }

            binding.imagePlaceholder.setImageURI(data?.data)
            binding.imagePlaceholder.scaleType = ImageView.ScaleType.FIT_CENTER
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun sendEditTextEvent(type: EditTextType) {
        viewModel.dispatchEvent(OnEditTextChanged(type))
    }

    private fun choosePhoto() {
        context?.let { nonNullContext ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkStoragePermission(nonNullContext) == PackageManager.PERMISSION_DENIED) {
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    pickImageFromGallery()
                }
            } else {
                pickImageFromGallery()
            }
        }

    }

    private fun pickImageFromGallery() {
        val pickPhotoIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(pickPhotoIntent, IMAGE_PICK_CODE)
    }

    private fun checkStoragePermission(nonNullContext: Context) =
        checkSelfPermission(nonNullContext, Manifest.permission.READ_EXTERNAL_STORAGE)

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001

        fun newInstance(viewModel: CreateRecordViewModel): FirstStepFragment {
            return FirstStepFragment(viewModel)
        }
    }
}
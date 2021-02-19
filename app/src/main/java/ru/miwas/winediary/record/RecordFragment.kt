package ru.miwas.winediary.record

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import ru.miwas.winediary.R
import ru.miwas.winediary.core.base.BaseFragment
import ru.miwas.winediary.core.viewbinding.viewBinding
import ru.miwas.winediary.databinding.RecordFragmentBinding
import ru.miwas.winediary.di.DaggerDI
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import ru.miwas.winediary.record.di.component.DaggerRecordComponent
import ru.miwas.winediary.record.di.module.RecordFragmentModule
import ru.miwas.winediary.record.model.Wine
import ru.miwas.winediary.record.RecordViewModel.Event.BackButtonClicked
import ru.miwas.winediary.record.RecordViewModel.Event.DeleteButtonClicked
import ru.miwas.winediary.record.RecordViewModel.Event.DeleteConfirmationButtonClicked
import java.io.File
import javax.inject.Inject

class RecordFragment(
    private val id: Long
) : BaseFragment(R.layout.record_fragment) {

    @Inject
    lateinit var viewModel: RecordViewModel

    @Inject
    lateinit var fragmentNavigationHelper: FragmentNavigationHelper

    private val binding by viewBinding(RecordFragmentBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerRecordComponent
            .builder()
            .recordFragmentModule(RecordFragmentModule(this))
            .appComponent(DaggerDI.appComponent)
            .build()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = resources.getColor(R.color.orangeVeryLight, null)
        prepareView()
        observeViewModel()
        viewModel.setWineId(id)
        viewModel.startProcesses()
    }

    override fun prepareViewModel() {
        fragmentManager?.let {
            fragmentNavigationHelper.configHelper(it, R.id.mainContainer)
        }
    }

    override fun observeViewModel() {
        viewModel.wine.observe(
            viewLifecycleOwner,
            Observer {
                configureView(it)
            }
        )

        viewModel.deleteConfirmationDialogState.observe(
            viewLifecycleOwner,
            Observer {
                showDeleteConfirmationDialog()
            }
        )
    }

    override fun prepareView() {

        binding.backButton.setOnClickListener {
            viewModel.dispatchEvent(BackButtonClicked)
        }

        binding.deleteButton.setOnClickListener {
            viewModel.dispatchEvent(DeleteButtonClicked)
        }
    }

    private fun configureView(wine: Wine) {
        with(wine) {
            if (imagePath.isNotEmpty()) {
                configureImage(imagePath)
            }
            binding.wineNameValue.text = name
            binding.wineNameToolbar.text = name
            binding.countryValue.text = country
            binding.yearValue.text = year.toString()
            binding.alcoholPercentageValue.text = alcoholPercentage.toString()
            binding.colorValue.text = color
            binding.priceValue.text = price.toString()
            binding.grapeVarietiesValue.text = grapeVariety
            binding.smellValue.text = smell
            binding.tasteValue.text = taste
            binding.combinationValue.text = combination
            binding.notesValue.text = notes
            binding.smellRatingBar.rating = rateSmell.toFloat()
            binding.tasteRatingBar.rating = rateTaste.toFloat()
            binding.totalRatingBar.rating = rateTotal.toFloat()
        }

    }

    private fun configureImage(imagePath: String) {
        Glide
            .with(this@RecordFragment)
            .load(File(imagePath))
            .fitCenter()
            .placeholder(R.drawable.image_placeholder)
            .into(binding.winePhoto)
    }

    private fun showDeleteConfirmationDialog() {

        val deleteDialog = AlertDialog.Builder(context)
            .setMessage(resources.getString(R.string.delete_record_confirmation_dialog))
            .setPositiveButton(resources.getString(R.string.delete_record_confirmation_positive)) { _, _ ->
                viewModel.dispatchEvent(DeleteConfirmationButtonClicked)
            }
            .setNegativeButton(R.string.delete_record_confirmation_negative) { dialog, _ ->
                dialog.dismiss()
            }.create()

        deleteDialog.show()

        deleteDialog.getButton(BUTTON_NEGATIVE)
            .setTextColor(resources.getColor(R.color.darkRed, null))

        deleteDialog.getButton(BUTTON_POSITIVE)
            .setTextColor(resources.getColor(R.color.darkGrey, null))
    }
}
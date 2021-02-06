package ru.miwas.winediary.record

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.record_fragment.*
import ru.miwas.winediary.R
import ru.miwas.winediary.base.BaseFragment
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
) : BaseFragment() {

    @Inject
    lateinit var viewModel: RecordViewModel

    @Inject
    lateinit var fragmentNavigationHelper: FragmentNavigationHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerRecordComponent
            .builder()
            .recordFragmentModule(RecordFragmentModule(this))
            .appComponent(DaggerDI.appComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prepareViewModel()
        activity?.window?.statusBarColor = resources.getColor(R.color.orangeVeryLight, null)
        return inflater.inflate(R.layout.record_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        backButton.setOnClickListener {
            viewModel.dispatchEvent(BackButtonClicked)
        }

        deleteButton.setOnClickListener {
            viewModel.dispatchEvent(DeleteButtonClicked)
        }
    }

    private fun configureView(wine: Wine) {
        with(wine) {
            if (imagePath.isNotEmpty()) {
                configureImage(imagePath)
            }
            wineNameValue.text = name
            wineNameToolbar.text = name
            countryValue.text = country
            yearValue.text = year.toString()
            alcoholPercentageValue.text = alcoholPercentage.toString()
            colorValue.text = color
            priceValue.text = price.toString()
            grapeVarietiesValue.text = grapeVariety
            smellValue.text = smell
            tasteValue.text = taste
            combinationValue.text = combination
            notesValue.text = notes
            smellRatingBar.rating = rateSmell.toFloat()
            tasteRatingBar.rating = rateTaste.toFloat()
            totalRatingBar.rating = rateTotal.toFloat()
        }

    }

    private fun configureImage(imagePath: String) {
        Glide
            .with(this@RecordFragment)
            .load(File(imagePath))
            .placeholder(R.drawable.image_placeholder)
            .into(winePhoto)
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
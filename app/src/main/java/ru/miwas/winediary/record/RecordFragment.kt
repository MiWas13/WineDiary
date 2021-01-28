package ru.miwas.winediary.record

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.record_fragment.*
import ru.miwas.winediary.R
import ru.miwas.winediary.base.BaseFragment
import ru.miwas.winediary.di.DaggerDI
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import ru.miwas.winediary.record.di.component.DaggerRecordComponent
import ru.miwas.winediary.record.di.module.RecordFragmentModule
import ru.miwas.winediary.record.model.Wine
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
    }

    override fun prepareView() {

    }

    private fun configureView(wine: Wine) {
        with(wine) {
            winePhoto.setImageBitmap(BitmapFactory.decodeFile(File(imagePath).absolutePath))
            wineNameValue.text = name
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
}
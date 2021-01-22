package ru.miwas.winediary.record

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.record_fragment.*
import ru.miwas.winediary.R
import ru.miwas.winediary.base.BaseFragment
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import ru.miwas.winediary.navigationcore.FragmentNavigationHelperImpl
import ru.miwas.winediary.record.model.Wine
import ru.miwas.winediary.record.navigation.RecordNavigatorImpl
import java.io.File

class RecordFragment(
    private val id: Long
) : BaseFragment() {
    private lateinit var viewModel: RecordViewModel
    private val fragmentNavigationHelper: FragmentNavigationHelper = FragmentNavigationHelperImpl()

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
        viewModel = RecordViewModelImpl(RecordNavigatorImpl(fragmentNavigationHelper))
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
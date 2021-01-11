package ru.miwas.winediary.navigationcore

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface FragmentNavigationHelper {

    fun configHelper(fragmentManager: FragmentManager, @IdRes containerId: Int)

    fun replaceFragmentWithBackStack(fragment: Fragment, stackName: String? = null)

    fun goToMain()

    fun removeLastFragment()

    fun removeAllFragments()

    fun clearHelper()

}
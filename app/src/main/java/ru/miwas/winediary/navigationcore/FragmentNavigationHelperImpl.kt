package ru.miwas.winediary.navigationcore

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentNavigationHelperImpl : FragmentNavigationHelper {

    private var fragmentManager: FragmentManager? = null

    private var containerId: Int? = null

    override fun configHelper(fragmentManager: FragmentManager, containerId: Int) {
        this.fragmentManager = fragmentManager
        this.containerId = containerId
    }

    override fun addFragment(fragment: Fragment, stackName: String?) {
        fragmentManager?.apply {
            containerId?.let {
                beginTransaction()
                    .add(it, fragment, stackName)
                    .addToBackStack(stackName)
                    .commit()
            }
        }
    }

    override fun removeLastFragment() {

    }

    override fun clearHelper() {
        fragmentManager = null
        containerId = null
    }

}
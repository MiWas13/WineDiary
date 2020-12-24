package ru.miwas.winediary.createrecord

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

class CreateRecordAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private var stepsList = arrayListOf<Fragment>()


    override fun getItemCount(): Int {
        return stepsList.count()
    }

    override fun createFragment(position: Int): Fragment {
        return stepsList[position]
    }

    fun setSteps(arrayList: ArrayList<Fragment>) {
        stepsList = arrayList
    }
}
package com.invages.orchidrus.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class EventFragmentsAdapter(fm: FragmentManager) :  FragmentPagerAdapter(fm) {

    var fragList = arrayListOf<Fragment>()
    var titleList = arrayListOf<String>()

    override fun getItem(position: Int): Fragment {
        return fragList[position]
    }

    override fun getCount(): Int {
        return fragList.size
    }


    fun addFragment(fragment: Fragment, title: String) {
        fragList.add(fragment)
        titleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

}
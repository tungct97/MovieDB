package com.example.moviedb.ui.screen

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.moviedb.R
import com.example.moviedb.databinding.ActivityMainBinding
import com.example.moviedb.ui.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {

    override val layoutId: Int = R.layout.activity_main

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var selectedTag: String? = null
        when (item.getItemId()) {
            R.id.nav_home -> selectedTag = HomeFragment.TAG
            R.id.nav_favourite -> selectedTag = FavouriteFragment.TAG
        }
        addFragment(selectedTag)
        return true
    }

    override fun iniView(view: ActivityMainBinding) {
        view.bottomNavigationView.setOnNavigationItemSelectedListener(this)
        addFragment(HomeFragment.TAG)
    }

    fun getFragment(tag: String?): Fragment? {
        var fragment: Fragment? = null
        when (tag) {
            HomeFragment.TAG -> fragment = HomeFragment.newInstance()
            FavouriteFragment.TAG -> fragment = FavouriteFragment.newInstance()
        }
        return fragment
    }

    fun addFragment(tag: String?, addToBackStack: Boolean = false) {
        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .apply {
                    if (addToBackStack) {
                        commitTransaction(this, addToBackStack)
                    }
                }
                .show(fragment)
                .commit()
            return
        }
        getFragment(tag)?.let {
            if (tag != null) {
                addFragment(it, tag)
            }
        }
    }

    private fun commitTransaction(fragmentTransaction: FragmentTransaction, addToBackStack: Boolean) {
        if (addToBackStack) fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment, tag).commit()
    }
}

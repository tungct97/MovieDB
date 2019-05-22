package com.example.moviedb.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.moviedb.R
import com.example.moviedb.databinding.ActivityMainBinding
import com.example.moviedb.ui.base.BaseActivity
import com.example.moviedb.ui.screen.favourite.FavouriteFragment
import com.example.moviedb.ui.screen.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {

    override val layoutId: Int = R.layout.activity_main

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        supportFragmentManager.popBackStack()
        var selectedTag: String? = null
        when (item.getItemId()) {
            R.id.nav_home -> selectedTag = HomeFragment.TAG
            R.id.nav_favourite -> selectedTag = FavouriteFragment.TAG
        }
        addFragment(selectedTag)
        return true
    }

    fun addFragment(tag: String?, addToBackStack: Boolean = false) {
        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .apply {
                    if (addToBackStack) {
                        commitTransaction(tag, this, addToBackStack)
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


    override fun iniView(view: ActivityMainBinding, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragment(HomeFragment.TAG)
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    fun getFragment(tag: String?): Fragment? {
        var fragment: Fragment? = null
        when (tag) {
            HomeFragment.TAG -> fragment = HomeFragment.newInstance()
            FavouriteFragment.TAG -> fragment = FavouriteFragment.newInstance()
        }
        return fragment
    }


    private fun commitTransaction(tag: String?, fragmentTransaction: FragmentTransaction, addToBackStack: Boolean) {
        if (addToBackStack) fragmentTransaction.addToBackStack(tag)
        fragmentTransaction.commit()
    }

    fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment, tag).commit()
    }

    fun addChildFragment(
        fragment: Fragment, tag: String?, addToBackStack: Boolean = false
    ) {
        supportFragmentManager.beginTransaction().replace(
            R.id.frameLayout, fragment, tag
        ).apply {
            if (addToBackStack) {
                commitTransaction(tag, this, addToBackStack)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val count = supportFragmentManager.backStackEntryCount
        if (count > 0) {
            supportFragmentManager.popBackStack()
        }
    }
}

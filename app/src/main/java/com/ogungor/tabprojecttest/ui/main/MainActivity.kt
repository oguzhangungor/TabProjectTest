package com.ogungor.tabprojecttest.ui.main


import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import com.google.firebase.auth.FirebaseAuth
import com.ogungor.tabprojecttest.R
import com.ogungor.tabprojecttest.activity.BaseActivity
import com.ogungor.tabprojecttest.util.extentions.launchFeedsActivity
import com.ogungor.tabprojecttest.util.extentions.showLongToast

class MainActivity : BaseActivity(), MainActivityContract.View {

    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    private lateinit var mainActivityPresenter: MainActivityContract.Presenter
    private lateinit var auth:FirebaseAuth
    private lateinit var currentUser:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityPresenter = MainActivityPresenter()
            .apply {
                setView(this@MainActivity)
                create()
                loginUserControl(currentUser)


            }
    }

    override fun getLayout(): Int = R.layout.activity_main

    override fun initUi() {
        auth= FirebaseAuth.getInstance()
        this.currentUser=auth
        sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        tabs = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

    }

    override fun intentToFeedsActivity() {
        launchFeedsActivity()
        finish()
    }

    override fun showLoginMessage() {
        showLongToast("Hoşgeldiniz  ${auth.currentUser?.email.toString()}")

    }
}
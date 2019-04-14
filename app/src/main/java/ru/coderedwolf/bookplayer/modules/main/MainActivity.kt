package ru.coderedwolf.bookplayer.modules.main

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.coderedwolf.bookplayer.R
import ru.coderedwolf.bookplayer.modules.base.RoutingMvpActivity
import ru.coderedwolf.bookplayer.modules.common.BackButtonListener
import ru.terrakok.cicerone.android.support.SupportAppNavigator


class MainActivity : RoutingMvpActivity<MainPresenter>(), MainView {

    private val mNavigator = SupportAppNavigator(this, supportFragmentManager, R.id.fragmentContainer)

    override fun getNavigator() = mNavigator

    @InjectPresenter lateinit var mMainPresenter: MainPresenter
    @ProvidePresenter fun presenter() = providePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val fragment = findNowFragment()
        if (fragment is BackButtonListener) {
            if (fragment.onBackPressed()) {
                mMainPresenter.onBackPressed()
            }
        } else {
            mMainPresenter.onBackPressed()
        }
    }

    private fun findNowFragment() = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
}
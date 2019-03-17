package pham.honestbee.usertracking.ui

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import pham.honestbee.usertracking.R
import pham.honestbee.usertracking.databinding.ActivityMainBinding
import javax.inject.Inject

/**
 * Created by Pham on 18/3/2019
 */

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    var binding: ActivityMainBinding? = null
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.rvVideos?.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
        initViewModelAndBind()
    }

    private fun initViewModelAndBind() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        binding?.viewModel = viewModel
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
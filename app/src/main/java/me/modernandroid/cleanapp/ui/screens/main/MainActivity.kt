package me.modernandroid.cleanapp.ui.screens.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import me.modernandroid.cleanapp.R
import me.modernandroid.cleanapp.databinding.ActivityMainBinding
import me.modernandroid.cleanapp.models.Repository
import me.modernandroid.cleanapp.ui.adapters.RepositoryRecyclerViewAdapter

class MainActivity : AppCompatActivity(), RepositoryRecyclerViewAdapter.OnItemClickListener {

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var binding: ActivityMainBinding
    private val repositoryRecyclerViewAdapter = RepositoryRecyclerViewAdapter(arrayListOf(), this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            repositoryName.text = "Medium Android Repository Article"
            repositoryOwner.text = "Kotlin Apply"
            numberOfStarts.text = "1000 stars"
        }

        var repository = Repository("Test Constructor name",
                "Fleka", 1000, true)
//        repository.repositoryName = "Test Set name"
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

//        Handler().postDelayed({repository.repositoryName="Observable Name"}, 2000)

        binding.repositoryRv.layoutManager = LinearLayoutManager(this)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter

        viewModel.repositories.observe(this,
                Observer<ArrayList<Repository>> { it?.let{ repositoryRecyclerViewAdapter.replaceData(it)} })

    }

}

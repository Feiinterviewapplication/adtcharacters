package com.feiyatsu.rickmortyapplication.ui.feed

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feiyatsu.networking.data.NetworkResource
import com.feiyatsu.networking.model.Character
import com.feiyatsu.rickmortyapplication.R
import com.feiyatsu.rickmortyapplication.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), CharacterListener {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val feedViewModel: FeedViewModel by viewModel()
    private val characterAdapter: CharacterAdapter by lazy {
        CharacterAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setRecyclerView()
        feedViewModel.fetchCharacters()
        observeViewModel()
    }

    private fun setRecyclerView() {
        binding.feedRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.feedRecyclerView.adapter = characterAdapter
        binding.feedRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (recyclerView.canScrollVertically(1).not() && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    feedViewModel.fetchCharacters()
                }
            }
        })

    }

    private fun observeViewModel() {
        feedViewModel.characters.observe(this, Observer {
            when (it) {
                is NetworkResource.Success -> {
                    binding.feedProgressBar.visibility = View.GONE
                    characterAdapter.addNewCharacters(it.data)
                }
                is NetworkResource.Error -> {
                    binding.feedProgressBar.visibility = View.GONE
                    Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show()
                }
                is NetworkResource.Loading -> {
                    binding.feedProgressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onCharacterClicked(character: Character) {
        AlertDialog.Builder(this)
            .setTitle(character.name)
            .setMessage(getString(R.string.feed_dialog_message, character.location.name))
            .setPositiveButton(R.string.close) { d, w ->
                d.dismiss()
            }
            .show()
    }
}
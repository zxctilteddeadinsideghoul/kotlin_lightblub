package com.example.blub.presenter.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.androidbroadcast.vbpd.viewBinding
import com.example.blub.R
import com.example.blub.data.model.BulbColorDto
import com.example.blub.databinding.FragmentMainBinding
import com.example.blub.di.ViewModelFactory
import com.example.blub.di.appComponent
import com.example.blub.presenter.viewmodel.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }
    private val binding by viewBinding(FragmentMainBinding::bind)

    private var colors: List<BulbColorDto> = emptyList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        observeViewModel()
        viewModel.loadInitialData()
    }

    private fun setupListeners() {
        // Switch listener for on/off
        binding.switchOnOff.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.turnOn()
            } else {
                viewModel.turnOff()
            }
        }

        binding.seekBarBrightness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvBrightnessValue.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.let {
                    viewModel.setBrightness(it.progress)
                }
            }
        })

        // Spinner listener for color
        binding.spinnerColor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (colors.isNotEmpty() && position >= 0 && position < colors.size) {
                    val selectedColor = colors[position]
                    viewModel.setColor(selectedColor.name)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun observeViewModel() {
        viewModel.brightnessLevelLiveData.observe(viewLifecycleOwner) { levels ->
            binding.seekBarBrightness.min = levels.min
            binding.seekBarBrightness.max = levels.max
        }

        viewModel.colorsLiveData.observe(viewLifecycleOwner) { colorList ->
            colors = colorList
            val colorNames = colorList.map { it.name }
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                colorNames
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerColor.adapter = adapter
        }

        // Observe errors
        viewModel.errorLiveData.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }
}


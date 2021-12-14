package com.interview.qpony.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.interview.qpony.R
import com.interview.qpony.databinding.FragmentCurrencyDetailBinding
import com.interview.qpony.viewmodel.HomeViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class CurrencyDetailFragment : DaggerFragment() {
    companion object {
        val TAG = CurrencyDetailFragment::class.simpleName
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentCurrencyDetailBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Cannot access view in after view destroyed and before view creation")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel =
            ViewModelProviders.of(requireActivity(), viewModelFactory)[HomeViewModel::class.java]
        bindView()
    }

    private fun bindView() {
        homeViewModel.currencyItemWrapper.observe(viewLifecycleOwner, { convertCurrency ->
            val dateTitle = getString(R.string.currency_detail_date_text, convertCurrency.date)
            val currencyNameTitle = getString(R.string.currency_detail_currency_text, convertCurrency.currencyName)
            val currencyRateTitle = getString(R.string.currency_detail_currency_value_text, String.format("%.2f", convertCurrency.value), convertCurrency.currencyName)
            binding.currencyDetailDateTextView.text = dateTitle
            binding.currencyDetailNameText.text = currencyNameTitle
            binding.currencyDetailRateText.text = currencyRateTitle
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
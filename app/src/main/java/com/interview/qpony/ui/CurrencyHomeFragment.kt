package com.interview.qpony.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.interview.qpony.databinding.FragmentCurrencyHomeBinding
import com.interview.qpony.model.CurrencyItemWrapper
import com.interview.qpony.ui.adapter.ExchangeRecyclerAdapter
import com.interview.qpony.ui.adapter.OnItemClickListener
import com.interview.qpony.viewmodel.HomeViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class CurrencyHomeFragment : DaggerFragment(), OnItemClickListener {

    companion object {
        val TAG = CurrencyHomeFragment::class.simpleName
    }


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var rvContacts: RecyclerView
    private lateinit var adapter: ExchangeRecyclerAdapter


    private lateinit var loadingPB: ProgressBar
    private lateinit var nestedSV: NestedScrollView


    private var _binding: FragmentCurrencyHomeBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Cannot access view in after view destroyed and before view creation")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel =
            ViewModelProviders.of(requireActivity(), viewModelFactory)[HomeViewModel::class.java]

        rvContacts = binding.exchangeRateRecycleView
        loadingPB = binding.idPBLoading
        nestedSV = binding.idNestedSV

        bindView()
        setupUi()
    }

    private fun setupUi() {
        adapter = ExchangeRecyclerAdapter(homeViewModel.getExchangeRateList(), this)
        rvContacts.adapter = adapter
        adapter.notifyDataSetChanged()


        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvContacts.layoutManager = layoutManager
        nestedSV.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                loadingPB.visibility = View.VISIBLE
                homeViewModel.getNextExchangeRateData()
            }
        })

    }

    private fun bindView() {
        homeViewModel.exchangeRateList.observe(viewLifecycleOwner, {
            adapter.notifyDataSetChanged()
        })

        homeViewModel.errorApi.observe(viewLifecycleOwner, { it ->
            it.getContentIfNotHandled()?.let {
                Toast.makeText(requireContext(), getString(it), Toast.LENGTH_LONG).show()
                loadingPB.visibility = View.INVISIBLE
            }
        })

    }

    override fun onItemClicked(currencyItemWrapper: CurrencyItemWrapper) {
        homeViewModel.currencyItemWrapper.value = currencyItemWrapper
        (activity as? MainActivity)?.switchFragment(CurrencyDetailFragment())
    }
}
package com.interview.qpony.ui.adapter


import android.content.Context
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.interview.qpony.R
import com.interview.qpony.databinding.ExchangeRateRecycleViewItemBinding
import com.interview.qpony.model.CurrencyItemWrapper
import com.interview.qpony.model.CurrencyItemWrapperType
import javax.inject.Singleton


class ExchangeRecyclerAdapter(
    private val exchangeRate: List<CurrencyItemWrapper>,
    private val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<ExchangeRecyclerAdapter.ViewHolder>() {


    inner class ViewHolder(binding: ExchangeRateRecycleViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val dateItemTextView = binding.exchangeRateItemDate
        private val nameItemTextView = binding.exchangeRateItemName
        private val rateItemTextView = binding.exchangeRateItemRate

        fun bind(currencyItemWrapper: CurrencyItemWrapper, clickListener: OnItemClickListener, context: Context) {
            when (currencyItemWrapper.type) {
                CurrencyItemWrapperType.DATE -> {
                    dateItemTextView.visibility = View.VISIBLE
                    dateItemTextView.text = currencyItemWrapper.date

                    nameItemTextView.visibility = View.INVISIBLE
                    rateItemTextView.visibility = View.INVISIBLE
                }
                CurrencyItemWrapperType.CURRENCY -> {
                    dateItemTextView.visibility = View.INVISIBLE

                    val currencyNameTitle = String.format(context.getString(R.string.currency_item_name_text), currencyItemWrapper.currencyName)
                    val currencyRateTitle = String.format(context.getString(R.string.currency_item_currency_value_text),String.format("%.2f", currencyItemWrapper.value), currencyItemWrapper.currencyName)
                    nameItemTextView.text = currencyNameTitle
                    rateItemTextView.text = currencyRateTitle

                    nameItemTextView.visibility = View.VISIBLE
                    rateItemTextView.visibility = View.VISIBLE
                    itemView.setOnClickListener {
                        clickListener.onItemClicked(currencyItemWrapper)
                    }
                }
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val binding = ExchangeRateRecycleViewItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currencyItemWrapper: CurrencyItemWrapper = exchangeRate[position]
        val context = holder.itemView.context
        holder.bind(currencyItemWrapper, itemClickListener, context)
    }

    override fun getItemCount(): Int {
        return exchangeRate.size
    }
}

@Singleton
interface OnItemClickListener {
    fun onItemClicked(currencyItemWrapper: CurrencyItemWrapper)
}
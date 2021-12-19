package com.example.tipping.view

import TipHistory
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.presentation.event.observeEvent
import com.example.presentation.fragment.autoCleared
import com.example.tipping.R
import com.example.tipping.databinding.DialogReceiptPaymentDetailsBinding
import com.example.tipping.databinding.FragmentTipsHistoryBinding
import com.example.tipping.databinding.TipsHistoryListItemBinding
import com.example.tipping.viewmodel.TipHistoryViewModel
import com.example.tipping.viewmodel.TipHistoryViewModelImpl
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.databinding.BindableItem
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat

class TipHistoryListFragment : Fragment() {

    private lateinit var binding: FragmentTipsHistoryBinding
    private val viewModel: TipHistoryViewModel by viewModel<TipHistoryViewModelImpl>()
    private var listAdapter: GroupieAdapter by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentTipsHistoryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        val dividerDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        listAdapter = GroupieAdapter()
        binding.paymentsHistoryList.adapter = listAdapter
        binding.paymentsHistoryList.addItemDecoration(dividerDecoration)
        viewModel.tipHistoryLoaded.observeEvent(viewLifecycleOwner) { list ->
            updateList(list)
        }
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_toolbar_back)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
        return binding.root
    }

    private fun updateList(historyItems: List<TipHistory>?) {
        if (historyItems != null) {
            val items = historyItems.map { historyItem ->
                TipHistoryItem(historyItem, ItemViewModel(requireActivity(), viewLifecycleOwner))
            }
            listAdapter.update(items)
        }
    }

    class TipHistoryItem(
        private val tipHistory: TipHistory,
        private val itemViewModel: ItemViewModel,
    ) : BindableItem<TipsHistoryListItemBinding>() {
        override fun isSameAs(other: Item<*>): Boolean {
            return other is TipHistoryItem &&
                tipHistory.id == other.tipHistory.id
        }

        override fun hasSameContentAs(other: Item<*>): Boolean {
            return other is TipHistoryItem &&
                tipHistory == other.tipHistory
        }

        override fun getLayout() = R.layout.tips_history_list_item

        override fun bind(binding: TipsHistoryListItemBinding, position: Int) {
            itemViewModel.tipHistory = tipHistory
            binding.viewModel = itemViewModel
        }
    }

    @SuppressLint("SimpleDateFormat")
    class ItemViewModel(private val context: Context, private val lifecycleOwner: LifecycleOwner) {
        lateinit var tipHistory: TipHistory

        val paymentAmount: String
            get() = "\$${tipHistory.payment}"

        val tipAmount: String
            get() = "Tip: \$${tipHistory.tipAmount}"

        val date: String
            get() = SimpleDateFormat("yyyy MMMM d").format(tipHistory.paymentDate)

        val receiptUrl: String?
            get() = tipHistory.receiptImageUriPath

        fun itemClicked() {
            launchDialog(lifecycleOwner, context, tipHistory)
        }

        private fun launchDialog(
            lifecycleOwner: LifecycleOwner,
            context: Context,
            tipHistory: TipHistory
        ) {
            val dialog = MaterialAlertDialogBuilder(context)
            dialog.background = context.getDrawable(R.drawable.bg_white)
            val binding = DataBindingUtil.inflate<DialogReceiptPaymentDetailsBinding>(
                LayoutInflater.from(dialog.context),
                R.layout.dialog_receipt_payment_details,
                null,
                false
            )
            binding.lifecycleOwner = lifecycleOwner
            tipHistory.receiptImageUriPath?.let { binding.receiptPhoto.setImageURI(it) }
            binding.paymentAmount.text = "\$${tipHistory.payment}"
            binding.paymentDate.text = SimpleDateFormat("yyyy MMMM d").format(tipHistory.paymentDate)
            binding.tipAmount.text = "Tip: \$${tipHistory.tipAmount}"

            dialog.setView(binding.root)
                .create()
                .also {
                    it.setCanceledOnTouchOutside(true)
                }.show()
        }
    }
}

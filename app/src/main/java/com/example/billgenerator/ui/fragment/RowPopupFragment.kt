import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.roomdatabase.data.entity.bill1.BillData1
import com.example.billgenerator.databinding.FragmentRowPopupBinding

class RowPopupFragment : DialogFragment() {

    companion object {
        const val TAG = "RowPopupFragment"
        private const val ARG_BILL_DATA = "billData"

        fun newInstance(billData: BillData1): RowPopupFragment {
            val fragment = RowPopupFragment()
            val args = Bundle()
            args.putParcelable(ARG_BILL_DATA, billData)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding: FragmentRowPopupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRowPopupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val billData = arguments?.getParcelable<BillData1>(ARG_BILL_DATA)

        if (billData != null) {
            // Populate UI elements with billData details
            binding.billDescriptionView.text = billData.billDescription1
            binding.billQuantityView.text = billData.billDescription2
            binding.billUnitPriceView.text = billData.billDescription3
            binding.billTotalView.text = billData.billDescription4
            // Add other fields as needed
        }

        binding.billButtonClose.setOnClickListener {
            dismiss()
        }
    }
}

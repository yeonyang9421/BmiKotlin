package kr.co.woobi.imyeon.bmikotlin


import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_bmi_main.*
import kotlinx.android.synthetic.main.fragment_result.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BmiMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

     val view = inflater.inflate(R.layout.fragment_bmi_main, container, false)

        val edit_height = view.findViewById<EditText>(R.id.edit_height);
        val edit_weight = view.findViewById<EditText>(R.id.edit_weight)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())

        val height = pref.getFloat("height", 0f)
        val weight = pref.getFloat("weight", 0f)

        if(height != 0f && weight != 0f){
            edit_height.setText(height.toString())
            edit_weight.setText(weight.toString())
        }

        button_result.setOnClickListener {
            val bundle= bundleOf(
                "height" to edit_height.text.toString().toDouble(),
                "weight" to edit_weight.text.toString().toDouble())
            it.findNavController().navigate(R.id.action_bmiMainFragment_to_resultFragment,bundle)
        }
    }
}

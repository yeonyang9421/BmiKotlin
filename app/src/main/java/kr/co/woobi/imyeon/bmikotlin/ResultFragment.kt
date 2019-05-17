package kr.co.woobi.imyeon.bmikotlin


import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import kotlinx.android.synthetic.main.fragment_bmi_main.*
import kotlinx.android.synthetic.main.fragment_result.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val height = arguments?.getDouble("height")
        val weight = arguments?.getDouble("weight")

        val bmi = weight!! / Math.pow(height!! / 100.0, 2.0)
        toast("$bmi")

        when {
            bmi >= 35 -> text_result.text = "고도비만"
            bmi >= 30 -> text_result.text = "2단계비만"
            bmi >= 25 -> text_result.text = "1단계 비만"
            bmi >= 23 -> text_result.text = "과체중"
            bmi >= 18.5 -> text_result.text = "정상"
            else -> text_result.text = "저체중"
        }

        var pref = PreferenceManager.getDefaultSharedPreferences(requireContext())

        pref.edit {
            putFloat("height", height?.toFloat())
            putFloat("weight", weight?.toFloat())


//        val editor = pref.edit()
//        editor.putFloat("height", height)
//            .putFloat("weight", weight)
//            .apply()

            Log.d("BmiResultFragment", "$height, $weight")
        }
    }
}


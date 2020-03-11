package com.elviraminnullina.veo_ride_test_2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_onboarding_one.*

class FragmentBoarding : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding_one, container, false)
    }

    var currentBoardingStep: Boarding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val boardingOneNext = view.findViewById<AppCompatTextView>(R.id.boarding_next)
        currentBoardingStep = arguments?.getSerializable("boarding") as? Boarding
        setContentDependingOnScreen()
        boardingOneNext?.setOnClickListener {
            val fragmentTransaction = fragmentManager?.beginTransaction();
            fragmentTransaction?.apply {
                add(R.id.fragment_onboarding, getInstance(boardingEnumPassingDecider()))
                        .addToBackStack(view.id.toString())
                commit()
            }
        }
        boarding_skip.setOnClickListener{
            Log.d("TAG", "Skip")
        }
    }

    private fun setContentDependingOnScreen() {
        if (currentBoardingStep == null) {
            return
        }
        var imageID = 0
        var boardingNextBtnValue: String = ""
        var boardingTitle: String = ""
        var boardingDescription: String = ""
        when (currentBoardingStep) {
            Boarding.boarding_one -> {
                imageID = R.drawable.starting_a_ride
                boardingNextBtnValue = getString(R.string.next)
                boardingTitle = getString(R.string.start_riding)
                boardingDescription = getString(R.string.ride_option_appear_on_map)
            }
            Boarding.boarding_two -> {
                imageID = R.drawable.scan_code
                boardingNextBtnValue = getString(R.string.next)
                boardingTitle = getString(R.string.scan_and_go)
                boardingDescription = getString(R.string.scan_the_code)
            }
            Boarding.boarding_three -> {
                imageID = R.drawable.safety
                boardingNextBtnValue = getString(R.string.got_it)
                boardingTitle = getString(R.string.safety_first)
                boardingDescription = getString(R.string.wear_helmet)
            }
        }
        boarding_image.setImageDrawable(ResourcesCompat.getDrawable(resources, imageID, null))
        boarding_next.text = boardingNextBtnValue
        boarding_main_text.text = boardingTitle
        boarding_description.text = boardingDescription

    }

    private fun boardingEnumPassingDecider(): Boarding? =
            when (currentBoardingStep) {
                Boarding.boarding_one ->
                    Boarding.boarding_two
                Boarding.boarding_two ->
                    Boarding.boarding_three
                else -> null
            }

    companion object {
        fun getInstance(fragmentId: Boarding?): FragmentBoarding {
            val fragment = FragmentBoarding()
            val bundle = Bundle()
            bundle.putSerializable("boarding", fragmentId)
            fragment.arguments = bundle
            return fragment
        }
    }
}
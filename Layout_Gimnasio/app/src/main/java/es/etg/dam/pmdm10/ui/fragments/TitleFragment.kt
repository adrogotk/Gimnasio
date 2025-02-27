package es.etg.dam.pmdm10.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.etg.dam.pmdm10.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment() {
    // TODO: Rename and change types of parameters
        // TODO: Rename and change types of parameters
        private var _binding: FragmentTitleBinding? = null
        val binding get() = _binding!! //Helper
        private var listener: FragmentActionsListener? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding= FragmentTitleBinding.inflate(inflater, container, false)
            return binding.root
        }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
        }
        override fun onAttach(context: Context) {
            super.onAttach(context)
            if (context is FragmentActionsListener) {
                listener = context
            }
        }
        override fun onDetach() {
            super.onDetach()
            listener = null
        }

}
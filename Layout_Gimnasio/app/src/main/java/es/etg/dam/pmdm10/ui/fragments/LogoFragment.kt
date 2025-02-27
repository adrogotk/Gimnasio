package es.etg.dam.pmdm10.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.etg.dam.pmdm10.databinding.FragmentLogoBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AvatarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LogoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentLogoBinding? = null
    val binding get() = _binding!! //Helper
    private var listener: FragmentActionsListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentLogoBinding.inflate(inflater, container, false)
        return binding.root
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
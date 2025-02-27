package es.etg.dam.pmdm10.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import es.etg.dam.pmdm10.ui.adapter.DataAdapter
import es.etg.dam.pmdm10.ui.viewmodels.ItemViewModel
import es.etg.dam.pmdm10.databinding.FragmentMenuBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    companion object{
        const val TEXTO_DESCRIPCION="Elemento "
        const val TEXTO_OPCION="Opcion"
    }
    // TODO: Rename and change types of parameters
    private var _binding: FragmentMenuBinding? = null
    val binding get() = _binding!! //Helper
    private var listener: FragmentActionsListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val data = ArrayList<ItemViewModel>()
            for (i in 1..20) {
                val image = android.R.drawable.arrow_up_float
                val descripcion = TEXTO_DESCRIPCION + i
                val opcion = TEXTO_OPCION
                data.add(ItemViewModel(image, descripcion, opcion))
            }
            val adapter = DataAdapter(data)
            val recyclerview = binding.menu
            recyclerview.layoutManager = LinearLayoutManager(requireContext())
            val activity = this.activity
            recyclerview.adapter = adapter
            adapter.setOnClickListener(object :
                DataAdapter.OnClickListener {
                override fun onClick(position: Int, model: ItemViewModel) {
                    val msg: String = "Ha saleccionado el elemento ${model.descripcion}"
                    Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
                }
            })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentMenuBinding.inflate(inflater, container, false)
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
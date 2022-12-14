package com.proyectgrupo9

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text
import java.sql.DataTruncation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_cuenta.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_perfil : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       val rootView:View = inflater.inflate(R.layout.fragment_perfil, container, false)

        val dataBundle = arguments
        val mail = dataBundle?.getString("correo")
        val nombre = dataBundle?.getString("nombre")
        val telefono = dataBundle?.getString("telefono")
        val contraseña = dataBundle?.getString("contraseña")



        var titulo = rootView.findViewById<TextView>(R.id.titulo)
        titulo?.text = "Aqui el titulo: "+ mail
        Toast.makeText(context, mail.toString(), Toast.LENGTH_SHORT).show()


        val btnSalir = rootView.findViewById<Button>(R.id.btnSalir)
        btnSalir.setOnClickListener{
            Toast.makeText(context, "SALIR", Toast.LENGTH_LONG).show()
        }

        val btnEliminar = rootView.findViewById<Button>(R.id.btnEliminar)
        btnEliminar.setOnClickListener {
            //Mensaje de eliminar
        }

        return rootView

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment_cuenta.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
            Fragment_cuenta().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}
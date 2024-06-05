package com.exa.android.inventorymanagement.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exa.android.inventorymanagement.Adapter.CategoryAdapter
import com.exa.android.inventorymanagement.Models.Category
import com.exa.android.inventorymanagement.R
import com.exa.android.inventorymanagement.databinding.FragmentHomeBinding
import com.exa.android.inventorymanagement.viewmodel.DataViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding ? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var image_uri : String ? = null
    lateinit var adapter : CategoryAdapter
    lateinit var viewmodel : DataViewModel

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){
       image_uri = it.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(this)[DataViewModel::class.java]

        binding.fab.setOnClickListener{
            showCategoryDialog()
        }

        adapter = CategoryAdapter(listOf())
        binding.categoryRecycler.adapter = adapter

        viewmodel.categories.observe(viewLifecycleOwner){
               adapter = CategoryAdapter(it)
        }

        return binding.root
    }

    private fun showCategoryDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.category_dialog, null)
        val edittxt = dialogView.findViewById<EditText>(R.id.category_edit)
        val insert_img = dialogView.findViewById<Button>(R.id.pickImageButton)

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add New Category")
            .setView(dialogView)
            .setPositiveButton("Add", null)
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }.create()

        dialog.setOnShowListener {
            val btn = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            insert_img.setOnClickListener {
                 getContent.launch("image/*")
            }
            btn.setOnClickListener {
                val name = edittxt.text.toString()
                if(name.isNotEmpty()){
                    if(image_uri == null){
                        image_uri = "https://th.bing.com/th/id/OIP.pBhlmwgZiVKO350vMesCZgHaBe?rs=1&pid=ImgDetMain"
                    }

                    viewmodel.insertCategory(Category(name, image_uri!!))

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container,StockFragment())
                        .addToBackStack("stockFragment")
                        .commit()
                    dialog.dismiss()

                }else{
                    edittxt.error = "Category Name Cannot Be Empty"
                }
            }
        }
        dialog.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
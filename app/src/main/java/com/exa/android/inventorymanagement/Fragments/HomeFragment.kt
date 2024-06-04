package com.exa.android.inventorymanagement.Fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.exa.android.inventorymanagement.R
import com.exa.android.inventorymanagement.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){
        binding.image.setImageURI(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.fab.setOnClickListener{
            showCategoryDialog()
        }

        val view = binding.root
        return view
    }private fun showCategoryDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.add_category, null)
        val edittxt = dialogView.findViewById<EditText>(R.id.edit)
        val insert_img = dialogView.findViewById<Button>(R.id.btn)

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
                val category = edittxt.text.toString()
                if(category.isNotEmpty()){
                    binding.categoryTxt.text = category

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
package com.exa.android.inventorymanagement.Fragments

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.exa.android.inventorymanagement.R
import com.exa.android.inventorymanagement.databinding.FragmentStockBinding
import com.exa.android.inventorymanagement.databinding.StockDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class StockFragment : Fragment() {
    private var _binding : FragmentStockBinding ? = null
    private val binding get() = _binding!!

    private var image_uri  : String? = null

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){
        image_uri = it.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentStockBinding.inflate(inflater, container, false)


        binding.addStockFab.setOnClickListener{
            showStockDialog()
        }

        return binding.root
    }

    fun showStockDialog(){

        val sBinding = StockDialogBinding.inflate(layoutInflater)

      val dialog = MaterialAlertDialogBuilder(requireContext())
          .setTitle("Add New Stock")
          .setView(sBinding.root)
          .setPositiveButton("Add", null)
          .setNegativeButton("Cancel"){dialog, _ ->
              dialog.dismiss()
          }.create()

        dialog.setOnShowListener {
            val btn = dialog.getButton(Dialog.BUTTON_POSITIVE)
            val name = sBinding.stockName.text.toString()
            val quantity = sBinding.quantity.text.toString()
            val price = sBinding.price.text.toString()

            sBinding.pickImageButton.setOnClickListener{
                getContent.launch("image/*")
            }

            if(name.isEmpty()){
                sBinding.stockName.error = "Name is Required"
            }else if(quantity.isEmpty()){
                sBinding.quantity.error = "Quantity is Required"
            }else if(price.isEmpty()){
                sBinding.price.error = "Price is Required"
            }else{
                val bundle = Bundle().apply{
                    putString("name", name)
                    putString("price",price)
                    putString("quantity",quantity)
                    putString("image",image_uri)
                }
                ItemFragment().arguments = bundle
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
package com.rakesh.mindbody

import android.app.Application
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rakesh.mindbody.adapter.ProvinceAdapter
import com.rakesh.mindbody.databinding.ActivityMainBinding
import com.rakesh.mindbody.viewmodel.UserViewModel

class ProvinceActivity : AppCompatActivity() {
    private var userViewModel: UserViewModel? = null
    var adapter: ProvinceAdapter? = null
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id: String = intent.getIntExtra("id", 0).toString()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        binding?.recyclerView?.setHasFixedSize(true)
        adapter = ProvinceAdapter()
        binding?.recyclerView?.adapter = adapter
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        setUp(id)
    }

    private fun setUp(id: String) {
        userViewModel?.getProvinces(id)
        userViewModel?.provinceList?.observe(this, {
            if(it.isEmpty()){
                showDialog()
            } else
            adapter!!.setUserList(ArrayList(it))
        })

        userViewModel?.loading?.observe(this, { loading ->
            loading?.let {
                if(it){
                    binding?.loading?.visibility = View.VISIBLE
                }else{
                    binding?.loading?.visibility = View.GONE
                }
            }

        })

        adapter!!.onItemClick = { province ->
            Toast.makeText(
                applicationContext,
                "You clicked : " + province?.name,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Info")
        builder.setMessage("Data not available.")
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { dialogInterface: DialogInterface?, i: Int ->
            dialogInterface?.dismiss()
            finish()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }
}
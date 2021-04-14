package com.rakesh.mindbody

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakesh.mindbody.adapter.UserAdapter
import com.rakesh.mindbody.databinding.ActivityMainBinding
import com.rakesh.mindbody.viewmodel.UserViewModel


class MainActivity : AppCompatActivity() {
    private var userViewModel: UserViewModel? = null
    private var adapter: UserAdapter? = null
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        binding?.recyclerView?.setHasFixedSize(true)
        adapter = UserAdapter()
        binding?.recyclerView?.adapter = adapter
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        setup()
    }

    private fun setup() {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        userViewModel?.getCountries()
        userViewModel?.countryList?.observe(this, {
            adapter!!.setUserList(ArrayList(it))
        })

        userViewModel?.loading?.observe(this, { loading ->
            loading?.let {
                if (it) {
                    binding?.loading?.visibility = View.VISIBLE
                } else {
                    binding?.loading?.visibility = View.GONE
                }
            }

        })
        adapter!!.onItemClick = { user ->
            startActivity(Intent(this, ProvinceActivity::class.java).putExtra("id", user?.id))
        }
    }

}
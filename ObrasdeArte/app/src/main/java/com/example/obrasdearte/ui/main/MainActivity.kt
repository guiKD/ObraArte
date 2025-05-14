package com.example.obrasdearte.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.obrasdearte.R
import com.example.obrasdearte.databinding.ActivityMainBinding
import com.example.obrasdearte.ui.detalhe.DetalheActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configuraLista()
    }
    private fun configuraLista() {
        adapter = MainListAdapter(
            onClick = { obra ->
                val detalheIntent = Intent(this, DetalheActivity::class.java)
                    .apply {
                        putExtra(DetalheActivity.EXTRA_OBRA_ARTE, obra)
                    }
                startActivity(detalheIntent)
            }
        )
        binding.rvObrasDeArte.layoutManager =
            LinearLayoutManager(this)
        binding.rvObrasDeArte.adapter = adapter
        viewModel.lista.observe(this) { obras ->
            adapter.submitList(obras)
        }
    }
}
package com.example.obrasdearte.ui.detalhe

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.obrasdearte.R
import com.example.obrasdearte.data.ObraArte
import com.example.obrasdearte.databinding.ActivityDetalheBinding
import com.squareup.picasso.Picasso

class DetalheActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalheBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheBinding .inflate(layoutInflater)
        setContentView( binding.root)
        configuraObraDeArte(getObraDeArteVindoDaOutraTela())
    }
    companion object {
        const val EXTRA_OBRA_ARTE = "EXTRA_OBRA_ARTE"
    }
    private fun getObraDeArteVindoDaOutraTela (): ObraArte? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES .TIRAMISU) {
            intent.getParcelableExtra( EXTRA_OBRA_ARTE ,
                ObraArte::class.java)
        } else {
            @Suppress("DEPRECATION" )
            intent.getParcelableExtra( EXTRA_OBRA_ARTE )
        }
    }
    private fun configuraObraDeArte (obraDeArte: ObraArte?) {
        obraDeArte?. let {
            with(binding) {
                tvTitulo.text = it.titulo
                tvDescricao .text = it.descricao
                Picasso.get().load( it.imagem)
                    .error( R.drawable.erro)
                    .placeholder( R.drawable.loading)
                    .into( ivObraDeArte )
            }
        }
    }
}
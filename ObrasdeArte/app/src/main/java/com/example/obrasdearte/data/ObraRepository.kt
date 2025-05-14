package com.example.obrasdearte.data

import android.content.Context
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class ObraRepository(private val context: Context) {
    fun carregarObras(): List<ObraArte> {
        val json =
            context.assets.open("obras.json").bufferedReader().use { it.readText()
            }
        val tipo = object : TypeToken<List<ObraArte>>() {}.type
        return Gson().fromJson(json, tipo)
    }
}
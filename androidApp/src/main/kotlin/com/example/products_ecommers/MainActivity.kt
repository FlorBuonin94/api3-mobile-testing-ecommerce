package com.example.products_ecommers

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.emptyList


class MainActivity : ComponentActivity() {
    private var listaProductos = mutableStateOf<List<Product>>(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        obtenerProductos()

        setContent {
            PantallaListadoProductos(productos = listaProductos.value)
    }
}

private fun obtenerProductos(){

    //Configuramos el client con la URL que corregimos
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.jsonkeeper.com/") //Updated base URL
        .addConverterFactory(GsonConverterFactory.create()) //Convierte el texto JSON a objeto Kotlin
        .build()

    val service = retrofit.create(ProductService::class.java)

    val call = service.getProducts()

    call.enqueue(object: Callback<ProductResponse> { //Cola de espera

        override fun onResponse(call: Call<ProductResponse?>, response: Response<ProductResponse?>) {
            if (response.isSuccessful) {
                val cuerpo = response.body()
                if (cuerpo != null) {
                    //Guardamos los productos en nuestro "Cerebro"
                    listaProductos.value = cuerpo.products
                }
            } else {
                Log.e("API_TEST", "Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<ProductResponse?>, t: Throwable) {
            Log.e("API_TEST", "Fallo de conexión: ${t.message}")
        }
    })
}

@Composable
fun PantallaListadoProductos(productos: List<Product>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 16.dp, end = 16.dp)
    ){
        Text(
            text = "Mis Productos (API)",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(productos) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ){
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = item.name, fontSize = 18.sp, fontWeight = FontWeight.Bold) //letra en negrito del título
                        Spacer(modifier = Modifier.height(4.dp)) //genera un interlineado hacia abajo
                        Text(text = "Precio: ${item.currency} ${item.price}", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}
}
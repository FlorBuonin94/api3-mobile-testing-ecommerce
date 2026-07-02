package com.example.products_ecommers

import androidx.activity.compose.rememberLauncherForActivityResult
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    private val activity = MainActivity()

    @Test
    fun `Dado un listado de productos validar que la información se pueda mostrar en pantalla`() {

        //Preparar datos de prueba
        val productos = listOf(
            Product(
                id = 1,
                name = "iPhone 13",
                description = "The latest iPhone from Apple",
                price = 999.99,
                currency = "USD",
                in_stock= true
            ),
            Product(
                id = 2,
                name = "Samsung Galaxy S21",
                description = "The latest Samsung phone",
                price = 899.99,
                currency = "USD",
                in_stock= true
        ),
            Product(
                id = 3,
                name = "Google Pixel 6",
                description = "The latest Google phone",
                price = 799.99,
                currency = "USD",
                in_stock= false
        )
        )

        //Ejecutamos el método
        val resultado = activity.obtenerListadoProductos(productos)

        //Validamos los resultados
        assertEquals(
            """
                ID: 1, Nombre: iPhone 13
                ID: 2, Nombre: Samsung Galaxy S21
                ID: 3, Nombre: Google Pixel 6
            """.trimIndent(),
            resultado
        )
    }

    @Test
    fun `Cuando el listado este vacio validar que no se puede mostrar info en pantalla`(): Unit {
        //Preparamos los datos de prueba
        val productos = emptyList<Product>()

        //Ejecutamos el método
        val resultado = activity.obtenerListadoProductos(productos)

        //Validar los resultados
        assertEquals("No hay productos disponibles", resultado)
    }
}
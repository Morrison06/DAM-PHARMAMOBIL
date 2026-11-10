package pe.edu.upeu.pharmamobil.domain.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ProductoReleaseTest {
    @Test
    fun productoValidoConStockSuficiente() {
        val producto = Producto(id = 1, nombre = "Paracetamol", precio = 8.5, stock = 15)
        assertEquals("Paracetamol", producto.nombre)
        assertFalse(producto.requiereReposicion)
    }

    @Test
    fun productoBajoMinimoRequiereReposicion() {
        val producto = Producto(id = 2, nombre = "Gasas", precio = 2.5, stock = 3)
        assertTrue(producto.requiereReposicion)
    }

    @Test
    fun rechazaPrecioInvalidoYStockNegativo() {
        assertFailsWith<IllegalArgumentException> {
            Producto(id = 3, nombre = "Prueba", precio = 0.0, stock = 1)
        }
        assertFailsWith<IllegalArgumentException> {
            Producto(id = 4, nombre = "Prueba", precio = 1.0, stock = -1)
        }
    }
}

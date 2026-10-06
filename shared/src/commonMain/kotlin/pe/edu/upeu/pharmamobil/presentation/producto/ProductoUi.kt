package pe.edu.upeu.pharmamobil.presentation.producto

import pe.edu.upeu.pharmamobil.domain.model.Producto
import pe.edu.upeu.pharmamobil.platform.formatearSoles

/**
 * Modelo listo para pintar. El precio se transforma aqui, en presentation,
 * y no dentro del composable ni modificando el modelo de dominio.
 */
data class ProductoUi(
    val id: Long,
    val nombre: String,
    val precio: String,
    val stock: String,
    val requiereReposicion: Boolean,
    val precioValor: Double,
    val stockValor: Int
)

fun Producto.aUi(): ProductoUi = ProductoUi(
    id = id,
    nombre = nombre,
    precio = formatearSoles(precio),
    stock = "$stock u.",
    requiereReposicion = requiereReposicion,
    precioValor = precio,
    stockValor = stock
)

/** Permite que el detalle entregue al ViewModel un Producto del dominio. */
fun ProductoUi.aDominio(): Producto = Producto(
    id = id,
    nombre = nombre,
    precio = precioValor,
    stock = stockValor
)

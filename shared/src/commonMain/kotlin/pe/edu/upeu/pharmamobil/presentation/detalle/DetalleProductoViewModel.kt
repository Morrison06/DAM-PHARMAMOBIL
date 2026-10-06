package pe.edu.upeu.pharmamobil.presentation.detalle

import androidx.lifecycle.ViewModel
import pe.edu.upeu.pharmamobil.domain.model.Producto
import pe.edu.upeu.pharmamobil.domain.platform.Compartidor
import pe.edu.upeu.pharmamobil.domain.usecase.comoTextoParaCompartir

class DetalleProductoViewModel(
    private val compartidor: Compartidor
) : ViewModel() {

    fun compartir(producto: Producto) {
        compartidor.compartir(producto.comoTextoParaCompartir())
    }
}

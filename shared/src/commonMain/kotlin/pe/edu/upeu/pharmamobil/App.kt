package pe.edu.upeu.pharmamobil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import pe.edu.upeu.pharmamobil.presentation.detalle.DetalleProductoScreen
import pe.edu.upeu.pharmamobil.presentation.producto.ProductoScreen
import pe.edu.upeu.pharmamobil.presentation.producto.ProductoUi
import pe.edu.upeu.pharmamobil.theme.PharmaMobilTheme

@Composable
fun App() = KoinContext {
    var seleccionado by remember { mutableStateOf<ProductoUi?>(null) }

    PharmaMobilTheme {
        val producto = seleccionado

        if (producto == null) {
            ProductoScreen(
                viewModel = koinViewModel(),
                onVerDetalle = { seleccionado = it }
            )
        } else {
            DetalleProductoScreen(
                producto = producto,
                viewModel = koinViewModel(),
                onVolver = { seleccionado = null }
            )
        }
    }
}

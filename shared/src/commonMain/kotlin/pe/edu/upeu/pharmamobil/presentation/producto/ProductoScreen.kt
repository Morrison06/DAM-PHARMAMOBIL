package pe.edu.upeu.pharmamobil.presentation.producto

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pe.edu.upeu.pharmamobil.presentation.components.EstadoVacio
import pe.edu.upeu.pharmamobil.presentation.components.MensajeExito
import pe.edu.upeu.pharmamobil.presentation.components.ValidatedTextField

@Composable
fun ProductoScreen(
    viewModel: ProductoViewModel,
    onVerDetalle: (ProductoUi) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Registrar producto", style = MaterialTheme.typography.titleMedium)

                ValidatedTextField(
                    value = uiState.formulario.nombre,
                    onValueChange = viewModel::onNombreChange,
                    label = "Nombre",
                    error = uiState.formulario.nombreError,
                    leadingIcon = Icons.Default.Medication
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ValidatedTextField(
                        value = uiState.formulario.precio,
                        onValueChange = viewModel::onPrecioChange,
                        label = "Precio",
                        error = uiState.formulario.precioError,
                        ayuda = "En soles",
                        keyboardType = KeyboardType.Decimal,
                        modifier = Modifier.weight(1f)
                    )
                    ValidatedTextField(
                        value = uiState.formulario.stock,
                        onValueChange = viewModel::onStockChange,
                        label = "Stock",
                        error = uiState.formulario.stockError,
                        ayuda = "Unidades",
                        keyboardType = KeyboardType.Number,
                        modifier = Modifier.weight(1f)
                    )
                }

                Button(
                    onClick = viewModel::registrar,
                    enabled = !uiState.registrando,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (uiState.registrando) "Registrando…" else "Registrar")
                }
            }
        }

        uiState.mensajeExito?.let { MensajeExito(it) }

        Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
            when (val fase = uiState.fase) {
                ProductoUiState.Fase.Cargando -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )

                ProductoUiState.Fase.SinProductos -> EstadoVacio(
                    icono = Icons.Default.Inventory2,
                    titulo = "Todavía no hay productos",
                    descripcion = "Registra el primero con el formulario.",
                    modifier = Modifier.align(Alignment.Center)
                )

                is ProductoUiState.Fase.ConProductos -> LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(fase.productos, key = { it.id }) { producto ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onVerDetalle(producto) }
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(producto.nombre, style = MaterialTheme.typography.titleSmall)
                                Text(
                                    "${producto.precio} · ${producto.stock}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }

                is ProductoUiState.Fase.Error -> EstadoVacio(
                    icono = Icons.Default.CloudOff,
                    titulo = "No pudimos cargar el inventario",
                    descripcion = fase.mensaje,
                    modifier = Modifier.align(Alignment.Center),
                    accion = {
                        FilledTonalButton(onClick = viewModel::cargarProductos) {
                            Text("Reintentar")
                        }
                    }
                )
            }
        }
    }
}

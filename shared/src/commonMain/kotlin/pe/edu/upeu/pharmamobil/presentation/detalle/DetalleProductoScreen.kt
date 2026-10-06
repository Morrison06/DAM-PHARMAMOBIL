package pe.edu.upeu.pharmamobil.presentation.detalle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pe.edu.upeu.pharmamobil.presentation.producto.ProductoUi
import pe.edu.upeu.pharmamobil.presentation.producto.aDominio

@Composable
fun DetalleProductoScreen(
    producto: ProductoUi,
    viewModel: DetalleProductoViewModel,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(Icons.Default.Medication, contentDescription = null)
                    Text(producto.nombre, style = MaterialTheme.typography.headlineSmall)
                }

                HorizontalDivider()

                Text(
                    text = "Precio",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(producto.precio, style = MaterialTheme.typography.titleLarge)

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(Icons.Default.Inventory2, contentDescription = null)
                    Text("Stock: ${producto.stock}")
                }

                if (producto.requiereReposicion) {
                    Text(
                        text = "Stock bajo: requiere reposicion",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Spacer(Modifier.height(4.dp))

                Button(
                    onClick = { viewModel.compartir(producto.aDominio()) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Share, contentDescription = null)
                    Text("Compartir", modifier = Modifier.padding(start = 8.dp))
                }

                OutlinedButton(
                    onClick = onVolver,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Volver al inventario")
                }
            }
        }
    }
}

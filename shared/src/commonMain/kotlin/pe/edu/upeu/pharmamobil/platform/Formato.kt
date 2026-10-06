package pe.edu.upeu.pharmamobil.platform

/**
 * Capacidad nativa declarada en codigo comun.
 * Cada plataforma decide como representar la moneda peruana.
 */
expect fun formatearSoles(valor: Double): String

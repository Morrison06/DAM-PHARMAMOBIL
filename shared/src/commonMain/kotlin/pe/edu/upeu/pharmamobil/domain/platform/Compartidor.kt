package pe.edu.upeu.pharmamobil.domain.platform

/**
 * Contrato comun para una capacidad que depende del sistema operativo.
 * commonMain conoce el contrato, pero no Android ni iOS.
 */
interface Compartidor {
    fun compartir(texto: String)
}

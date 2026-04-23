package ni.edu.uam.appdehbitos.model

/**
 * Modelo de datos para representar un hábito.
 */
data class Habito(
    val nombre: String,
    val meta: String,
    val categoria: String,
    val completado: Boolean = false
)

package ni.edu.uam.appdehbitos.ui.theme.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ni.edu.uam.appdehbitos.model.Habito

/**
 * Componente que representa un hábito individual en la lista.
 */
@Composable
fun HabitItem(
    habito: Habito,
    modoOscuro: Boolean,
    colorTexto: Color,
    colorSubtexto: Color,
    colorTarjeta: Color,
    onCheckedChange: (Boolean) -> Unit
) {
    // Animación suave de color cuando el hábito se marca como completado
    val colorFondoActual by animateColorAsState(
        targetValue = if (habito.completado) Color(0xFFE8F5E9).copy(alpha = if(modoOscuro) 0.1f else 1f) else colorTarjeta,
        animationSpec = tween(durationMillis = 300)
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(if (modoOscuro) 0.dp else 4.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp)),
        color = colorFondoActual
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Control de selección
            Checkbox(
                checked = habito.completado,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF4CAF50),
                    uncheckedColor = colorSubtexto
                )
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Nombre del hábito
                Text(
                    text = habito.nombre,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorTexto
                )
                // Meta y Categoría
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = habito.meta,
                        fontSize = 13.sp,
                        color = colorSubtexto
                    )
                    Text(
                        text = " • ",
                        color = colorSubtexto
                    )
                    // Mini etiqueta de categoría
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(getCategoriaColor(habito.categoria).copy(alpha = 0.2f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = habito.categoria,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = getCategoriaColor(habito.categoria)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Función auxiliar para asignar colores a las categorías.
 */
fun getCategoriaColor(categoria: String): Color {
    return when (categoria) {
        "Salud" -> Color(0xFF2196F3)
        "Estudio" -> Color(0xFFFF9800)
        "Trabajo" -> Color(0xFF9C27B0)
        else -> Color(0xFF607D8B)
    }
}

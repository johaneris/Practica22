package ni.edu.uam.appdehbitos.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Componente que muestra el resumen de los días de la semana.
 */
@Composable
fun WeeklySummary(
    progreso: Float,
    colorTexto: Color,
    colorIncompleto: Color
) {
    val dias = listOf("L", "M", "X", "J", "V", "S", "D")
    
    // Determinamos cuántos días marcar como completados basándonos en el progreso actual
    // para dar un efecto visual de avance semanal.
    val diasCompletados = (progreso * 7).toInt()

    Column {
        Text(
            text = "Resumen semanal",
            color = colorTexto,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            dias.forEachIndexed { index, dia ->
                DiaCirculo(
                    letra = dia,
                    completado = index < diasCompletados,
                    colorTexto = colorTexto,
                    colorIncompleto = colorIncompleto
                )
            }
        }
    }
}

/**
 * Representación individual de un día de la semana.
 */
@Composable
fun DiaCirculo(
    letra: String,
    completado: Boolean,
    colorTexto: Color,
    colorIncompleto: Color
) {
    val colorCirculo = if (completado) Color(0xFF4CAF50) else colorIncompleto

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(colorCirculo),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = letra,
                color = if (completado) Color.White else colorTexto,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

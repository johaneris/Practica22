package ni.edu.uam.appdehbitos.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Tarjeta que muestra el progreso diario de los hábitos.
 */
@Composable
fun ProgressCard(
    progreso: Float,
    porcentaje: Int,
    completados: Int,
    total: Int,
    colorTexto: Color,
    colorSubtexto: Color,
    colorTarjetaProgreso: Color,
    colorBarraFondo: Color,
    colorBarraProgreso: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(colorTarjetaProgreso)
            .padding(20.dp)
    ) {
        Column {
            Text(
                text = "Progreso de hoy",
                color = colorTexto,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Barra de progreso personalizada
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .clip(RoundedCornerShape(50))
                    .background(colorBarraFondo)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progreso.coerceIn(0f, 1f))
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(50))
                        .background(colorBarraProgreso)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Texto del porcentaje
            Text(
                text = "$porcentaje%",
                color = colorTexto,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Resumen de conteo
            Text(
                text = "$completados de $total hábitos completados",
                color = colorSubtexto,
                fontSize = 14.sp
            )
        }
    }
}

package ni.edu.uam.appdehbitos.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Sección de encabezado con saludo y notificación.
 */
@Composable
fun HeaderSection(
    colorTexto: Color,
    colorTarjeta: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Saludo personalizado
        Text(
            text = "Hola, Johaneris 👋",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = colorTexto
        )

        // Icono de campana decorativo
        Surface(
            shape = CircleShape,
            color = colorTarjeta
        ) {
            Box(
                modifier = Modifier.size(42.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🔔",
                    fontSize = 20.sp
                )
            }
        }
    }
}

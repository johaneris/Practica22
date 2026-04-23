package ni.edu.uam.appdehbitos.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ni.edu.uam.appdehbitos.model.Habito
import ni.edu.uam.appdehbitos.ui.theme.components.*

/**
 * Pantalla principal del Dashboard de Hábitos.
 * Maneja el estado global de la lista, el progreso y el modo visual.
 */
@Composable
fun DashboardHabitosScreen() {
    // Estado para el modo oscuro/claro (Simulado visualmente)
    var modoOscuro by remember { mutableStateOf(false) }

    // Lista de hábitos iniciales
    val habitos = remember {
        mutableStateListOf(
            Habito("Beber agua", "2L", "Salud", true),
            Habito("Leer 20 min", "8:00 PM", "Estudio", false),
            Habito("Revisar tareas", "30 min", "Trabajo", true),
            Habito("Caminar", "25 min", "Salud", false)
        )
    }

    // Cálculos dinámicos de progreso
    val completados = habitos.count { it.completado }
    val totalHabitos = habitos.size
    val progreso = if (totalHabitos > 0) completados.toFloat() / totalHabitos.toFloat() else 0f
    val porcentaje = (progreso * 100).toInt()

    // Definición de colores basada en el estado modoOscuro
    val fondoPantalla = if (modoOscuro) Color(0xFF121212) else Color(0xFFF7F8FC)
    val colorTexto = if (modoOscuro) Color(0xFFF5F5F5) else Color(0xFF1E1E1E)
    val colorSubtexto = if (modoOscuro) Color(0xFFBDBDBD) else Color(0xFF666666)
    val colorTarjeta = if (modoOscuro) Color(0xFF1F1F1F) else Color.White
    val colorTarjetaProgreso = if (modoOscuro) Color(0xFF263238) else Color(0xFFE8F5E9)
    val colorBarraFondo = if (modoOscuro) Color(0xFF37474F) else Color(0xFFC8E6C9)
    val colorBarraProgreso = Color(0xFF4CAF50)
    val colorCirculoIncompleto = if (modoOscuro) Color(0xFF424242) else Color(0xFFD9D9D9)

    // Contenedor principal con Box para permitir el botón flotante sobre el contenido
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoPantalla)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .padding(bottom = 80.dp) // Espacio para evitar que el botón tape el último elemento
        ) {
            
            // 1. Encabezado
            HeaderSection(colorTexto, colorTarjeta)

            Spacer(modifier = Modifier.height(8.dp))

            // Selector de Modo Oscuro/Claro
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Modo visual: ${if (modoOscuro) "Oscuro" else "Claro"}",
                    color = colorSubtexto,
                    fontSize = 14.sp
                )
                Switch(
                    checked = modoOscuro,
                    onCheckedChange = { modoOscuro = it }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Tarjeta de progreso dinámico
            ProgressCard(
                progreso = progreso,
                porcentaje = porcentaje,
                completados = completados,
                total = totalHabitos,
                colorTexto = colorTexto,
                colorSubtexto = colorSubtexto,
                colorTarjetaProgreso = colorTarjetaProgreso,
                colorBarraFondo = colorBarraFondo,
                colorBarraProgreso = colorBarraProgreso
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Lista de Hábitos
            Text(
                text = "Tus hábitos",
                color = colorTexto,
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            habitos.forEachIndexed { index, habito ->
                HabitItem(
                    habito = habito,
                    modoOscuro = modoOscuro,
                    colorTexto = colorTexto,
                    colorSubtexto = colorSubtexto,
                    colorTarjeta = colorTarjeta,
                    onCheckedChange = { nuevoEstado ->
                        // Actualización del estado en la lista
                        habitos[index] = habitos[index].copy(completado = nuevoEstado)
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 4. Resumen semanal vinculado al progreso
            WeeklySummary(
                progreso = progreso,
                colorTexto = colorTexto,
                colorIncompleto = colorCirculoIncompleto
            )
        }

        // 5. Botón Flotante Simulado
        Box(
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            FloatingAddButton(
                onClick = {
                    // Acción simulada de añadir un nuevo hábito
                    val num = habitos.size + 1
                    habitos.add(Habito("Nuevo hábito $num", "Meta $num", "Salud", false))
                }
            )
        }
    }
}

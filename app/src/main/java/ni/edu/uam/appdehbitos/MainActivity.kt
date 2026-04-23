package ni.edu.uam.appdehbitos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ni.edu.uam.appdehbitos.ui.theme.AppDeHabitosTheme
import ni.edu.uam.appdehbitos.ui.theme.screens.DashboardHabitosScreen

/**
 * Actividad principal que sirve como punto de entrada de la aplicación.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Configuración para extender el contenido a los bordes de la pantalla
        enableEdgeToEdge()
        
        setContent {
            // Aplicamos el tema personalizado del proyecto (Nombre sin tildes)
            AppDeHabitosTheme {
                // Llamamos a la pantalla principal del Dashboard
                DashboardHabitosScreen()
            }
        }
    }
}

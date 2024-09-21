import androidx.compose.runtime.*
import io.reyurnible.order.OrderApp
import io.reyurnible.order.di.endPointsModule
import io.reyurnible.order.di.koinConfiguration
import io.reyurnible.order.ui.theme.OrderAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        koinConfiguration()
    }) {
        OrderAppTheme {
            OrderApp()
        }
    }
}

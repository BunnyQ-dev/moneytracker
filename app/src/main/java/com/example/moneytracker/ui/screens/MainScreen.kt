import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("overview") },
                    icon = { Icon(Icons.Default.Home, "Overview") },
                    label = { Text("Overview") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("transactions") },
                    icon = { Icon(Icons.Default.List, "Transactions") },
                    label = { Text("Transactions") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("statistics") },
                    icon = { Icon(Icons.Default.BarChart, "Statistics") },
                    label = { Text("Statistics") }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "overview",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("overview") { OverviewScreen() }
            composable("transactions") { TransactionsScreen() }
            composable("statistics") { StatisticsScreen() }
        }
    }
}
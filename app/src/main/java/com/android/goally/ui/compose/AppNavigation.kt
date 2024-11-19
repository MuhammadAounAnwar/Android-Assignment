package com.android.goally.ui.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.android.goally.ui.activitydetail.RoutineDetailScreen
import com.android.goally.ui.copilot.CopilotScreen
import com.android.goally.ui.copilot.CopilotViewModel


object Routes {
    const val ROUTINES_LIST = "routinesList"
    const val ROUTINE_DETAIL = "routineDetail/{routineId}"

    fun routineDetailRoute(routineId: String): String = "routineDetail/$routineId"
}

@Composable
fun AppNavigation(viewModel: CopilotViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.ROUTINES_LIST) {
        composable(Routes.ROUTINES_LIST) {
            CopilotScreen(viewModel = viewModel, navController = navController)
        }
        composable(Routes.ROUTINE_DETAIL) { backStackEntry ->
            val routineId = backStackEntry.arguments?.getString("routineId")
            routineId?.let {
                RoutineDetailScreen(viewModel.getRoutineById(it))
            }
        }
    }
}

fun NavController.navigateToRoutineDetail(routineId: String) {
    navigate(Routes.routineDetailRoute(routineId))
}

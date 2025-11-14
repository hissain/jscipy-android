package com.hissain.samplejscipyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hissain.jscipy.signal.FindPeaks
import com.hissain.jscipy.signal.ButterworthFilter
import com.hissain.jscipy.signal.RK4Solver
import com.hissain.jscipy.signal.api.IFindPeaks
import com.hissain.jscipy.signal.api.IButterworthFilter
import com.hissain.jscipy.signal.api.IRK4Solver
import com.hissain.samplejscipyapp.ui.theme.SampleJscipyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleJscipyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("jSciPy Library Demo", style = MaterialTheme.typography.headlineMedium)
                        Text("--------------------", style = MaterialTheme.typography.headlineSmall)

                        // FindPeaks Demo
                        FindPeaksDemo()

                        // ButterworthFilter Demo
                        ButterworthFilterDemo()

                        // RK4Solver Demo
                        RK4SolverDemo()
                    }
                }
            }
        }
    }
}

@Composable
fun FindPeaksDemo() {
    val signal = doubleArrayOf(0.0, 1.0, 0.5, 2.0, 1.5, 3.0, 2.5, 0.0)
    val findPeaks: IFindPeaks = FindPeaks()
    val params = FindPeaks.PeakParams().apply {
        distance = 1
        height = 1.0
    }
    val result = findPeaks.findPeaks(signal, params)

    Text("\nFindPeaks Demo:", style = MaterialTheme.typography.titleMedium)
    Text("Signal: ${signal.contentToString()}")
    Text("Peaks found at indices: ${result.peaks.contentToString()}")
}

@Composable
fun ButterworthFilterDemo() {
    val signal = DoubleArray(10) { i -> Math.sin(2 * Math.PI * 0.1 * i) + (Math.random() - 0.5) * 0.1 }
    val filter: IButterworthFilter = ButterworthFilter()
    val sampleRate = 100.0
    val cutoff = 10.0
    val order = 2

    val filteredSignal = filter.filtfilt(signal, sampleRate, cutoff, order)

    Text("\nButterworthFilter Demo:", style = MaterialTheme.typography.titleMedium)
    Text("Original Signal (first 5): ${signal.take(5).map { "%.2f".format(it) }}")
    Text("Filtered Signal (first 5): ${filteredSignal.take(5).map { "%.2f".format(it) }}")
}

@Composable
fun RK4SolverDemo() {
    val solver: IRK4Solver = RK4Solver()
    val equation = IRK4Solver.DifferentialEquation { t, y -> -2 * t * y }
    val y0 = 1.0
    val t0 = 0.0
    val tf = 1.0
    val h = 0.1

    val solution = solver.solve(equation, y0, t0, tf, h)

    Text("\nRK4Solver Demo:", style = MaterialTheme.typography.titleMedium)
    Text("Time points (first 5): ${solution.t.take(5).map { "%.2f".format(it) }}")
    Text("Solution values (first 5): ${solution.y.take(5).map { "%.2f".format(it) }}")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SampleJscipyAppTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("jSciPy Library Demo", style = MaterialTheme.typography.headlineMedium)
            Text("--------------------", style = MaterialTheme.typography.headlineSmall)
            FindPeaksDemo()
            ButterworthFilterDemo()
            RK4SolverDemo()
        }
    }
}

package com.hissain.samplejscipyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hissain.jscipy.signal.Signal
import com.hissain.jscipy.signal.fft.FFT
import com.hissain.jscipy.signal.math.RK4Solver
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
                    val scrollState = rememberScrollState()
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .verticalScroll(scrollState)
                    ) {
                        Text("jSciPy Library Demo", style = MaterialTheme.typography.headlineMedium)
                        Text("--------------------", style = MaterialTheme.typography.headlineSmall)

                        FindPeaksDemo()
                        ButterworthFilterDemo()
                        ChebyshevFilterDemo()
                        RK4SolverDemo()
                        ResampleDemo()
                        FFTDemo()
                        InterpolationDemo()
                        SavitzkyGolayDemo()
                    }
                }
            }
        }
    }
}

@Composable
fun FindPeaksDemo() {
    val signal = doubleArrayOf(0.0, 1.0, 0.5, 2.0, 1.5, 3.0, 2.5, 0.0)
    val peaks = Signal.find_peaks(signal, 1.0, 1, null)

    Text("\nFindPeaks Demo:", style = MaterialTheme.typography.titleMedium)
    Text("Signal: ${signal.contentToString()}")
    Text("Peaks found at indices: ${peaks.contentToString()}")
}

@Composable
fun ButterworthFilterDemo() {
    val signal = DoubleArray(10) { i -> Math.sin(2 * Math.PI * 0.1 * i) + (Math.random() - 0.5) * 0.1 }
    val sampleRate = 100.0
    val cutoff = 10.0
    val order = 2
    val filteredSignal = Signal.filtfilt(signal, sampleRate, cutoff, order)

    Text("\nButterworthFilter Demo:", style = MaterialTheme.typography.titleMedium)
    Text("Original: ${signal.take(5).map { "%.2f".format(it) }}...")
    Text("Filtered: ${filteredSignal.take(5).map { "%.2f".format(it) }}...")
}

@Composable
fun ChebyshevFilterDemo() {
    val signal = DoubleArray(10) { i -> Math.sin(2 * Math.PI * 0.1 * i) }
    val sr = 100.0
    val cut = 10.0
    val ord = 4
    val rip = 1.0
    val att = 20.0
    
    val cheby1 = Signal.cheby1_filtfilt(signal, sr, cut, ord, rip)
    val cheby2 = Signal.cheby2_filtfilt(signal, sr, cut, ord, att)

    Text("\nChebyshev Filter Demo:", style = MaterialTheme.typography.titleMedium)
    Text("Cheby I (rip=${rip}dB): ${cheby1.take(3).map { "%.2f".format(it) }}...")
    Text("Cheby II (att=${att}dB): ${cheby2.take(3).map { "%.2f".format(it) }}...")
}

@Composable
fun RK4SolverDemo() {
    val solver = RK4Solver()
    val equation = RK4Solver.DifferentialEquation { t, y -> -2.0 * t * y }
    val solution = solver.solve(equation, 1.0, 0.0, 1.0, 0.1)

    Text("\nRK4Solver Demo:", style = MaterialTheme.typography.titleMedium)
    Text("Time: ${solution.t.take(3).map { "%.2f".format(it) }}...")
    Text("Solution: ${solution.y.take(3).map { "%.2f".format(it) }}...")
}

@Composable
fun ResampleDemo() {
    val signal = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
    val num = 10
    val resampled = Signal.resample(signal, num)
    
    Text("\nResample Demo:", style = MaterialTheme.typography.titleMedium)
    Text("Original (len=${signal.size}): ${signal.contentToString()}")
    Text("Resampled (len=${resampled.size}): ${resampled.take(5).map { "%.2f".format(it) }}...")
}

@Composable
fun FFTDemo() {
    val signal = doubleArrayOf(1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0)
    val fft = FFT()
    val spectrum = fft.fft(signal)
    
    Text("\nFFT Demo:", style = MaterialTheme.typography.titleMedium)
    Text("Signal len: ${signal.size}")
    Text("Spectrum[0]: ${"%.2f".format(spectrum[0].real)} + j${"%.2f".format(spectrum[0].imag)}")
}

@Composable
fun InterpolationDemo() {
    val x = doubleArrayOf(0.0, 1.0, 2.0)
    val y = doubleArrayOf(0.0, 10.0, 0.0)
    val newX = doubleArrayOf(0.5, 1.5)
    val interp = Signal.interp1d_linear(x, y, newX)
    
    Text("\nInterpolation Demo:", style = MaterialTheme.typography.titleMedium)
    Text("Linear interp at 0.5, 1.5: ${interp.map { "%.2f".format(it) }}")
}

@Composable
fun SavitzkyGolayDemo() {
    val signal = doubleArrayOf(0.0, 1.0, 2.0, 3.0, 2.0, 1.0, 0.0)
    val smoothed = Signal.savgol_filter(signal, 5, 2)
    
    Text("\nSavitzky-Golay Demo:", style = MaterialTheme.typography.titleMedium)
    Text("Smoothed: ${smoothed.take(5).map { "%.2f".format(it) }}...")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SampleJscipyAppTheme {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.padding(16.dp).verticalScroll(scrollState)) {
            Text("jSciPy Library Demo")
            FindPeaksDemo()
            ButterworthFilterDemo()
            ChebyshevFilterDemo()
            RK4SolverDemo()
            ResampleDemo()
            FFTDemo()
            InterpolationDemo()
            SavitzkyGolayDemo()
        }
    }
}

# jscipy-app

This is an Android application that serves as a demonstration for the `jSciPy` library, specifically showcasing its signal processing capabilities. The project is hosted at `https://github.com/hissain/jscipy-android.git`.

## jSciPy Library Overview

The `jSciPy` library provides scientific computing functionalities, similar to Python's SciPy, but implemented for Java/Kotlin environments. This sample application demonstrates the following modules from `jSciPy.signal`:

- **PeakFinder**: Used for detecting peaks in a given signal.
- **ButterworthFilter**: Implements a Butterworth digital filter for signal filtering.
- **RK4Solver**: Provides an implementation of the Runge-Kutta 4th order method for solving ordinary differential equations.

## Project Structure

This project is a standard Android application built with Gradle and uses Jetpack Compose for its UI. Key directories include:
- `app/src/main/java/com/hissain/samplejscipyapp/`: Contains the main Kotlin source code for the application, including `MainActivity.kt` which demonstrates the `jSciPy` functionalities.
- `app/src/main/java/com/hissain/samplejscipyapp/ui/theme/`: Defines the UI theme for the application.
- `app/src/main/res/`: Contains Android resources like layouts, drawables, and values.

## Getting Started

To build and run this project, you will need Android Studio and the Android SDK.

1. Clone the repository:
   ```bash
   git clone https://github.com/hissain/jscipy-android.git
   ```
2. Open the project in Android Studio.
3. Build and run the application on an emulator or a physical device. The app will display a simple UI demonstrating the `PeakFinder`, `ButterworthFilter`, and `RK4Solver` in action.

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.

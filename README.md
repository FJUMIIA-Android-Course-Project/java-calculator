# Java Calculator

This is a simple calculator application written in Java, featuring a graphical user interface (GUI) that allows users to perform various mathematical operations.

## Prerequisites

- Java Development Kit (JDK) 21 or later
- Terminal or Command Prompt

## Project Structure

```
JavaCalculator/
├── manifest/
│   └── MANIFEST.MF
├── out/
│   ├── ButtonFactory.class
│   ├── Calculator.class
│   ├── CalculatorEngine.class
│   ├── CalculatorGUI.class
│   └── MemoryHandler.class
├── src/
│   ├── ButtonFactory.java
│   ├── CalculatorEngine.java
│   ├── CalculatorGUI.java
│   ├── Calculator.java
│   └── MemoryHandler.java
└── target/
    ├── classes/
    └── generated-sources/
        └── annotations/
```

## Building the Project

Follow the steps below to build the project from source and create an executable JAR file.

### Step 1: Compile the Java Files

Navigate to the root directory of the project and compile all the Java files in the `src` directory. The compiled `.class` files will be placed in the `out` directory.

```sh
javac --release 21 -d out src/*.java
```

- `--release 21`: Ensures compatibility with JDK 21.
- `-d out`: Specifies the output directory for the compiled files.
- `src/*.java`: Compiles all Java source files in the `src` directory.

### Step 2: Create a Manifest File

Create a `MANIFEST.MF` file in the `manifest` directory to specify the main class for the JAR.

For **Linux/MacOS**:

```sh
mkdir manifest
echo "Main-Class: Calculator" > manifest/MANIFEST.MF
```

For **Windows**:

```shell
mkdir manifest
echo Main-Class: Calculator > manifest\MANIFEST.MF
```

### Step 3: Package the JAR File

Create the executable JAR file using the `jar` command. This command will bundle all `.class` files along with the manifest.

```sh
jar cfm Calculator.jar manifest/MANIFEST.MF -C out .
```

- `c`: Create a new JAR file.
- `f`: Specify the JAR file name.
- `m`: Include the manifest file.
- `-C out .`: Add all files from the `out` directory.

### Step 4: Run the JAR File

Run the generated JAR file to launch the calculator application.

```sh
java -jar Calculator.jar
```

## Common Issues

- **No GUI Window Appears**: Ensure that the `Calculator` class properly initializes the GUI by using `SwingUtilities.invokeLater()`.
- **`MANIFEST.MF` File Issues**: Make sure there are no extra spaces or new lines in the `MANIFEST.MF` file, as incorrect formatting can cause the JAR to fail.

## Example Usage

After running the JAR file, you should see a window titled "Flood Calculator" where you can perform basic arithmetic operations, trigonometric functions, and more.

## License

This project is open-source and available under the MIT License.

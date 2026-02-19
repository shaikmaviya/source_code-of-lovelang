# Installation Guide

## Prerequisites
- Java Development Kit (JDK) 11 or higher installed
- Git (for cloning the repository)

## Step 1: Get the Code

### Option A: Clone from GitHub
```bash
git clone https://github.com/yourusername/Love-Language.git
cd Love-Language
```

### Option B: Download as ZIP
1. Go to the GitHub repository
2. Click the green "Code" button
3. Select "Download ZIP"
4. Extract the ZIP file
5. Open the folder in your terminal

## Step 2: Verify Java is Installed

```bash
java -version
javac -version
```

Both should show version 11 or higher.

## Step 3: Compile the Interpreter

Navigate to the Love Language directory and run:

```bash
javac Main.java
```

This creates `Main.class` (the compiled interpreter).

## Step 4: Run Your First Program

```bash
java Main examples.love
```

You should see output from the example program.

## Step 5: Create Your Own Programs

Create a new file with `.love` extension:

```bash
# Create a file named hello.love with your favorite editor
heart greeting = "Hello Love Language!"
whisper(greeting)
goodbye
```

Then run it:

```bash
java Main hello.love
```

## Troubleshooting

### "javac: command not found"
- Java is not installed or not in your PATH
- Install JDK from [oracle.com](https://www.oracle.com/java/technologies/downloads/)
- Add Java to your system PATH

### "Error: File must have .love extension"
- Make sure your file ends with `.love`
- Example: `myprogram.love` (not `myprogram.txt`)

### "Error: No such file or directory"
- Make sure the `.love` file is in the same directory as `Main.java`
- Use full path: `java Main C:/path/to/file.love`

## Next Steps

- Check [README.md](README.md) for language features
- Look at [examples.love](examples.love) and [functions_example.love](functions_example.love)
- Create your own Love programs!

Happy coding! 💕

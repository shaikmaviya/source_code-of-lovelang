# 💕 Love Language - A Romantic Programming Language

A cute and poetic programming language designed with love, where code reads like love letters!

## 🌹 Features

- **Variables**: `heart name = "Romeo"` - Declare variables with heart
- **Printing**: `whisper("Hello!")` - Output with whisper
- **Conditionals**: `iflove(condition) { }` - If statements
- **Else-If**: `elseheart(condition) { }` - Else-if statements  
- **Else**: `elselove { }` - Else statements
- **Loops**: `forever(i in 1..10) { }` - Loop through ranges
- **Functions**: `promise functionName(param) { }` - Define functions
- **String Concat**: Use `+` to join strings
- **Comments**: `secret This is a comment`
- **String Functions**: `hug(str1, str2)` - Concatenate strings
- **Exit**: `goodbye` - End program

## 📖 Quick Start

### Installation

1. Clone this repository:
```bash
git clone https://github.com/yourusername/Love-Language.git
cd Love-Language
```

2. Compile the Java interpreter:
```bash
javac Main.java
```

3. Run a Love program:
```bash
java Main filename.love
```

## 💘 Examples

### Variables and Printing
```love
heart name = "Romeo"
heart age = 25

whisper(name + ", you are " + age + " years old!")
goodbye
```

### Conditionals
```love
heart age = 20

iflove(age >= 18) {
  whisper("You are an adult!")
}
elseheart(age > 13) {
  whisper("You are a teenager!")
}
elselove{
  whisper("You are young!")
}

goodbye
```

### Loops
```love
whisper("Counting to 5:")
forever(i in 1..5) {
  whisper(i)
}

goodbye
```

### Functions with `promise`
```love
secret Define a greeting function
promise greet(name) {
  whisper("Hello, " + name + "!")
}

secret Call the function
greet("Juliet")
greet("Romeo")

goodbye
```

### String Functions
```love
heart first = "Romeo"
heart second = "Juliet"

whisper(hug(first, second))

goodbye
```

## 📝 Language Syntax Reference

| Keyword | Purpose | Example |
|---------|---------|---------|
| `heart` | Declare variable | `heart x = 5` |
| `whisper` | Print output | `whisper("hello")` |
| `promise` | Define function | `promise func(param) { }` |
| `iflove` | If condition | `iflove(x > 5) { }` |
| `elseheart` | Else-if condition | `elseheart(x == 5) { }` |
| `elselove` | Else block | `elselove { }` |
| `forever` | Loop | `forever(i in 1..10) { }` |
| `hug` | String concatenation | `hug("a", "b")` |
| `secret` | Comments | `secret This is ignored` |
| `goodbye` | Exit program | `goodbye` |

## 🎯 Comparison Operators

- `==` - Equal
- `!=` - Not equal
- `>` - Greater than
- `<` - Less than
- `>=` - Greater than or equal
- `<=` - Less than or equal

## 📂 Example Files

- `examples.love` - Basic examples with if-else statements
- `functions_example.love` - Examples using promise (functions)
- `momo.love` - Simple demo program

## 🔧 How It Works

The Love Language interpreter is written in Java. It:

1. Reads `.love` source files
2. Parses each line as a Love language command
3. Maintains a variable map for state
4. Stores user-defined functions
5. Executes control flow (if/else/loops)
6. Handles function calls and scoping

## 🚀 Running Programs

```bash
# Compile
javac Main.java

# Run a program
java Main yourprogram.love

# Example
java Main examples.love
```

## 💬 Creating Your First Program

Create a file named `hello.love`:

```love
heart name = "World"
whisper("Hello, " + name + "!")
goodbye
```

Run it:
```bash
java Main hello.love
```

## 🤝 Contributing

Feel free to fork this project and add new features! Some ideas:

- More built-in functions
- Arrays/Lists support
- File I/O operations
- Mathematical operations
- Better error messages

## 📄 License

MIT License - Feel free to use this language for any purpose!

## 👨‍💻 Author

Created with ❤️ as a romantic twist on programming languages.

---

**Happy Coding with Love!** 💕
"# source_code-of-lovelang" 

# FOOL Compiler

The FOOL Compiler is a lightweight, efficient compiler designed for the FOOL programming language. FOOL, which stands for "Functional Object-Oriented Language," merges functional programming paradigms with object-oriented principles, offering developers a flexible and expressive toolset for software development.

## Table of Contents

- [About](#about)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Usage](#usage)
- [Project Structure](#project-structure)
- [License](#license)

## About

This project was developed as part of the "Languages, compilers and computational models" course at the University of Bologna. It aims to provide a practical implementation of a compiler for a custom language that combines functional and object-oriented programming concepts.

## Features

- **Functional Programming Support** -> first-class functions, higher-order functions, and closures
- **Object-Oriented Programming Support** -> classes, inheritance and polymorphism
- **Static Typing** -> strong type system with type inference
- **Custom Virtual Machine** -> code generation for a stack-based virtual machine
- **ANTLR-Based Parsing** -> utilizes ANTLR for lexical and syntactic analysis
- **Symbol Table Management** -> efficient handling of scopes and variable bindings.

## Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 8 or higher.
- **ANTLR 4**: For generating the parser and lexer.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/FabioNotaro2001/FOOL-Compiler.git
   cd FOOL-Compiler

Compile the ANTLR grammar files:
antlr4 FOOL.g4

Compile the Java source files:
javac *.java

Usage

To compile a FOOL source file:
java FOOLCompiler <source_file>.fool

This will generate the corresponding assembly code for the custom virtual machine.

To execute the generated assembly code:
java ExecuteVM <assembly_file>.asm

Project Structure

    src/: Contains the source code for the compiler and virtual machine.

        FOOLCompiler.java: Main compiler class.

        ExecuteVM.java: Virtual machine execution engine.

        FOOL.g4: ANTLR grammar file for the FOOL language.

    tests/: Directory for test cases and example programs.

    README.md: This documentation file.

License

This project is licensed under the MIT License - see the LICENSE file for details.

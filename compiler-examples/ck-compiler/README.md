## CK Compiler
This is a project I created for learning internals of compilers. The `SimplerLang` grammar is taken from the
post ["Build your own programming language with ANTLR"](https://shalithasuranga.medium.com/build-your-own-programming-language-with-antlr-5201955537a5) 

### To compile `.ck` to `.class`
1. Open `src/test/java/com/arjunsk/compiler/ck/CkCompilerTest.java`
2. Run `compile()`
3. Look for the `output/CgSample.class` generated under root.
4. To see the source code of `CgSample.class`, use IntelliJ decompiler.

### Grammar
```antlrv4
grammar simplerlang;

program : statement+;
statement : let | show ;

let : VAR '=' INT ;
show : 'show' (INT | VAR) ;

VAR : [a-z]+ ;
INT : [0–9]+ ;
WS : [ \n\t]+ -> skip;
```

### Features
1. Used a simple grammar to focus more on `compiler phases` rather than `language support`.
2. Took `ANTLR` generated code as a reference.
2. Implemented `Parse Tree`.
3. Implemented `Visitor` & `Vistable`.
4. Implemented `CodeGeneration` using `Java ASM`.
5. Implemented `Interpreter`.

### TODO
* Implement Semantic Analyser.
* ~~Implement custom filename.(Unsupported)~~
* ~~Implement Parse Tree.~~
* ~~Implement Visitor Pattern.~~
* ~~Implement byte code generation~~

### Phases of Compiler
1. Lexical Analysis [Done]
2. Parsing [Done]
3. Semantic Analyser [TODO]
4. Optimization [Optional]
5. Code Generation [Done]

### Reference:
1. [Java ASM](https://github.com/arjunsk/java-bytecode/tree/master/java-asm/ow2-asm-example/src/main/java/com/arjunsk/asm/asmifier)
2. [Ops Code](https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html)

> Do check out other modules in this project for better understanding.️
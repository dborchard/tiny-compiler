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
2. Implemented [Parse Tree](src/main/java/com/arjunsk/compiler/ck/domain/tree/ParseTree.java).
3. Implemented [Visitor](src/main/java/com/arjunsk/compiler/ck/visitor/Visitor.java) & [Visitable](src/main/java/com/arjunsk/compiler/ck/domain/tree/Visitable.java).
4. Implemented [CodeGeneration](src/main/java/com/arjunsk/compiler/ck/visitor/codegenerator/CodeGeneratorVisitor.java) using `Java ASM`.
5. Implemented [Interpreter](src/main/java/com/arjunsk/compiler/ck/visitor/interpreter/InterpreterVisitor.java) & [Semantic Analyzer](src/main/java/com/arjunsk/compiler/ck/visitor/semantic/SemanticAnalyzer.java).

### TODO
* ~~Implement Semantic Analyser.~~
* ~~Implement custom filename.(Unsupported)~~
* ~~Implement Parse Tree.~~
* ~~Implement Visitor Pattern.~~
* ~~Implement byte code generation~~

### Compiler Phases
1. Lexical Analysis [Done]
2. Syntactic Analysis (ie Parsing) [Done]
3. Semantic analysis [Done] & Intermediate Code Generation [NA]
4. Optimization (optional)
5. Code Generation [Done]

Intermediate Code Generation: code gets converted to independent intermediate code. We are not doing this in `ck-compiler`. 
We could use `LLVM` as Backend for implementing this feature. 

### Reference:
1. [Java ASM](https://github.com/arjunsk/java-bytecode/tree/master/java-asm/ow2-asm-example/src/main/java/com/arjunsk/asm/asmifier)
2. [Ops Code](https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html)
3. [Lecture Note](https://www.radford.edu/~nokie/classes/380/phases.html)
4. [LLVM Backend](https://llvm.org/docs/WritingAnLLVMBackend.html)

> Do check out other modules in this project for better understanding.️
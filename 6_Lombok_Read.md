<h1>Lombok</h1>

1. It aims to reduce the boilerplate code that developers have to write, such as 
   1. getters
   2. setters
   3. constructors,
   4. No Argument constructors etc.
   5. Equals, toString() etc.
2. Lombok achieves this by generating this code automatically during compilation, based on annotations we add to our Java classes.
3. Annotations:
   1. @Getters
   2. @Setter
   3. @ToString
   4. @EqualsAndHashCode
   5. @NoArgsConstructor
   6. @AllArgsConstructor
   7. @Builder
   8. @Slf4j
   9. @Data - contains - @Getter + @Setter + @ToString + @EqualAndHashCode + @Value + @RequiredArgsConstructor
4. How does it works - 
   1. Lombok generates bytecode for methods like getters, setters, equals, hashcode as specified by the annotations used in your code. 
      This generated code is added to the compiled class files(.class files)
   2. Java compiler compiles your classes, including the generated code. This means that the generated methods become part of your compiles class files.
      When you run application, the generated methods are available for use, just like any other methods in you classes.
5. Using ```@Builder``` overrides the default constructor of the class, hence there might be possibility that we might need to add ```@NoArgsConstructor``` and ```@AllArgsConstructor```
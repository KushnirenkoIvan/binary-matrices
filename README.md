# Binary matrices multiplication example

Provides example of multiplication of randomly generated binary matrices
with predefined size sequentially and in parallel, prints logs to console.

### Requirements: 
- Java 8

### Structure: 
- `ua.ikushnirenko.binary_matrices.model.BinaryMatrix` - represents a binary matrix of any size.
- `ua.ikushnirenko.binary_matrices.generator.MatrixGenerator` - generates a binary matrix with specified rows and cols.  
- `ua.ikushnirenko.binary_matrices.service.MatrixService` - interface for operating with matrices. 
   Has base abstract implementation: `ua.ikushnirenko.binary_matrices.service.AbstractMatrixService`, 
   two implementations: `ua.ikushnirenko.binary_matrices.service.ParallelMatrixServiceImpl` and 
   `ua.ikushnirenko.binary_matrices.service.SequentialMatrixServiceImpl`.    
- `ua.ikushnirenko.binary_matrices.Main` - entry point.

### How to build: 
Run tests:
> mvn clean test

Build package:
> mvn clean package

### Parameters
Jar accepts one CLI argument - size of square matrix. _Default value: 2000_.
Also, this number is be used for creating ThreadPoolExecutor as corePoolSize.

### How to run
> cd target
> java -jar binary-matrices-0.0.1-0.jar {square_matrix_size}
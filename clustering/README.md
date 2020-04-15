
# JGraphT - Clustering

This sample shows a project which 

 * creates a small undirected graph,
 * computes clusters of vertices using different algorithms.

The project can be executed like: 

```
mvn package
java -jar target/jgrapht-clustering-example-1.4.0.jar
```

## Going Native 

With the help of [GraalVM](https://www.graalvm.org/) we can also 
compile down to a native executable which will speed up things. Assuming you have a proper installation 
of GraalVM with Java 11 support and the [native-image](https://www.graalvm.org/docs/reference-manual/native-image) tool, you can issue the following command.

```
mvn package
native-image -jar target/jgrapht-clustering-example-1.4.0.jar
```

An executable called `clustering` will be created. The configuration for the native-tool is automatically 
picked up from `src/main/resources/META-INF/native-image/example.jgrapht.clustering/App/native-image.properties`. Execute the program using 

```
./clustering
```


Enjoy!

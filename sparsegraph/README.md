
# JGraphT - Sparse Graph Example

This sample shows a project which 
 
 * reads a graph from a JSON file into an edge list, 
 * builds a sparse graph from the edge list, and 
 * computes the PageRank of all vertices.

Note that sparse representations have the benefits of being very fast since they are cache-friendly and 
have a small memory footprint. On the other hand they have the drawback of being static (cannot be 
changed after they are created) and that vertices and edges have to be continuous integers. 
Thus, this representation is suited for users which have very large static graphs and they want to 
execute some complicated algorithm on them.

The project can be executed like: 

```
mvn package
java -jar target/jgrapht-sparsegraph-example-1.4.0-SNAPSHOT.jar input.json pagerank.csv
```

which will read the provided graph from file `input.json` and output a CSV file with `vertexid,pagerank`
values. The input graph is 

```
{
"meta": "Graph from https://en.wikipedia.org/wiki/PageRank",

"nodes": [
    { "id": "0" },
    { "id": "1" },
    { "id": "2" },
    { "id": "3" },
    { "id": "4" },
    { "id": "5" },
    { "id": "6" },
    { "id": "7" },
    { "id": "8" },
    { "id": "9" },
    {" id": "10" }
],
"edges": [
    { "source": "1", "target": "2" },
    { "source": "2", "target": "1" },
    { "source": "3", "target": "0" },
    { "source": "3", "target": "1" },
    { "source": "4", "target": "1" },
    { "source": "4", "target": "3" },
    { "source": "4", "target": "5" },
    { "source": "5", "target": "1" },
    { "source": "5", "target": "4" },
    { "source": "6", "target": "1" },
    { "source": "6", "target": "4" },
    { "source": "7", "target": "1" },
    { "source": "7", "target": "4" },
    { "source": "8", "target": "1" },
    { "source": "8", "target": "4" },
    { "source": "9", "target": "4" },
    { "source": "10", "target": "4" }
]
}

```

which is the one provided in the [PageRank](https://en.wikipedia.org/wiki/PageRank) wikipedia page. The output should be 

```
0,0.032782
1,0.380316
2,0.346993
3,0.039088
4,0.080886
5,0.039088
6,0.016170
7,0.016170
8,0.016170
9,0.016170
10,0.016170
```

# Going Native 

This example is suitable of executing on very large graphs. With the help of GraalVM we can also 
compile down to a native executable which will speed up things. Assuming you have a proper installation 
of GraalVM with Java 11 support and the native-image tool, you can issue the following command.

```
mvn package
native-image -jar target/jgrapht-sparsegraph-example-1.4.0-SNAPSHOT.jar
```

An executable called `sparsegraph` will be created. The configuration for the native-tool is automatically 
picked up from `src/main/resources/META-INF/native-image/example.jgrapht.sparsegraph/App/native-image.properties`. Execute the program using 

```
./sparsegraph input.json pagerank.csv
```


Enjoy!

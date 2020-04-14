
# JGraphT - Sparse Graph Example

This sample shows a project which 
 
 * reads a graph from a JSON file into an edge list, 
 * builds a sparse graph from the edge list, and 
 * computes the PageRank of all vertices.

Note that sparse representations have the benefits of being very fast since they are cache-friendly and 
having small memory footprints. On the other hand they have the drawback of being static (cannot be 
changed after they are created) and that vertices and edges have to be continuous integers. 

Thus, this representation is suited for users which have very large static graphs and they want to 
execute some complicated algorithm on them.

Enjoy!

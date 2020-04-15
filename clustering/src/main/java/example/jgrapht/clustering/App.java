package example.jgrapht.clustering;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.alg.clustering.KSpanningTreeClustering;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm.Clustering;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.util.SupplierUtil;

public class App {

	public static void main(String[] args) throws IOException {

		Graph<String, DefaultWeightedEdge> g = GraphTypeBuilder.undirected().weighted(true).allowingMultipleEdges(false)
				.allowingSelfLoops(false).vertexSupplier(SupplierUtil.createStringSupplier())
				.edgeSupplier(SupplierUtil.createDefaultWeightedEdgeSupplier()).buildGraph();

		System.out.println("Building graph");
		
		g.addVertex("v1");
		g.addVertex("v2");
		g.addVertex("v3");
		g.addVertex("v4");

		// 1st community
		g.addEdge("v1", "v2");
		g.addEdge("v2", "v3");
		g.addEdge("v3", "v4");
		g.addEdge("v4", "v1");

		g.addVertex("v5");
		g.addVertex("v6");
		g.addVertex("v7");
		g.addVertex("v8");

		// 2nd community
		g.addEdge("v5", "v6");
		g.addEdge("v6", "v7");
		g.addEdge("v7", "v8");
		g.addEdge("v8", "v5");

		// bridge them with higher weight edge
		DefaultWeightedEdge bridge = g.addEdge("v4", "v5");
		g.setEdgeWeight(bridge, 5d);

		System.out.println("Running k-spanning-tree clustering (with k = 2)");
		final int k = 2;
		Clustering<String> clusters1 = new KSpanningTreeClustering<>(g, k).getClustering();
		System.out.println("Found " + clusters1.getNumberClusters() + " clusters");
		for (Set<String> c : clusters1.getClusters()) {
			System.out.println("Cluster: " + c.stream().collect(Collectors.joining(",")));
		}

	}

}

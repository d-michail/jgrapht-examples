package example.jgrapht.sparsegraph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.alg.scoring.PageRank;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.nio.json.JSONEventDrivenImporter;
import org.jgrapht.opt.graph.sparse.SparseIntDirectedGraph;

public class App {

	public static void main(String[] args) throws IOException {

		if (args.length < 2) {
			System.out.println("Proper usage is: program inputfile outputfile");
			System.exit(0);
		}

		String input = args[0];
		String output = args[1];

		JSONEventDrivenImporter importer = new JSONEventDrivenImporter();

		List<Pair<Integer, Integer>> edges = new ArrayList<>();
		int[] maxVertex = new int[1];

		importer.addEdgeConsumer(t -> {
			int source = Integer.parseInt(t.getFirst());
			int target = Integer.parseInt(t.getSecond());
			maxVertex[0] = Math.max(maxVertex[0], Math.max(source, target));
			edges.add(Pair.of(source, target));
		});

		importer.importInput(new File(input));

		int numVertices = maxVertex[0] + 1;

		Graph<Integer, Integer> graph = new SparseIntDirectedGraph(numVertices, edges);
		edges.clear();

		final double dampingFactor = 0.85;
		final int maxIterations = 20;

		PageRank<Integer, Integer> alg = new PageRank<>(graph, dampingFactor, maxIterations);
		Map<Integer, Double> pageRank = alg.getScores();

		try (FileWriter writer = new FileWriter(output, false)) {
			for (int i = 0; i < numVertices; i++) {
				writer.append(String.format("%d,%f\n", i, pageRank.get(i)));
			}
		}

	}

}

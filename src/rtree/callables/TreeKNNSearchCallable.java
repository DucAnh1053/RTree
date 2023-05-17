package rtree.callables;

import java.util.List;
import java.util.concurrent.Callable;

import rtree.RTreeMulti;
import shape.Boundable;
import utils.Pair;
import utils.Record;

public class TreeKNNSearchCallable<T extends Boundable> extends TreeBaseCallable<T>
		implements Callable<List<List<Pair<Record<T>, Float>>>> {
	private List<Record<T>> records;
	private int k;

	public TreeKNNSearchCallable(RTreeMulti<T> tree, int id, List<Record<T>> records, int k) {
		super(tree, id);
		this.records = records;
		this.k = k;
	}

	public List<List<Pair<Record<T>, Float>>> call() throws Exception {
		return tree.nearestNeighborsSearch(records, k);
	}
}

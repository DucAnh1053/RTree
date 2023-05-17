package rtree.callables;

import java.util.List;
import java.util.concurrent.Callable;

import rtree.RTreeMulti;
import shape.Boundable;
import utils.Record;

public class TreeDeleteCallable<T extends Boundable> extends TreeBaseCallable<T> implements Callable<List<Boolean>> {
	private List<Record<T>> records;

	public TreeDeleteCallable(RTreeMulti<T> tree, int id, List<Record<T>> records) {
		super(tree, id);
		this.records = records;
	}

	public List<Boolean> call() throws Exception {
		return tree.delete(records);
	}
}

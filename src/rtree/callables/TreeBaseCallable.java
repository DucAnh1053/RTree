package rtree.callables;

import rtree.RTreeMulti;
import shape.Boundable;

public abstract class TreeBaseCallable<T extends Boundable> {
	protected RTreeMulti<T> tree;
	protected int id;

	protected TreeBaseCallable(RTreeMulti<T> tree, int id) {
		this.tree = tree;
		this.id = id;
	}
}

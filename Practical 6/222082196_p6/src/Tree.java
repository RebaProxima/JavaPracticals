//************ Total marks for Tree: 25 marks ************************************************
import java.util.Iterator;

/**
 * A General Tree. There should be a reference to the root of the tree
 * and then all of the nodes contained in the tree are children of the root.
 *
 * @param <T> The type of the objects that will be contained in the tree
 */
public class Tree<T>{
	private TreeNode<T> root = null;
	private Integer size = 0;
	
	/**
	 * Create a new tree; 
	 */
	public Tree() {
		size = 0;
		root = new TreeNode<T>(null, null);
	}
	
	public Tree(T element) {
		super();
		root = new TreeNode<T>(null, element);
	}
	
	/**
	 * Get the root of the tree
	 * @return return the root of the tree
	 */
	public Position<T> root() {
		return root;
	}
	

	/**
	 * Get an iterator for the children of a node
	 * @param node a Position<T> which is a node in this tree;
	 * @return an Iterator over the children of a node
	 * @throws Exception 
	 */
	public Iterator<Position<T>> children(Position<T> node) throws Exception {
		TreeNode<T> treeNode = checkPosition(node);
		return treeNode.getChildren();
	}

	
	/**
	 * Get the parent of a node
	 * @param node a Position<T> which is a node in this tree
	 * @return a Position<T> which is the parent of this node
	 * @throws Exception 
	 */
	public Position<T> parent(Position<T> node) throws Exception {
		TreeNode<T> treeNode = checkPosition(node);
		return treeNode.getParent();
	}
	
	/**
	 * Add an element as a child of a node in the tree
	 * @param node the position in the tree
	 * @param element the element that must be added to the tree
	 * @return The Position<T> that is contained in the tree
	 * @throws Exception
	 * 5 marks *******************************************************************
	 */
	public Position<T> addElementAsChild(Position<T> node, T element) throws Exception {		
		//TODO: Complete
		//Check the position of the parent
		TreeNode<T> parentNode = checkPosition(node);
		TreeNode<T> childNode = parentNode.addChild(element);
		//Increment the list size
		size++;
		
		return childNode;

	}
	
	/**
	 * Set the element for a given TreeNode.
	 * @param node the node to change the element of
	 * @param element the element to set in the node
	 * @throws Exception 
	 */
	public void setElement(Position<T> pos, T element) throws Exception {
		TreeNode<T> node = checkPosition(pos);
		node.setElement(element);
	}
	
	/**
	 * The number of nodes in this tree
	 * @return the number of nodes in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Return true if the tree is empty
	 * @return true if the tree is empty, false if the tree is not empty;
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Convert a Position<T> into a TreeNode<T>
	 * @param p a Position<T> to convert
	 * @return the corresponding TreeNode<T>
	 * @throws Exception 
	 */
	private TreeNode<T> checkPosition(Position<T> p) throws Exception {
		if (!(p instanceof TreeNode<?>)) {
			throw new Exception("Invalid Position");
		}
		
		return (TreeNode<T>)p;
	}


	/**
	 * Perform a preorder traversal over all of the elements in the tree.
	 * @param root The root of the subtree
	 * @return the resulting indented marks
	 * @throws Exception 
	 * 10 marks *******************************************************************
	 */
	public String preOrderElementTraversal(Tree<T> tree, Position<T> root) throws Exception {		
		//TODO: Complete
		StringBuilder tranverseThroughElements = new StringBuilder();
		
		
		int nodeDepth = depth(tree,root);
		for(int i = 0 ; i < nodeDepth ; i++) {
			tranverseThroughElements.append("\t");
		}
		
		tranverseThroughElements.append(root.element());
		
		
		Iterator<Position<T>> childrenIterator = children(root);
		while(childrenIterator.hasNext()) {
			
			tranverseThroughElements.append("\n");
			tranverseThroughElements.append(preOrderElementTraversal(tree, childrenIterator.next()));
		}
		
		return tranverseThroughElements.toString();
	}
	
	/**
	 * Calculate depth of given node.
	 * @param tree
	 * @param v - a position in the tree
	 * @return the depth of v in the tree
	 * 5 marks *******************************************************************
	 */
	public Integer depth(Tree<T> tree, Position<T> v) {
		
		int depth = 0;
		
		try {
			//We go bottom to the top in the tree and check if the node has a parent
			while(tree.parent(v) != null) {
				//we access the parent
				v = tree.parent(v);
				//Increase the depth
				depth ++;
			}
		}catch (Exception e) {
			
		}
		
		return depth;
	}
	

	/**
	 * Determine if a given node in an internal node
	 * @param tree
	 * @param p
	 * @return boolean - true if node is internal
	 * @throws Exception
	 * 5 marks *******************************************************************
	 */
	public Boolean isInternal(Tree<T> tree, Position<T> p) throws Exception {
		//TODO: Complete
		Iterator<Position<T>> iterator = tree.children(p);
		return iterator.hasNext();
	}
}

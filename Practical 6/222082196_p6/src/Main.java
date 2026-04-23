//************ Total marks for Main: 10 marks ************************************
import java.util.Iterator;
import java.util.Random;

class Mark{
	String name;
	Integer value;
	
	public Mark(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		if (name!=null)
			return name + " " + value;
		else
			return String.valueOf(value);
	}	
}

public class Main {

	public static void main(String[] args) throws Exception {
		Random r = new Random(System.currentTimeMillis());
		
		Tree<Mark> tree = new Tree<Mark>(new Mark("CSC3A", 100));
		Position<Mark> root = tree.root();
		
		Position<Mark> st = tree.addElementAsChild(root, new Mark("ST", 50));
		Position<Mark> st1 = tree.addElementAsChild(st, new Mark(null, (r.nextInt(100))));
		Position<Mark> st2 = tree.addElementAsChild(st, new Mark(null, (r.nextInt(100))));
		
		Position<Mark> miniP = tree.addElementAsChild(root, new Mark("MP", 25));
		Position<Mark> mp = tree.addElementAsChild(miniP, new Mark(null, (r.nextInt(100))));
		
		Position<Mark> ct_pa = tree.addElementAsChild(root, new Mark("CT+PA", 25));
		
		Position<Mark> ct = tree.addElementAsChild(ct_pa, new Mark("CT", 50));		
		for(int i=0;i<3;i++){
			tree.addElementAsChild(ct, new Mark(null, (r.nextInt(100))));
		}
		
		Position<Mark> pa = tree.addElementAsChild(ct_pa, new Mark("PA", 50));		
		for(int i=0;i<8;i++){
			tree.addElementAsChild(pa, new Mark(null, (r.nextInt(100))));
		}
		
		System.out.println(tree.preOrderElementTraversal(tree, root));
		System.out.println("\nSemester Mark:" + calcSM(tree, tree.root()));
	}
	
	/**
	 * Calculate the semester mark using the weights and marks contained in the tree
	 * @param tree
	 * @return the semester mark
	 * 10 marks 
	 * @throws Exception *******************************************************************
	 */
	private static Double calcSM(Tree<Mark> tree, Position<Mark> root) throws Exception {
		//TODO: Complete
		Mark currentMark = root.element();
		
		Iterator<Position<Mark>> childrenIterator = tree.children(root);
		if(!childrenIterator.hasNext()) {
			return currentMark.value.doubleValue();
		}
		
		double totalMark = 0.0;
		while(childrenIterator.hasNext()) {
			Position<Mark> positionOfChild = childrenIterator.next();
			double childResult = calcSM(tree, positionOfChild);
			totalMark += (childResult * (positionOfChild.element().value)/100.0);
		}
		
		return totalMark;
		
	}

}

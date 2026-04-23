/**
 * 
 */

/**
 * 
 */
public class Main {

	/**
	 * @param args
	 * @throws MatrixException 
	 */
	public static void main(String[] args) throws MatrixException {
		// TODO Auto-generated method stub
		MatrixOperations<Integer> mat = new MatrixOperations<Integer>(2,2);
		
		mat.setElement(0, 0, 4);
		mat.setElement(0, 1, 2);
		mat.setElement(1, 0, 4);
		mat.setElement(1, 1, 3);
		
		System.out.println("Original Matrix");
		mat.printMatrix();
		
		
		System.out.println("Adding Matrix");
		mat.addMatrix(mat);

	}

}

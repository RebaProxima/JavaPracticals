/**
 * This is a class that will be responsible for matrix operations
 * 
 * @author 222082196
 * @param <E>
 */
public class MatrixOperations<T> implements IMatrix<T>{
	
	Matrix mat = new Matrix();
	
	/** Number of rows of the matrix */
	private int rows;
	/** Number of columns of the matrix */
	private int cols;
	/** Array of the matrix */
	private T[][] array;
	/** This is the current matrix */
	Matrix<Integer> matrix = new Matrix<Integer>(2,2);
	
	/**
	 * 
	 * 
	 * @param rows   Number of rows
	 * @param cols   Number of cols
	 */
	@SuppressWarnings("unchecked")
	public MatrixOperations (int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		array = (T[][]) new Object[rows][cols];
	}
	/**
	 * 
	 * @param rows
	 * @param cols
	 * @param value
	 */
	
	public void setElement(int rows, int cols, T value) {
		array[rows][cols] = value;
	}
	
	/**
	 * 
	 * @param rows
	 * @param cols
	 * @return an element
	 */
	
	public T getElement(int rows, int cols) {
		return array[rows][cols];
	}
	
	/**
	 * 
	 * @return array
	 */
	
	public Integer[][] getArray() {
		return (Integer[][]) array;
	}
	
	/**
	 *  Returns number of rows
	 */
	@Override
	public Integer numberRows() {
		// TODO Auto-generated method stub
		return rows;
	}

	@Override
	public Integer numberCols() {
		// TODO Auto-generated method stub
		return cols;
	}

	@Override
	public IMatrix<T> getRow(Integer i) throws MatrixException {
		// TODO Auto-generated method stub
		return (IMatrix<T>) array[i][1];
	}

	@Override
	public IMatrix<T> getCol(Integer j) throws MatrixException {
		// TODO Auto-generated method stub
		return (IMatrix<T>) array[1][j];
	}

	@Override
	public T getElement(Integer i, Integer j) throws MatrixException {
		// TODO Auto-generated method stub
		return array[i][j];
	}

	@Override
	public IMatrix<T> addMatrix(IMatrix<T> otherMatrix) throws MatrixException {
		

		MatrixOperations<T> result = new MatrixOperations<>(matrix.numberRows(), matrix.numberCols());
		
		for(int i = 0 ; i < otherMatrix.numberRows(); i++) {
			for (int j = 0; j < matrix.numberCols(); j++) {
				
			}
		}
		return result;
	}

	@Override
	public IMatrix<T> addScalar(Integer c) throws MatrixException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMatrix<Double> addScalar(Double c) throws MatrixException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMatrix<T> multiplyScalar(Integer c) throws MatrixException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMatrix<Double> multiplyScalar(Double c) throws MatrixException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMatrix<T> multiplyMatrix(IMatrix<T> otherMatrix) throws MatrixException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMatrix<T> transpose() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void printMatrix() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols ; j++) {
				System.out.print(array[i][j]);
			}
			System.out.println( );
		}
		System.out.println( );
	}
	
	
	
}
	


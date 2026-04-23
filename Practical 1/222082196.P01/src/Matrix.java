
public class Matrix<T> implements IMatrix<T>{
	
	private int rows;
	private int cols;
	/** Array of the matrix */
	private T[][] array;
	
	public Matrix(){
		this.rows = rows;
		this.cols = cols;
	}
	
	@SuppressWarnings("unchecked")
	public Matrix( int row, int col) {
		this.rows = rows;
		this.cols = cols;
		array = (T[][]) new Object[row][col];
	}
	
	public void setElement(int rows, int cols, T value) {
		array[rows][cols] = value;
	}
	
	public T getElement(int rows, int cols) {
		return array[rows][cols];
	}
	
	public Integer[][] getArray() {
		return (Integer[][]) array;
	}
	
	

	@Override
	public Integer numberRows() {
		// TODO Auto-generated method stub
		return rows;
	}

	@Override
	public Integer numberCols() {
		// TODO Auto-generated method stub
		return rows;
	}

	@Override
	public IMatrix<T> getRow(Integer i) throws MatrixException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMatrix<T> getCol(Integer j) throws MatrixException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getElement(Integer i, Integer j) throws MatrixException {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public IMatrix<T> addMatrix(IMatrix<T> otherMatrix) throws MatrixException {
		// TODO Auto-generated method stub
		return null;
	}


	

}

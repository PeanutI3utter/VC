public class vc_u5 {
	public static void main(String[] args) {
		Integer[][] matrix = {{20, 42, 24, 150, 72}, {12, 130, 5, 240, 23}, {80, 62, 100, 65, 145}, {98, 76, 188, 210, 200}};
		Integer[][] smoothed = averager(matrix);
		Integer[][] negated = negateImage(smoothed);
		System.out.println(matrixToString(expandMatrix(matrix)));
		System.out.println(matrixToString(smoothed));
		System.out.println(matrixToString(negated));
	}
	
  
  // expands matrix 
	public static Integer[][]expandMatrix(Integer[][] matrixIn) {
		Integer[][] expandedMatrix = new Integer[matrixIn.length + 2][matrixIn[0].length + 2];
		expandedMatrix[0][0] = matrixIn[0][0];
		expandedMatrix[0][expandedMatrix[0].length - 1] = matrixIn[0][matrixIn[0].length - 1];
		expandedMatrix[expandedMatrix.length - 1][0] = matrixIn[matrixIn.length - 1][0];
		expandedMatrix[expandedMatrix.length - 1][expandedMatrix[0].length - 1] = matrixIn[matrixIn.length - 1][matrixIn[0].length - 1];
		for(int i = 1; i < expandedMatrix.length - 1; i++) {
			expandedMatrix[i][0] = matrixIn[i - 1][0];
			expandedMatrix[i][expandedMatrix[0].length - 1] = matrixIn[i - 1][matrixIn[0].length - 1];
		}
		for (int i = 1; i < expandedMatrix[0].length - 1; i++) {
			expandedMatrix[0][i] = matrixIn[0][i - 1];
			expandedMatrix[expandedMatrix.length - 1][i] = matrixIn[matrixIn.length - 1][i - 1];
		}
		for (int i = 0; i < matrixIn.length; i++) {
			for (int j = 0; j < matrixIn[0].length; j++) {
				expandedMatrix[i + 1][j + 1] = matrixIn[i][j];
			}
		}
		return expandedMatrix;
	}
	
  // print out matrices
	public static <T> String matrixToString(T[][] matrix) {
		String out = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				out += matrix[i][j].toString() + " ";
			}
			out += "\n";
		}
		return out;
	}
	
  // filter averaging out pixel values
	public static Integer[][] averager(Integer[][] matrixIn) {
		Integer[][] extendedMatrixInteger = expandMatrix(matrixIn);
		Integer[][] resultIntegers = new Integer[matrixIn.length][matrixIn[0].length];
		for (int i = 0; i < resultIntegers.length; i++) {
			for (int j = 0; j < resultIntegers[0].length; j++) {
				resultIntegers[i][j] = pointAverage(i + 1, j + 1, extendedMatrixInteger);
			}
		}
		return resultIntegers;
	}
	
  
  // average at point i,j
	public static Integer pointAverage(int i, int j, Integer[][] matrix) {
		assert(i > 0 && i < matrix.length && j > 0 && j < matrix[0].length);
		int sum = 0;
		for (int k = -1; k < 2; k++) {
			for (int l = -1; l < 2; l++) {
				sum += matrix[i + k][j + l];
			}
		}
		return (Integer)(int)Math.round((double)sum / 9);
	}
	
  
  // negates image
	public static Integer[][] negateImage(Integer[][] image){
		Integer[][] result = new Integer[image.length][image[0].length];
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				result[i][j] = 255 - image[i][j];
			}
		}
		return result;
	}
}

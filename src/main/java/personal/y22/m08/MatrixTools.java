package personal.y22.m08;

import static java.lang.Math.*;

public class MatrixTools {
    // multiply each element of a vector by its corresponding element (at the same index) in another vector
    // take the sum of these products. this is the dot product. return it.
    public static int calculateDotProduct(int[] firstVector, int[] secondVector) {
        boolean lengthsMatch = (firstVector.length == secondVector.length);
        int dotProduct = 0;
        if (lengthsMatch) {
            for (int i = 0; i < firstVector.length; i++) {
                dotProduct += firstVector[i] * secondVector[i];
            }
        } else {
            System.out.println("Vectors of different lengths cannot have this operation applied to them");
        }
        return dotProduct;
    }

    // the magnitude of a vector is the square root of the dot product of that vector with itself
    // this is the distance, in n-dimensional space, between origin and the tip of the vector,
    // assuming the tail of the vector is at the origin
    public static double calculateMagnitude(int[] vector) {
        return sqrt(calculateDotProduct(vector, vector));
    }

    // if the dot-product of any two n-dimensional vectors is 0, then the two vectors
    // vector1 and vector2 lie at 90 degrees to each other in n-dimensional space
    public static boolean vectorsAreOrthogonal(int[] vector1, int[] vector2) {
        return calculateDotProduct(vector1, vector2) == 0;
    }

    // a unit vector is any vector whose magnitude is 1
    public static boolean isUnitVector(int[] vector) {
        return calculateMagnitude(vector) == 1;
    }

    // a vector exists in as many dimensions as it has components,
    // even if those components are zero
    public static int countVectorDimensions(int[] vector) {
        return vector.length;
    }

    // a zero vector in any number of dimensions is a vector
    // for which all of its components are zero
    public static boolean isZeroVector(int[] vector) {
        for (int j : vector) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    // vectors are linearly independent if they are not copies of one another, multiplied by some constant
    // {0, 3, 6, 9} and {0, 30, 60, 90} are NOT independent, since v1 is 1/10th of v2, or v2 is 10x v1
    // if all the corresponding components are  the same multiple of one another, two vectors are linearly dependent
    // but if even a single component is not a multiple of its corresponding components, the vectors
    // do not lie on a single line, and thus those vectors are NOT linearly dependent
    public static boolean vectorsAreLinearlyIndependent(int[] vector1, int[] vector2) {
        double ratio = (double) vector1[0] / vector2[0];
        for (int i : vector1) {
            if ((double) vector1[i] / vector2[i] == ratio) {
                return false;
            }
        }
        return true;
    }

    // vectors are linearly dependent if one of two conditions are met:
    // the two vectors are exactly identical
    // the two vectors are all identical multiples of one another-- one could be {1, 2, 3} and another could be {2, 4, 6}
    // since all the values of the components of the second vector are exactly twice the corresponding compoennts of the first,
    // the two vectors are in fact on the same line, and so they are linearly DEPENDENT, or, equivalently, NOT linearly independent
    public static boolean vectorsAreLinearlyDependent(int[] vector1, int[] vector2) {
        return !vectorsAreLinearlyIndependent(vector1, vector2);
    }

    public static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) {
        double[][] result = new double[matrix1.length][matrix2[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = multiplySingleCells(matrix1, matrix2, i, j);
            }
        }

        return result;
    }

    public static double multiplySingleCells(double[][] matrix1, double[][] matrix2, int rowIdx, int colIdx) {
        double currCell = 0;
        int i = 0;
        while (i < matrix2.length) {
            currCell += matrix1[rowIdx][i] * matrix2[i][colIdx];
            i++;
        }
        return currCell;

    }
}
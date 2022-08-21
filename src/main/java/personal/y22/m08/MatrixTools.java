package personal.y22.m08;

public class MatrixTools {
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
}
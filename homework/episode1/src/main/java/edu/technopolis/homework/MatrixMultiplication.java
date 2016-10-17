package edu.technopolis.homework;

/**
 * Matrix multiplication home task.
 * <br/>
 * Matrix dimension and elements are passed as CLI arguments.
 */
public class MatrixMultiplication {
    public static void main(String... args) {

        if (args.length < 6){
            System.out.println("Неверные данные");
            return;
        }

        //Reading dimensions
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        int X = Integer.parseInt(args[2]);
        int Y = Integer.parseInt(args[3]);
        int[][] firstMatrix = new int[N][M];
        int[][] secondMatrix = new int[X][Y];

        if (args.length != (N*M + X*Y + 4)){
            System.out.println("Неверное количество аргументов");
            return;
        }
        //Reading matrices
        int pointer = 4;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                firstMatrix[i][j]=Integer.parseInt(args[pointer]);
                pointer++;
            }
        }
        for (int i = 0; i < X; i++){
            for (int j = 0; j < Y; j++){
                secondMatrix[i][j]=Integer.parseInt(args[pointer]);
                pointer++;
            }
        }


        int[][] resultMatrix = multiplyMatrices(firstMatrix, secondMatrix);
        if (resultMatrix != null) {
            printMatrix(resultMatrix);
        } else {
            System.out.println("Несоразмерные матрицы. Невозможно перемножить.");
        }
    }

    public static void printMatrix(int[][] matrix){
        for (int[] row : matrix){
            for (int elem : row){
                System.out.print(elem + " ");
            }
            System.out.println();
        }
    }

    public static int[][] multiplyMatrices(int[][] firstMatrix, int[][] secondMatrix){
        if (firstMatrix[0].length != secondMatrix.length) {
            return null;
        }
        int[][] resultMatrix = new int[firstMatrix.length][secondMatrix[0].length];
        int sum;
        for (int i = 0; i < resultMatrix.length; i++){
            for (int j = 0; j < resultMatrix[0].length; j++){
                sum = 0;
                for (int k = 0; k < firstMatrix[0].length; k++){
                    sum += firstMatrix[i][k]*secondMatrix[k][j];
                }
                resultMatrix[i][j] = sum;
            }
        }
        return resultMatrix;
    }
}

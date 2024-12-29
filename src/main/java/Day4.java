import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Day4
 *
 * @author Jean
 * @since 29/12/2024
 */
public class Day4 {

    public static int countWordFromFile(String word, String filePath, boolean xmasActive) {
        char[][] wordMatrix = fileToMatrix(filePath);
        char[] wordLetters = word.toCharArray();
        return countWordFromMatrix(wordLetters, wordMatrix, xmasActive);
    }

    private static int countWordFromMatrix(char[] wordLetters,
                                           char[][] wordMatrix,
                                           boolean xmasActive) {
        int count = 0;
        for (int i = 0; i < wordMatrix.length; i++) {
            for (int j = 0; j < wordMatrix[i].length; j++) {
                if (!xmasActive && wordMatrix[i][j] == wordLetters[0]) {
                    count += countAllMatches(wordLetters, wordMatrix, j, i);
                }
                if (xmasActive && wordMatrix[i][j] == 'A') {
                    count += countXMasMatches(wordMatrix, j, i);
                }
            }
        }
        return count;
    }

    private static int countXMasMatches(char[][] wordMatrix, int matrixIndexJ, int matrixIndexI) {
        if (matrixIndexJ == 0
                || matrixIndexI == 0
                || matrixIndexJ == wordMatrix.length - 1
                || matrixIndexI == wordMatrix[matrixIndexJ].length - 1) {
            return 0;
        }

        if (((wordMatrix[matrixIndexI - 1][matrixIndexJ - 1] == 'S' && wordMatrix[matrixIndexI + 1][matrixIndexJ + 1] == 'M')
                || (wordMatrix[matrixIndexI - 1][matrixIndexJ - 1] == 'M' && wordMatrix[matrixIndexI + 1][matrixIndexJ + 1] == 'S'))
                && (((wordMatrix[matrixIndexI - 1][matrixIndexJ + 1] == 'S' && wordMatrix[matrixIndexI + 1][matrixIndexJ - 1] == 'M')
                || (wordMatrix[matrixIndexI - 1][matrixIndexJ + 1] == 'M' && wordMatrix[matrixIndexI + 1][matrixIndexJ - 1] == 'S')))) {
            return 1;
        }

        return 0;
    }

    private static int countAllMatches(char[] wordLetters, char[][] wordMatrix, int matrixIndexJ, int matrixIndexI) {
        int count = 0;
        if (checkWordInMatrix(wordLetters, wordMatrix, matrixIndexJ, matrixIndexI, -1, 0)) {
            count++;
        }
        if (checkWordInMatrix(wordLetters, wordMatrix, matrixIndexJ, matrixIndexI, -1, -1)) {
            count++;
        }
        if (checkWordInMatrix(wordLetters, wordMatrix, matrixIndexJ, matrixIndexI, 0, -1)) {
            count++;
        }
        if (checkWordInMatrix(wordLetters, wordMatrix, matrixIndexJ, matrixIndexI, 1, -1)) {
            count++;
        }
        if (checkWordInMatrix(wordLetters, wordMatrix, matrixIndexJ, matrixIndexI, 1, 0)) {
            count++;
        }
        if (checkWordInMatrix(wordLetters, wordMatrix, matrixIndexJ, matrixIndexI, 1, 1)) {
            count++;
        }
        if (checkWordInMatrix(wordLetters, wordMatrix, matrixIndexJ, matrixIndexI, 0, 1)) {
            count++;
        }
        if (checkWordInMatrix(wordLetters, wordMatrix, matrixIndexJ, matrixIndexI, -1, 1)) {
            count++;
        }
        return count;
    }

    private static boolean checkWordInMatrix(char[] wordLetters,
                                             char[][] wordMatrix,
                                             int matrixIndexJ,
                                             int matrixIndexI,
                                             int indexJMovement,
                                             int indexIMovement) {
        for (int i = 1; i < wordLetters.length; i++) {
            matrixIndexJ = matrixIndexJ + indexJMovement;
            matrixIndexI = matrixIndexI + indexIMovement;
            if (
                    matrixIndexI < 0
                            || matrixIndexI >= wordMatrix.length
                            || matrixIndexJ < 0
                            || matrixIndexJ >= wordMatrix[matrixIndexI].length
            ) {
                return false;
            }
            if (wordMatrix[matrixIndexI][matrixIndexJ] != wordLetters[i]) {
                return false;
            }
        }
        return true;
    }

    public static char[][] fileToMatrix(String filePath) {
        try {
            return Files.lines(Paths.get(filePath))
                    .map(String::toCharArray)
                    .toArray(char[][]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new char[0][0];
        }
    }

}

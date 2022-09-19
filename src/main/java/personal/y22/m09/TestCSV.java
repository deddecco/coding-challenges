package personal.y22.m08;

import java.io.*;

public class TestCSV {
    public static void main(String[] args) throws IOException {
        String readThis = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m08\\testfile.csv";
        String writeHere = "C:\\Users\\andre\\IdeaProjects\\coding-challenges\\src\\main\\java\\personal\\y22\\m08\\outfile.csv";
        processCSV(readThis, writeHere);
    }

    public static void processCSV(String filepath, String writeHere) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(writeHere));

        String currentLine = br.readLine();
        String[] headerArray;
        headerArray = currentLine.split(",");
        int indexOfDataPoint1 = whereIsCol(headerArray, "ZIP");
        int indexOfDataPoint2 = whereIsCol(headerArray, "Age");
        int indexOfDataPoint3 = whereIsCol(headerArray, "Name");

        writeHeader(bw, headerArray, indexOfDataPoint1, indexOfDataPoint2, indexOfDataPoint3);

        while ((currentLine = br.readLine()) != null) {
            String[] lineArray = currentLine.split(",");
            writeLine(bw, indexOfDataPoint1, indexOfDataPoint2, indexOfDataPoint3, lineArray);
        }

        bw.close();
        br.close();
    }

    private static void writeLine(BufferedWriter bw, int indexOfDataPoint1, int indexOfDataPoint2, int indexOfDataPoint3, String[] lineArray) throws IOException {
        bw.write(lineArray[indexOfDataPoint1]);
        bw.write(",");
        bw.write(lineArray[indexOfDataPoint2]);
        bw.write(",");
        bw.write(lineArray[indexOfDataPoint3]);
        bw.write("\n");
    }

    private static void writeHeader(BufferedWriter bw, String[] headerArray, int indexOfDataPoint1, int indexOfDataPoint2, int indexOfDataPoint3) throws IOException {
        bw.write(headerArray[indexOfDataPoint1]);
        bw.write(",");
        bw.write(headerArray[indexOfDataPoint2]);
        bw.write(",");
        bw.write(headerArray[indexOfDataPoint3]);
        bw.write("\n");
    }


    public static int whereIsCol(String[] header, String dataPoint) {
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(dataPoint)) {
                return i;
            }
        }
        return -1;
    }
}

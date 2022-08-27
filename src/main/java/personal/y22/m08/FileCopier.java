package personal.y22.m08;

import java.io.*;

public class FileCopier {
    public static void main(String[] args) throws IOException {
        warnUserAboutBadOperation(args);
        // same case, no nums
        makeCopyWithoutLineNums(args);
        // same case, nums
        makeCopyWithLineNums(args);
        // lowercase, no nums
        makeCopyAllLowerCase(args);
        // uppercase, no nums
        makeCopyAllUpperCase(args);
        // upper case, nums
        makeCopyAllUpperCaseLineNums(args);
        // lowercase, nums
        makeCopyAllLowerCaseLineNums(args);
    }

    // displays error message if first command is not copy
    // other, more particular error messages
    // are given in each set of 4 methods that define each operation
    private static void warnUserAboutBadOperation(String[] args) {
        if (!(args[0].equals("copy"))) {
            System.out.println("Cannot perform operation");
            System.exit(-1);
        }
    }

    // copying without line numbers
    private static void copyWithoutLineNums(BufferedReader reader, FileWriter writer) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            writer.write(currentLine);
            writer.write("\n");
        }
    }
    private static void makeCopyWithoutLineNums(String[] args) throws IOException {
        FileWriter writer;
        // if args has 3 elements, they will be [copy instruction, source file, destination file]
        if (args.length == 3) {
            File source = getSourceFileWithoutLineNums(args);
            BufferedReader reader = new BufferedReader(new FileReader(source));
            File destination = getDestFileWithoutLineNums(args);
            writer = new FileWriter(destination);
            copyWithoutLineNums(reader, writer);
            writer.close();
        } else {
            System.out.println("Please ensure you have provided 3 arguments in the following order: ");
            System.out.println("first: copy");
            System.out.println("second: the absolute path of the file to be copied");
            System.out.println("third: the absolute path of the file into which the copy of the first should be written");
        }
    }
    private static File getSourceFileWithoutLineNums(String[] args) {
        return new File(args[1]);
    }
    private static File getDestFileWithoutLineNums(String[] args) {
        return new File(args[2]);
    }

    // copying with line numbers
    private static void copyWithLineNums(BufferedReader reader, FileWriter writer) throws IOException {
        String currentLine;
        int i = 1;
        while ((currentLine = reader.readLine()) != null) {
            writer.write("Line " + i + ": " + currentLine);
            writer.write("\n");
            i++;
        }
    }
    private static void makeCopyWithLineNums(String[] args) throws IOException {
        FileWriter writer;
        // if args has 4 elements, they will be [copy instruction, line number instruction, source file, destination file]
        if (args.length == 4 && "-n".equals(args[1]) && new File(args[2]).equals(getSourceFileWithLineNums(args))) {
            File source = getSourceFileWithLineNums(args);
            BufferedReader reader = new BufferedReader(new FileReader(source));
            File destination = getDestFileWithLineNums(args);
            writer = new FileWriter(destination);
            copyWithLineNums(reader, writer);
            writer.close();
        } else {
            System.out.println("Please ensure you have provided 4 arguments in the following order: ");
            System.out.println("first: copy");
            System.out.println("second: -n");
            System.out.println("third: the absolute path of the file to be copied");
            System.out.println("fourth: the absolute path of the file into which the copy of the first should be written");
        }
    }
    private static File getSourceFileWithLineNums(String[] args) {
        return new File(args[2]);
    }
    private static File getDestFileWithLineNums(String[] args) {
        return new File(args[3]);
    }

    // copying with all uppercase
    private static void copyWithAllUpperCase(BufferedReader reader, FileWriter writer) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            writer.write(currentLine.toUpperCase());
            writer.write("\n");
        }
    }
    private static void makeCopyAllUpperCase(String[] args) throws IOException {
        FileWriter writer;
        if (args.length == 4 && "-u".equals(args[1]) && new File(args[2]).equals(getSourceFileWithAllUpperCase(args))) {
            File source = getSourceFileWithAllUpperCase(args);
            BufferedReader reader = new BufferedReader(new FileReader(source));
            File destination = getDestFileWithAllUpperCase(args);
            writer = new FileWriter(destination);
            copyWithAllUpperCase(reader, writer);
            writer.close();
        } else {
            System.out.println("Please ensure you have provided 4 arguments in the following order: ");
            System.out.println("first: copy");
            System.out.println("second: -u");
            System.out.println("third: the absolute path of the file to be copied");
            System.out.println("fourth: the absolute path of the file into which the copy of the first should be written");
        }
    }
    private static File getSourceFileWithAllUpperCase(String[] args) {
        return new File(args[2]);
    }
    private static File getDestFileWithAllUpperCase(String[] args) {
        return new File(args[3]);
    }

    // copying with all lowercase
    private static void copyWithAllLowerCase(BufferedReader reader, FileWriter writer) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            writer.write(currentLine.toLowerCase());
            writer.write("\n");
        }
    }
    private static void makeCopyAllLowerCase(String[] args) throws IOException {
        FileWriter writer;
        if (args.length == 4 && "-l".equals(args[1]) && new File(args[2]).equals(getSourceFileWithAllLowerCase(args))) {
            File source = getSourceFileWithAllLowerCase(args);
            BufferedReader reader = new BufferedReader(new FileReader(source));
            File destination = getDestFileWithAllLowerCase(args);
            writer = new FileWriter(destination);
            copyWithAllLowerCase(reader, writer);
            writer.close();
        } else {
            System.out.println("Please ensure you have provided 4 arguments in the following order: ");
            System.out.println("first: copy");
            System.out.println("second: -l");
            System.out.println("third: the absolute path of the file to be copied");
            System.out.println("fourth: the absolute path of the file into which the copy of the first should be written");
        }
    }
    private static File getSourceFileWithAllLowerCase(String[] args) {
        return new File(args[2]);
    }
    private static File getDestFileWithAllLowerCase(String[] args) {
        return new File(args[3]);
    }

    // copy with line numbers in uppercase
    private static void copyWithLineNumbersUpperCase(BufferedReader reader, FileWriter writer) throws IOException {
        String currentLine;
        int i = 1;
        while ((currentLine = reader.readLine()) != null) {
            writer.write("Line " + i + ": " + currentLine.toUpperCase());
            writer.write("\n");
        }
    }
    private static void makeCopyAllUpperCaseLineNums(String[] args) throws IOException {
        FileWriter writer;
        if (args.length == 5 && (("-u".equals(args[1]) && "-n".equals(args[2])) || ("-n".equals(args[1]) && "-u".equals(args[2]))) && new File(args[3]).equals(getSourceFileWithAllUpperCaseLineNums(args))) {
            File source = getSourceFileWithAllUpperCaseLineNums(args);
            BufferedReader reader = new BufferedReader(new FileReader(source));
            File destination = getDestFileWithAllUpperCaseLineNums(args);
            writer = new FileWriter(destination);
            copyWithLineNumbersUpperCase(reader, writer);
            writer.close();
        } else {
            System.out.println("Please ensure you have provided 5 arguments in the following order: ");
            System.out.println("first: copy");
            System.out.println("second: -n or -u");
            System.out.println("third: which ever option, -n or -u, you did not place in the second slot");
            System.out.println("fourth: the absolute path of the file to be copied");
            System.out.println("fifth: the absolute path of the file into which the copy of the first should be written");
        }
    }
    private static File getSourceFileWithAllUpperCaseLineNums(String[] args) {
        return new File(args[3]);
    }
    private static File getDestFileWithAllUpperCaseLineNums(String[] args) {
        return new File(args[4]);
    }

    // copy with line numbers in lowercase
    private static void copyWithLineNumbersLowerCase(BufferedReader reader, FileWriter writer) throws IOException {
        String currentLine;
        int i = 1;
        while ((currentLine = reader.readLine()) != null) {
            writer.write("Line " + i + ": " + currentLine.toLowerCase());
            writer.write("\n");
        }
    }
    private static void makeCopyAllLowerCaseLineNums(String[] args) throws IOException {
        FileWriter writer;
        if (args.length == 5 && (("-l".equals(args[1]) && "-n".equals(args[2])) || ("-n".equals(args[1]) && "-l".equals(args[2]))) && new File(args[3]).equals(getSourceFileWithAllLowerCaseLineNums(args))) {
            File source = getSourceFileWithAllLowerCaseLineNums(args);
            BufferedReader reader = new BufferedReader(new FileReader(source));
            File destination = getDestFileWithAllLowerCaseLineNums(args);
            writer = new FileWriter(destination);
            copyWithLineNumbersLowerCase(reader, writer);
            writer.close();
        } else {
            System.out.println("Please ensure you have provided 5 arguments in the following order: ");
            System.out.println("first: copy");
            System.out.println("second: -n or -l");
            System.out.println("third: which ever option, -n or -l, you did not place in the second slot");
            System.out.println("fourth: the absolute path of the file to be copied");
            System.out.println("fifth: the absolute path of the file into which the copy of the first should be written");
        }
    }
    private static File getSourceFileWithAllLowerCaseLineNums(String[] args) {
        return new File(args[3]);
    }
    private static File getDestFileWithAllLowerCaseLineNums(String[] args) {
        return new File(args[4]);
    }
}
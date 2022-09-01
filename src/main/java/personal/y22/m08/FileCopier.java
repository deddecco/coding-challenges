package personal.y22.m08;

import java.io.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileCopier {
    public static void main(String[] args) throws IOException {
        boolean argsLengthOK = (args.length <= 5 && args.length >= 3);
        if (!argsLengthOK || !args[0].equals("copy")) {
            warnUserAboutBadOperation();
        }
        if (args.length == 3) {
            makeCopyWithoutLineNums(args);
        }

        if (args.length == 4) {
            if (Objects.equals(args[1], "-n")) {
                makeCopyWithLineNums(args);
            } else if (Objects.equals(args[1], "-l")) {
                makeCopyAllLowerCase(args);
            } else if (Objects.equals(args[1], "-u")) {
                makeCopyAllUpperCase(args);
            } else if (Objects.equals(args[1], "-c")) {
                makeFirstLetterCapital(args);
            } else {
                warnUserAboutBadOperation();
            }
        }
        if (args.length == 5) {
            if (Objects.equals(args[1], "-n")) {
                if (Objects.equals(args[2], "-l")) {
                    makeCopyAllLowerCaseLineNums(args);
                } else if (Objects.equals(args[2], "-u")) {
                    makeCopyAllUpperCaseLineNums(args);
                } else if (Objects.equals(args[2], "-c")) {
                    makeFirstLetterCapitalWithLineNums(args);
                } else {
                    warnUserAboutBadOperation();
                }
            }

            if (Objects.equals(args[1], "-l")) {
                if (Objects.equals(args[2], "-n")) {
                    makeCopyAllLowerCaseLineNums(args);
                } else {
                    warnUserAboutBadOperation();
                }
            }
            if (Objects.equals(args[1], "-u")) {
                if (Objects.equals(args[2], "-n")) {
                    makeCopyAllUpperCaseLineNums(args);
                } else {
                    warnUserAboutBadOperation();
                }
            }
            if (Objects.equals(args[1], "-c")) {
                if (Objects.equals(args[2], "-n")) {
                    makeFirstLetterCapitalWithLineNums(args);
                } else {
                    warnUserAboutBadOperation();
                }
            }
        }
    }

    private static void warnUserAboutBadOperation() {
        System.out.println("To copy one file to another: copy file1 file2");
        System.out.println("To copy one file to another in all uppercase: copy -u file1 file2");
        System.out.println("To copy one file to another in all lowercase: copy -l file1 file2");
        System.out.println("To copy one file to another with line numbers: copy -n file1 file2");
        System.out.println("To copy one file to another capitalizing the first letter of the line: copy -c file1 file2");
        System.out.println("To copy one file to another in all uppercase with line numbers: copy -u -n file1 file2");
        System.out.println("To copy one file to another in all uppercase with line numbers: copy -n -u file1 file2");
        System.out.println("To copy one file to another in all lowercase with line numbers: copy -l -n file1 file2");
        System.out.println("To copy one file to another in all lowercase with line numbers: copy -n -l file1 file2");
        System.out.println("To copy one file to another capitalizing the first letter of the line and adding line numbers: copy -n -c file1 file2");
        System.out.println("To copy one file to another capitalizing the first letter of the line and adding line numbers: copy -c -n file1 file2");
        System.exit(-1);
    }

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

        File source = getSourceFile(args);
        BufferedReader reader = new BufferedReader(new FileReader(source));
        File destination = getDestFile(args);
        writer = new FileWriter(destination);
        copyWithoutLineNums(reader, writer);
        writer.close();
    }

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
        File source = getSourceFile(args);
        BufferedReader reader = new BufferedReader(new FileReader(source));
        File destination = getDestFile(args);
        writer = new FileWriter(destination);
        copyWithLineNums(reader, writer);
        writer.close();
    }

    private static void copyWithAllUpperCase(BufferedReader reader, FileWriter writer) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            writer.write(currentLine.toUpperCase());
            writer.write("\n");
        }
    }

    private static void makeCopyAllUpperCase(String[] args) throws IOException {
        FileWriter writer;

        File source = getSourceFile(args);
        BufferedReader reader = new BufferedReader(new FileReader(source));
        File destination = getDestFile(args);
        writer = new FileWriter(destination);
        copyWithAllUpperCase(reader, writer);
        writer.close();
    }

    private static void copyWithAllLowerCase(BufferedReader reader, FileWriter writer) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            writer.write(currentLine.toLowerCase());
            writer.write("\n");
        }
    }

    private static void makeCopyAllLowerCase(String[] args) throws IOException {
        FileWriter writer;
        File source = getSourceFile(args);
        BufferedReader reader = new BufferedReader(new FileReader(source));
        File destination = getDestFile(args);
        writer = new FileWriter(destination);
        copyWithAllLowerCase(reader, writer);
        writer.close();
    }

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
        File source = getSourceFile(args);
        BufferedReader reader = new BufferedReader(new FileReader(source));
        File destination = getDestFile(args);
        writer = new FileWriter(destination);
        copyWithLineNumbersUpperCase(reader, writer);
        writer.close();
    }

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
        File source = getSourceFile(args);
        BufferedReader reader = new BufferedReader(new FileReader(source));
        File destination = getDestFile(args);
        writer = new FileWriter(destination);
        copyWithLineNumbersLowerCase(reader, writer);
        writer.close();
    }

    private static final Pattern FIRST_LETTER = Pattern.compile("([a-zA-Z])");

    private static void capitalizeFirstLetterOfLine(BufferedReader reader, FileWriter writer) throws IOException {
        String currLine;
        while ((currLine = reader.readLine()) != null) {
            Matcher matcher = FIRST_LETTER.matcher(currLine);
            String modLine = matcher.replaceFirst(matchResult -> matchResult.group(1).toUpperCase());
            writer.write(modLine);
            writer.write("\n");
        }
    }

    private static void makeFirstLetterCapital(String[] args) throws IOException {
        FileWriter writer;
        if (new File(args[2]).equals(getSourceFile(args))) {
            File source = getSourceFile(args);
            BufferedReader reader = new BufferedReader(new FileReader(source));
            File destination = getDestFile(args);
            writer = new FileWriter(destination);
            capitalizeFirstLetterOfLine(reader, writer);
            writer.close();
        }
    }

    private static File getSourceFile(String[] args) {
        if (new File(args[args.length - 2]).exists()) {
            return new File(args[args.length - 2]);
        } else {
            System.out.println("The file " + args[args.length - 2] + " does not exist");
            warnUserAboutBadOperation();
            return null;
        }
    }

    private static File getDestFile(String[] args) {
        return new File(args[args.length - 1]);
    }

    private static void capitalizeFirstLetterOfLineWithLineNums(BufferedReader reader, FileWriter writer) throws IOException {
        String currLine;
        int i = 1;
        while ((currLine = reader.readLine()) != null) {
            Matcher matcher = FIRST_LETTER.matcher(currLine);
            String modLine = matcher.replaceFirst(matchResult -> matchResult.group(1).toUpperCase());
            writer.write("Line " + i + ": " + modLine);
            writer.write("\n");
            i++;
        }
    }

    private static void makeFirstLetterCapitalWithLineNums(String[] args) throws IOException {
        FileWriter writer;
        if (new File(args[3]).equals(getSourceFile(args))) {
            File source = getSourceFile(args);
            BufferedReader reader = new BufferedReader(new FileReader(source));
            File destination = getDestFile(args);
            writer = new FileWriter(destination);
            capitalizeFirstLetterOfLineWithLineNums(reader, writer);
            writer.close();
        }
    }
}
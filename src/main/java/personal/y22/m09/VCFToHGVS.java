package personal.y22.m09;

import java.io.*;
import java.util.Objects;

public class VCFToHGVS {

    // rewrites a VCF row containing a SNP into HGVS nomenclature
    public static String convertSNP(String chr, int pos, String ref, String alt) {
        return "chr" + chr + ":g." + pos + ref + ">" + alt;
    }

    // rewrites a VCF row containing a deletion into HGVS nomenclature
    public static String convertDel(String chr, int pos, String ref, String alt) {
        String del = "";
        if (ref.length() > 1 && alt.length() == 0) {
            del += "chr" + chr + ":g." + pos + "_" + (pos + ref.length() - 1) + "del" + ref;
        } else if (ref.length() == 1 && alt.length() == 0) {
            del = "chr" + chr + ":g." + pos + "del" + ref;
        } else {
            System.out.println("Please try again. You cannot delete <1 base");
        }
        return del;
    }

    // rewrites a VCF row containing an insertion into HGVS nomenclature
    public static String convertIns(String chr, int pos, String alt) {
        String ins = "";
        if (alt.length() >= 1) {
            ins += "chr" + chr + ":g." + (pos - 1) + "_" + pos + "ins" + alt;
        } else {
            System.out.println("Please try again. You cannot insert <1 base");
        }
        return ins;
    }

    // detects a SNP (after trimming)
    public static boolean isSNP(String ref, String alt) {
        return (ref.length() == 1) && (alt.length() == 1);
    }

    // detects a deletion (after trimming)
    private static boolean isDel(String alt) {
        return alt.length() == 0;
    }

    // detects an insertion (after trimming)
    private static boolean isInsertion(String ref, String alt) {
        return ref.length() < alt.length();
    }

    // start trim from right
    // trim from left requires adjusting pos
    // trim from right, stop when no longer matching
    // go to left, trim until no more matches
    private static String[] trim(int pos, String ref, String alt) {
        boolean canTrimFromRight = true;
        while (canTrimFromRight) {
            if (ref.length() == 0 || alt.length() == 0) {
                canTrimFromRight = false;
            } else if (ref.charAt(ref.length() - 1) == alt.charAt(alt.length() - 1)) {
                ref = ref.substring(0, ref.length() - 1);
                alt = alt.substring(0, alt.length() - 1);
            } else {
                canTrimFromRight = false;
            }
        }

        boolean canTrimFromLeft = true;
        int trimPos = 0;
        while (canTrimFromLeft) {
            if (ref.length() == 0 || alt.length() == 0) {
                canTrimFromLeft = false;
            } else if (ref.charAt(0) == alt.charAt(0)) {
                ref = ref.substring(1);
                alt = alt.substring(1);
                trimPos++;
            } else {
                canTrimFromLeft = false;
            }
        }
        pos += trimPos;
        return new String[]{ref, alt, String.valueOf(pos)};
    }

    // if an input file exists and a line that was read is not a metadata line,
    // provided that variant passed the filtering process,
    // write that line to the output file
    // after all lines have been written, close the reader and  writer objects
    // to mitigate against resource leaks
    public static void processVCF(String VCFFilePath, String HGVSOutputFile, String[] header) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(VCFFilePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(HGVSOutputFile));

        String line;
        String currentVar;

        String[] resultOfSplit;
        if (new File(VCFFilePath).exists()) {
            while ((line = reader.readLine()) != null) {
                if (!isMetaData(line) && !isHeader(line)) {
                    currentVar = line;
                    resultOfSplit = currentVar.split("\\s");

                    String chrom = resultOfSplit[whereIsDataPoint(header, "#CHROM")];
                    int pos = Integer.parseInt(resultOfSplit[whereIsDataPoint(header, "POS")]);
                    String ref = resultOfSplit[whereIsDataPoint(header, "REF")];

                    boolean filterPass = (Objects.equals(resultOfSplit[whereIsDataPoint(header, "FILTER")], "PASS"));
                    // find the index where ALT is stored
                    int indexOfAlt = whereIsDataPoint(header, "ALT");
                    // call split with a comma on that index
                    String[] altSplit = resultOfSplit[indexOfAlt].split(",");
                    // duplicate resultOfSplit as many times as there are commas
                    for (String currentAlt : altSplit) {
                        String output = generateOutput(chrom, pos, ref, currentAlt);
                        if (output != null && filterPass) {
                            writer.write(output);
                            writer.write("\n");
                        }
                    }
                }
            }
        }
        writer.close();
        reader.close();
    }

    // trim the reads provided for ref and alt, and adjust pos if necessary
    // then create a String named "call", and place into that string the HGVS nomenclature
    // describing either a SNP, a deletion, or an insertion
    public static String generateOutput(String chrom, int pos, String ref, String alt) {
        String[] trimmed = trim(pos, ref, alt);
        String call = "";
        if (isSNP(trimmed[0], trimmed[1])) {
            call += convertSNP(chrom, Integer.parseInt(trimmed[2]), trimmed[0], trimmed[1]);
        } else if (isDel(trimmed[1])) {
            call += convertDel(chrom, Integer.parseInt(trimmed[2]), trimmed[0], trimmed[1]);
        } else if (isInsertion(trimmed[0], trimmed[1])) {
            call += convertIns(chrom, Integer.parseInt(trimmed[2]), trimmed[1]);
        } else {
            System.out.println("Unsupported: " + chrom + " " + trimmed[2] + " " + trimmed[0] + " " + trimmed[1]);
        }
        return call;
    }

    // the VCF is a tab-delimited text file
    // each new column, separated by its left and right neighbors by a tab,
    // contains the same information as its top and bottom neighbors
    // about a different variant
    public static String[] parseLine(String currentLine) {
        return currentLine.split("\\s");
    }

    // metadata lines start with ##
    // if this expression evaluates to true, we will ignore the line
    // because metadata lines contain no variants
    private static boolean isMetaData(String line) {
        return line.charAt(0) == '#' && line.charAt(1) == '#';
    }

    // the header is a single line beginning with only 1 #
    // and contains the name of the data point found in a given
    // position in a row representative of a single change
    private static boolean isHeader(String line) {
        return line.charAt(0) == '#' && line.charAt(1) != '#';
    }

    // linear search to look through a header row
    // and find the index of a given data point
    public static int whereIsDataPoint(String[] header, String dataPoint) {
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(dataPoint)) {
                return i;
            }
        }
        return -1;
    }

    // read a file found by following the provided path
    // ignore metadata
    // stop reading once first non-metadata line has been read
    // this is the header line
    // split the header by white space and save the result of the split
    // into an array of strings
    public static String[] getHeader(String filePath) throws IOException {
        String line;
        String header;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String[] resultOfSplit = new String[1000];
        // if the file exists...
        if (new File(filePath).exists()) {
            // ... and there are lines left...
            while ((line = reader.readLine()) != null) {
                // ... and the line being read isn't metadata...
                if (!isMetaData(line)) {
                    // ... the first line read is the header
                    header = line;
                    resultOfSplit = parseLine(header);
                    break;
                }
            }
        }
        return resultOfSplit;
    }

    // given two arguments from the command line (one for each  file, input and output)
    // check if the input file specified by the first argument exists
    // if so, process the VCF in that argument => an output file given by the second argument
    // in HGVS nomenclature
    // if the file in the first argument does not exist, print an error message and immediately
    // terminate execution. do not attempt to process a non-existent file
    public static void main(String[] args) throws IOException {
        // receive files as command line args
        String inputFilePath = args[0];
        String outputFilePath = args[1];

        if (new File(inputFilePath).exists()) {
            String[] headerRowVCF = getHeader(inputFilePath);
            processVCF(inputFilePath, outputFilePath, headerRowVCF);
        } else {
            System.out.println("File " + inputFilePath + " does not exist");
            System.exit(-1);
        }

    }
}
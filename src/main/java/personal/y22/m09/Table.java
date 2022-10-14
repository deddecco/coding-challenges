package personal.y22.m09;

import java.io.*;
import java.util.*;

public class Table {
    public int numRecords = 0;
    private String fileName;
    private String tableName;
    private String[] headings;
    public String[][] data;

    public String[] getHeadings() {
        String[] headingsCopy = new String[headings.length];
        System.arraycopy(headings, 0, headingsCopy, 0, headingsCopy.length);
        return headingsCopy;
    }

    public String getTableName() {
        return tableName;
    }

    public String getVal(String[] record, String col) {
        return record[whereIsColumn(col)];
    }

    // prints almost all the file,
    // i.e., all rows of the file except the first
    public void printData() {
        for (int i = 0; i < numRecords; i++) {
            System.out.println(Arrays.toString(this.data[i]));
        }
    }

    // prints the header of the file
    public void printHeader() {
        System.out.println(Arrays.toString(this.headings));
    }

    // look for corresponding String[] containing data for given id
    public String[] getRecord(String id) {
        for (int i = 0; i < numRecords; i++) {
            if (data[i][0].equals(id)) {
                return data[i];
            }
        }
        return null;
    }

    // adds a record to the end of a table
    public void addRecord(String[] newRecord) {
        if ((numRecords + 1) <= data.length) {
            int indexID = whereIsColumn("ID");
            if (canAddID(newRecord[indexID])) {
                data[numRecords] = newRecord;
                numRecords++;
            } else {
                System.out.println("Record with this ID already exists");
            }
        } else {
            System.out.println("Too many records: maximum array length exceeded");
        }
    }

    public void deleteRecord(String id) {

        int indexToDelete = indexOfID(id);
        String[][] newData = new String[numRecords - 1][data[0].length];
        int i = 0;
        while (i < indexToDelete) {
            newData[i] = this.data[i];
            i++;
        }
        i = indexToDelete + 1;
        while (i < numRecords) {
            newData[i - 1] = this.data[i];
            i++;
        }
        numRecords--;

        data = newData;
    }

    // given an id, the column in which a change is to occur, and the new value
    // find the record with that id
    // go to the specified column
    // overwrite that column with the new value
    // and return the modified record
    public void modifyRecord(String id, String colToChange, String newVal) {
        String[] modifiedRecord = getRecord(id);
        int indexToModify = whereIsColumn(colToChange);
        modifiedRecord[indexToModify] = newVal;
        this.data[indexOfID(id)] = modifiedRecord;
    }

    // returns the index of the record in the table with the given id
    public int indexOfID(String id) {
        for (int i = 0; i < data.length; i++) {
            if (Objects.equals(data[i][whereIsColumn("ID")], id)) {
                return i;
            }
        }
        return -1;
    }

    // identifies the location of a given column
    // name within the header by its index
    public int whereIsColumn(String columnName) {
        for (int i = 0; i < headings.length; i++) {
            if (headings[i].equals(columnName)) {
                return i;
            }
        }
        return -1;
    }

    // returns true if ID is not present in structure yet
    // returns false if ID is already present in structure
    public boolean canAddID(String id) {
        return indexOfID(id) == -1;
    }

    // retrieves the data from the given file
    // and places the information contained
    // in that file into a 2d array of Strings
    public void loadTable(String tableName, String dataFilePath) throws IOException {
        this.tableName = tableName;
        this.fileName = dataFilePath;

        if (new File(dataFilePath).exists()) {
            BufferedReader br = new BufferedReader(new FileReader(dataFilePath));
            String currentLine = br.readLine();
            currentLine = currentLine.trim();
            this.headings = currentLine.split("\t");
            int i = 0;
            this.data = new String[1000][10];
            while ((currentLine = br.readLine()) != null) {
                this.data[i] = currentLine.trim().split("\t");
                i++;
                this.numRecords++;
            }
        } else {
            System.out.println("Config file not found");
        }
    }

    // given the String[][] data containing the data
    // that matches with the headings in String[] headings
    // save the headings and data into a file specified by filename
    // headings, data, and filename are properties of the class
    public void saveToDisk() throws IOException {
        if (new File(fileName).exists()) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

            String headingString = String.join("\t", headings);
            bw.write(headingString);

            for (int i = 0; i < numRecords; i++) {
                bw.write("\n");
                String dataString = String.join("\t", this.data[i]);
                bw.write(dataString);
            }

            bw.close();
        }
    }

    public List<String[]> findAllMatches(String colName, String colValue) {
        // List to store matches
        List<String[]> matches = new ArrayList<>();

        int columnLookingThrough = whereIsColumn(colName);
        // look through the whole contents of data
        // and place all matches in matches (first match at matches[0], second at matches[1], etc.)
        for (int i = 0; i < numRecords; i++) {
            if (data[i][columnLookingThrough].equals(colValue)) {
                matches.add(data[i]);
            }
        }
        // return the list with the matches
        return matches;
    }




    // returns a list containing all records
    // in the table that called the method
    public List<String[]> findAll() {
        // List to store records
        List<String[]> matches = new ArrayList<>();

        // add all records to list
        for (int i = 0; i < numRecords; i++) {
            matches.add(data[i]);
        }

        // return list
        return matches;
    }


    // given a column by which the sort should be conducted,
    // call Arrays.sort() on the 2d array containing the data
    public void sortRecords(String columnToSortBy) {

        int columnToSortThrough = whereIsColumn(columnToSortBy);
        Comparator<String[]> comparator = Comparator.comparing(o -> o[columnToSortThrough]);

        String[][] datacopy = new String[numRecords][data[0].length];
        System.arraycopy(data, 0, datacopy, 0, numRecords);

        Arrays.sort(datacopy, comparator);
    }

}
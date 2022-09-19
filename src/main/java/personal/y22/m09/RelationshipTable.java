package personal.y22.m09;

import java.io.*;
import java.util.*;

public class RelationshipTable {

    private int numRelRecords = 0;
    public String relFileName;
    public String relTableName;
    private String[] relTableHeadings;
    private String[][] relData;

    private Table table1;
    private Table table2;

    // print every row of the data table
    public void printData() {
        for (int i = 0; i < numRelRecords; i++) {
            System.out.println(Arrays.toString(this.relData[i]));
        }
    }

    // prints the header of the file
    public void printHeader() {
        System.out.println(Arrays.toString(this.relTableHeadings));
    }

    // read from file and get 2 ids that define relationship, augment data with info from records corresponding to 2 ids--
    // from 2 different tables-- i.e., given relationship between product and department, provide info from tables
    // containing only information about product and only about department
    public void loadRelTable(String tableName, String dataFilePath, Table dataTable1, Table dataTable2) throws IOException {
        this.relTableName = tableName;
        this.relFileName = dataFilePath;
        this.table1 = dataTable1;
        this.table2 = dataTable2;

        if (new File(dataFilePath).exists()) {
            // read first line of file at specified path and save into array containing headings
            BufferedReader br = new BufferedReader(new FileReader(dataFilePath));
            String currentLine = br.readLine();
            // use String.split() to separate headings tabwise
            String[] headingArray = currentLine.split("\t");
            List<String> headingList = new ArrayList<>();

            // ArrayList = dynamic;
            // add each element of (static-size) array to dynamic-size AL
            for (int i = 0; i < headingArray.length; i++) {
                headingList.add(headingArray[i]);
            }

            // get headings from table1
            String[] table1headings = table1.getHeadings();
            for (int i = 0; i < table1headings.length; i++) {
                table1headings[i] = table1headings[i].trim();
                table1headings[i] = dataTable1.getTableName() + "_" + table1headings[i];
            }
            // get headings from table2
            String[] table2headings = table2.getHeadings();
            for (int j = 0; j < table2headings.length; j++) {
                table2headings[j] = table2headings[j].trim();
                table2headings[j] = dataTable2.getTableName() + "_" + table2headings[j];
            }
            // combine the two tables' headings with the relationship headings
            String[] mergeWithDups = Merge.mergeArrays(table1headings, table2headings);

            // remove duplicates after merging
            for (int i = 0; i < mergeWithDups.length; i++) {
                if (!headingList.contains(mergeWithDups[i])) {
                    headingList.add(mergeWithDups[i]);
                }
            }

            // go back to an immutable array
            this.relTableHeadings = headingList.toArray(new String[0]);

            // calculating important indices ahead of time for reference in later for-loop
            int[] indicesOfColumnsInRelTabHead = new int[relTableHeadings.length];
            int[] indicesOfColumnsInTable1 = new int[relTableHeadings.length];
            int[] indicesOfColumnsInTable2 = new int[relTableHeadings.length];

            // first 2 indices (2 ids) taken care of
            for (int index = 2; index < relTableHeadings.length; index++) {

                // splitArray[0] = name of table
                // splitArray[1] = column within that table
                String[] splitArray = splitByUnderScore(relTableHeadings[index]);
                boolean isTable1 = splitArray[0].equals(table1.getTableName());
                boolean isTable2 = splitArray[0].equals(table2.getTableName());
                indicesOfColumnsInRelTabHead[index] = whereIsColumn(relTableHeadings[index]);
                if (isTable1) {
                    indicesOfColumnsInTable1[index] = table1.whereIsColumn(splitArray[1]);
                }
                if (isTable2) {
                    indicesOfColumnsInTable2[index] = table2.whereIsColumn(splitArray[1]);
                }
            }

            this.relData = new String[1000][relTableHeadings.length];
            int i = 0;

            // read whole file
            while ((currentLine = br.readLine()) != null) {
                assignPos(currentLine.split("\t"), i, 0);

                String idTable1 = this.relData[i][0];
                String idTable2 = this.relData[i][1];

                // get records from tables 1 and 2
                String[] recordFrom1 = table1.getRecord(idTable1);
                String[] recordFrom2 = table2.getRecord(idTable2);

                // populate 3rd column to end of file with data from records found above
                for (int index = 2; index < relTableHeadings.length; index++) {
                    String[] splitArray = splitByUnderScore(relTableHeadings[index]);
                    // determine which table a data column came from
                    boolean isTable1 = splitArray[0].equals(table1.getTableName());
                    boolean isTable2 = splitArray[0].equals(table2.getTableName());
                    // save info from each table into the relationship table
                    if (isTable1) {
                        relData[i][indicesOfColumnsInRelTabHead[index]] = recordFrom1[indicesOfColumnsInTable1[index]];
                    }
                    if (isTable2) {
                        relData[i][indicesOfColumnsInRelTabHead[index]] = recordFrom2[indicesOfColumnsInTable2[index]];
                    }
                }
                // move to next row in structure
                i++;
                // add one to number of records
                this.numRelRecords++;
            }
        } else {
            // if file does not exist
            System.out.println("Config file not found");
        }
        for (int i = 0; i < numRelRecords; i++) {
            System.out.println(Arrays.toString(relData[i]));
        }
    }

    // return a list of all the records for which the value of a given column colName is the value colValue
    public List<String[]> findAllMatches(String colName, String colValue) {
        // List to store matches
        List<String[]> matches = new ArrayList<>();

        int columnLookingThrough = whereIsColumn(colName);
        // look through the whole contents of data
        // and place all matches in matches (first match at matches[0], second at matches[1], etc.)
        for (int i = 0; i < numRelRecords; i++) {
            if (relData[i][columnLookingThrough].equals(colValue)) {
                matches.add(table1.data[i]);
                //matches.add(table2.data[i]);
            }
        }
        // return the list with the matches
        return matches;
    }


    public List<String[]> getAllVals(String tableName, String val) {
        // given name, determine if name refers to table1 or table2
        // find all relationships for which val is parameter
        boolean isTable1 = (Objects.equals(tableName, table1.getTableName()));
        boolean isTable2 = (Objects.equals(tableName, table2.getTableName()));
        List<String[]> matches = new ArrayList<>();
        if (isTable1) {
            //System.out.println("Value from table1, searching in table2");
            matches = table2.findAllMatches(table1.getTableName(), val);
        } else if (isTable2) {
            //System.out.println("Value from table2, searching in table1");
            matches = table1.findAllMatches(table2.getTableName(), val);
        } else {
            System.out.println("Unable to find table " + tableName + " in this relationship");
            System.exit(-1);
        }
        return matches;
    }

    // returns the record with the given dataID if it exists, or null otherwise
    public String[] getRecord(String dataID) {
        for (int i = 0; i < numRelRecords; i++) {
            if (this.relData[i][whereIsColumn("ID")].equals(dataID)) {
                return this.relData[i];
            }
        }
        return null;
    }

    public void addRecord(String[] newRecord) {
        if ((numRelRecords + 1) <= relData.length) {
            if (canAddDataID(newRecord[whereIsColumn("PRODUCT already exists ")])) {
                relData[numRelRecords] = newRecord;
                numRelRecords++;
            } else {
                System.out.println("Record with this ID already exists");
            }
        } else {
            System.out.println("Too many records: maximum array length exceeded");
        }
    }

    // given an id, a column which needs to be modified, and the new value for that record and that column, change the value
    public void modifyRecord(String dataID, String colToChange, String newVal) {
        String[] modifiedRecord = getRecord(dataID);
        int indexToModify = whereIsColumn(colToChange);
        modifiedRecord[indexToModify] = newVal;
        this.relData[indexOfDataID(dataID)] = modifiedRecord;
    }

    // finds the dataID specified within a given record and reports its index location
    public int indexOfDataID(String id) {
        for (int i = 0; i < relData.length; i++) {
            if (Objects.equals(relData[i][whereIsColumn("ID")], id)) {
                return i;
            }
        }
        return -1;
    }

    public int indexOfRelID(String id) {
        for (int i = 0; i < relData.length; i++) {
            if (Objects.equals(relData[i][whereIsColumn("RelID")], id)) {
                return i;
            }
        }
        return -1;
    }

    // given a column name, look through the header array and return that column's index in the header
    public int whereIsColumn(String columnName) {
        for (int i = 0; i < relTableHeadings.length; i++) {
            if (relTableHeadings[i].equals(columnName)) {
                return i;
            }
        }
        return -1;
    }

    // guarantees a given dataID is unique before allowing addition of a record with that ID into a relationship table
    public boolean canAddDataID(String dataID) {
        return indexOfDataID(dataID) == -1;
    }

    public boolean canAddRelID(String relID) {
        return indexOfRelID(relID) == -1;
    }


    // write the contents of the two arrays
    // (one 1d for the headings, one 2d for the data)
    // to a single file
    // and then close the writing object to save the file
    public void saveToDisk() throws IOException {
        if (new File(relFileName).exists()) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(relFileName));

            String headingString = String.join("\t", relTableHeadings);
            bw.write(headingString);

            for (int i = 0; i < numRelRecords; i++) {
                bw.write("\n");
                String relString = String.join("\t", this.relData[i]);
                bw.write(relString);
            }
            bw.close();
        }
    }

    // String.split() creates an array of exactly the right size which is smaller than what we want
    // so do the split into a temp array, and copy from the temp array into the data structure
    // we actually want to work with
    public void assignPos(String[] line, int row, int startCol) {

        for (int i = startCol; i < (line.length + startCol); i++) {
            this.relData[row][i] = line[i];
        }
    }

    // given an individual heading, split it by "_" what is to the left of the underscore will
    // go in the first cell of the array created by the split-- this will be the name of the table from which the info came;
    // and whatever is to the right of the underscore will provide the column from that table from where the info came
    public String[] splitByUnderScore(String heading) {
        return heading.split("_");
    }

}
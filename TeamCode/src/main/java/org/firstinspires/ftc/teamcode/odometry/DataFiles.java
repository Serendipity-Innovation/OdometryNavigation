package org.firstinspires.ftc.teamcode.odometry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataFiles {
    // version File is the file that holds the version
    File versionFile =  new File
            ("/Users/JustKyle-ngaround/Desktop/odometryGraphData/version.txt");

    // Get Version of File
    public String getVersion(File filename) throws FileNotFoundException {
        try {
            Scanner dataReader = new Scanner(filename);
            String version = null;
            while (dataReader.hasNextLine()) { //keeps repeating until it gets to the last int
                version = dataReader.nextLine();
            }
            return version;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    // Making files function
    public void makeDataFile(String version) throws IOException {
        File graphData = new File(
                "/Users/JustKyle-ngaround/Desktop/odometryGraphData/odometryMapData"
                        + version + ".txt");
    }

    // Update version file
    public void updateVersion(String version, FileWriter versionFileWriter) throws IOException {
        try {
            int versionIncrementInt = Integer.valueOf(version);
            versionIncrementInt ++;
            String versionIncrementString = Integer.toString(versionIncrementInt);
            versionFileWriter.write("/n" + versionIncrementString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Add data(will be odometry data) to a function
    public void addData (FileWriter graphData, String value1, String value2) throws IOException {
        graphData.append(value1 + "," + value2 + ",");
    }

    // Writing Data Files
    public void writeGraphDataFileAndUpdateVersion (double value1, double value2) throws IOException {
        // Make the coordinates which are doubles to strings
        String stringValue1 = Double.toString(value1);
        String stringValue2 = Double.toString(value2);
        // Create the file and writer
        String version = getVersion(versionFile);
        makeDataFile(version);
        FileWriter graphData = new FileWriter(
                "/Users/JustKyle-ngaround/Desktop/odometryGraphData/odometryMapData"
                + version + ".txt");

        // Add the values into the odometryGraphData file
        addData(graphData, stringValue1, stringValue2);

        // Update the new version
        FileWriter versionFileWriter = new FileWriter(versionFile);
        updateVersion(version, versionFileWriter);
    }
}

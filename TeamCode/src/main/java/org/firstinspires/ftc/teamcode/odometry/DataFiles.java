package org.firstinspires.ftc.teamcode.odometry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataFiles {
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

    // Writing files function
    public void writeDataFile(String version) throws IOException {
        try {
            FileWriter graphData = new FileWriter("odometryMapData" + version + ".txt");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Update version file
    public void updateVersion(String version, FileWriter graphData) throws IOException {
        try {
            int versionIncrementInt = Integer.valueOf(version);
            versionIncrementInt ++;
            String versionIncrementString = Integer.toString(versionIncrementInt);
            graphData.write("/n" + versionIncrementString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

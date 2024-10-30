package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import vttp.batch5.sdf.task01.models.BikeEntry;

public class BikeFileProcessor {
    
    private ArrayList<BikeEntry> bikeEntries;

    public BikeFileProcessor() {
        bikeEntries = new ArrayList<>();
    }

    public void read(String csvPath) throws FileNotFoundException, IOException {

        // Note: Throw exceptions, for them to be handled in Main class
        FileReader fr = new FileReader(csvPath);
        BufferedReader br = new BufferedReader(fr);
        
        // Read first line of csv to skip the first line of headers
        br.readLine();

        while (true) {

            String record = br.readLine();
            
            // Stop reading at the end of the file
            if (record == null) {
                break;
            }
            
            // Split the records into the columns -> String array to use BikeEntry
            String[] cols = record.split(","); 
            
            bikeEntries.add(BikeEntry.toBikeEntry(cols));
        }

        // 
        br.close();
        fr.close();
    }

    public ArrayList<BikeEntry> getTopFive() {
        // Set to top five
        int topN = 5;

        List<BikeEntry> topEntries = this.bikeEntries.stream()
            // Sort in descending order
            .sorted((Comparator.comparing((BikeEntry b) -> b.getCasual() + b.getRegistered())).reversed())
            // Limit to top 5
            .limit(topN)
            // Collect to list
            .collect(Collectors.toList());

        // Transform back to ArrayList
        return new ArrayList<>(topEntries);
    }
}

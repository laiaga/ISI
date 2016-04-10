import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadCSV {
	private DBManager db;
	
	public ReadCSV(DBManager db){this.db = db;}
	
	public void read(String filename) {

		String csvFile = "datas/"+filename+".csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
			String[] tmp = line.split(cvsSplitBy);
			db.createTable(filename, new ArrayList<String>(Arrays.asList(tmp)));
			
			while ((line = br.readLine()) != null) {
				String[] values = line.split(cvsSplitBy);
				db.insert(filename, new ArrayList<String>(Arrays.asList(values)));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}


public class Main {

	
	public static void main(String[] args) {
		DBManager db = new DBManager();
		ReadExcel re = new ReadExcel(db);
		ReadCSV rc = new ReadCSV(db);
		
		re.readSome("prix_immobilier_2008", "2010Q2",4);
		re.readAll("statistiques_crimes_2014", 3);

		rc.read("possession_armes_2007");		
				
		db.terminate();
	}

}

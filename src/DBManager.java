import java.sql.*;
import java.util.ArrayList;

public class DBManager { 
   private Connection conn = null;
   private Statement stmt = null;
   
   public DBManager()
   {
	   try{
	      Class.forName("com.mysql.jdbc.Driver");
	
	      conn = DriverManager.getConnection("jdbc:mysql://localhost?&useSSL=false&" + "user=root&password=root");
	      stmt = conn.createStatement();
	      
	      String sql = "CREATE DATABASE IF NOT EXISTS ISI";
	      stmt.executeUpdate(sql);
	      
	      /*sql = "USE ISI";
	      stmt.executeUpdate(sql);*/
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }
   }
   
   public void terminate(){
	   try{
		   String sql = "DROP DATABASE ISI";
		   stmt.executeUpdate(sql);
		   if(stmt!=null)
			   stmt.close();
	   }catch(SQLException se2){}
	   try{
		   if(conn!=null)
			   conn.close();
	   }catch(SQLException se){
		   se.printStackTrace();
	   }
   }
   
   public void createTable(String tableName, ArrayList<String> columns) {
	  try{	     
	      String sql = "CREATE TABLE ISI." + tableName + " (";
	      String colName = null;
	      
	      for(int i=0 ; i<columns.size(); i++){
	    	  colName = columns.get(i);
	    	  if(i == 0){
	    		  sql+=colName;
		    	  sql+=" CHAR(20),\n";
	    	  }
	    	  else if(i==columns.size()-1){
	    		  sql+=colName;
		    	  sql+=" FLOAT)";
	    	  }
	    	  else{
	    		  sql+=colName;
		    	  sql+=" FLOAT,\n";
	    	  }
	      }
	      System.out.println(sql+"\n");
	      stmt.executeUpdate(sql);
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }
	}
   
   public void insert(String tableName, ArrayList<String> values){
	   try{
		   String sql = "INSERT INTO TABLE ISI."+tableName+" VALUES\n(";
		   String v;
		   for(int i=0 ; i<values.size() ; i++){
			   v = values.get(i);
			   if(i == 0){
		    		  sql+="\"";
		    		  sql+=v;
		    		  sql+="\",\n";
			   }
			   else if(i==values.size()-1){
		    		  sql+=v;
			    	  sql+=")";
			   }
			   else{
		    		  sql+=v;
		    		  sql+=",\n";
			   }
		   }
		   System.out.println(sql);
		   stmt.executeUpdate(sql);
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }
   }
}
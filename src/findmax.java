import java.io.*;
import java.util.*;

public class findmax {

	private static Vector toret ;

    public static Vector<String> getDescScores(){
    	BufferedReader reader= null;
		try {
			reader = new BufferedReader(new FileReader("SCOREHISTORY.DAT"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Map<Integer, String> map=new TreeMap<Integer, String>();
        String line="";
        try {
			while((line=reader.readLine())!=null){
			    //System.out.println(line);
			    map.put(Integer.parseInt(getField(line)),line);
			    //System.out.println((getField(line)).getClass().getName());
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        toret = new Vector(map.values());
        //FileWriter writer = new FileWriter("MAXSCOREHISTORY.DAT");
        //System.out.println();
        //System.out.println(toret.lastElement());
        
        /*for(String val : map.values()){
            writer.write(val);  
            writer.write('\n');
            toret.add(val);
        }
        writer.close();*/
    	return toret;
    }
    
    private static String getField(String line) {
        return line.split("\t")[2];//extract value you want to sort on
    }
}

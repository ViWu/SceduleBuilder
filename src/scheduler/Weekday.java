package scheduler;
import java.util.*;


	public enum Weekday {
	     MONDAY,TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;
	     
	     public static Weekday fromString(String s){
	    	 List<String> names = Arrays.asList("MONDAY","TUESDAY","WEDNESDAY","FRIDAY");
	    	 List<String> chars = Arrays.asList("M","T","W","F");
	    	 if(s.equalsIgnoreCase("r") || s.equalsIgnoreCase("thursday"))
	    		 return (THURSDAY);
	    	 for (int i=0;i < names.size();i++)
	    	    {
	    		 if ((names.get(i)).equalsIgnoreCase(s) || ((s).substring(0,1).equalsIgnoreCase(chars.get(i))) && s.length()==1)
	    			 return (Enum.valueOf(Weekday.class, names.get(i)));	 
	    	    }	   
	    	 throw  new IllegalArgumentException();
	     }

	     public String toshortName(){
	    	 List<String> chars = Arrays.asList("M","T","W","F");
	    	 if(!(this.name()).equals("THURSDAY")){
	    		 for (int i=0; i< chars.size();i++){
	    			 if((this.name()).substring(0,1).equals(chars.get(i)))
	    				 return chars.get(i);
	    	 		}
	    	 	}
	    	 return("R");
	     }
	   
	     public String toString(){
	    	 List<String> names = Arrays.asList("Monday","Tuesday","Wednesday","Friday");
	    	 for(int i=0;i<names.size();i++){
	    		 if((this.name()).equalsIgnoreCase(names.get(i)))     		 
	    			 return names.get(i);
	    	 }
	    	 return ("Thursday");
	     }
	 //===================================TEST=================================================
	     public static void main(String[] args){
	    	 String s = "f";
	    	 Weekday d = Weekday.THURSDAY;
	    	 Weekday m = d.fromString(s);
	    	 System.out.println("fromString: "+m.name());
	    	 String sh = d.toshortName();
	    	 String str = d.toString();
	    	 System.out.println("toString: "+str);
	    	 System.out.println("toShort: "+sh);
	     }
}

package scheduler;
import java.util.*;
import java.util.Comparator;

public class CourseCreditComparator implements Comparator<Course>{
	public CourseCreditComparator(){		
	}
	
	public int compare(Course course1,Course course2){
			if(course1.credits > course2.credits)
				return 1;
			else if(course1.credits < course2.credits)
				return -1;
			else{
				int compare = course1.name.compareTo(course2.name);
				return compare;	
			}
	
	}
	//===================================TEST=================================================
	public static void main(String[] args){
		Set<Weekday> ds = new HashSet<Weekday>();
		Set<Weekday> ds2 = new HashSet<Weekday>();
		ds.add(Weekday.MONDAY);
		ds.add(Weekday.WEDNESDAY);
		ds.add(Weekday.TUESDAY);
		ds2.add(Weekday.WEDNESDAY);
		ds2.add(Weekday.MONDAY);
		ds2.add(Weekday.TUESDAY);	
		Time t = new Time(12,30,true);
		Time t2 = new Time(1,50,true);
		Course math = new Course("Pyy1",4,ds,t,80);
		Course physics = new  Course("Pyy1",5,ds2,t,80);
		CourseCreditComparator n = new CourseCreditComparator();
		Comparator<Course> r = new CourseTimeComparator();
		int k= r.compare(math, physics);
		System.out.println("k is "+k);
    	if (k < 0)  
    	{  
    	    System.out.println(math.name+" is smaller");
    	} 
    	else if (k > 0)
    		   System.out.println(math.name+" is bigger");
        else 
    	     
        	System.out.println("both are equal");
	}
}
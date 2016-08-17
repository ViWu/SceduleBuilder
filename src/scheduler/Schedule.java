package scheduler;
import java.util.*;
import java.util.Comparator;

public class Schedule{
	Set<Course> schedule;
	
	public Schedule(){
		schedule = new HashSet<Course>();
	}
	
	public boolean add(Course course){
		Iterator<Course> it = schedule.iterator();
		boolean b;
		Course a;
		ScheduleConflictException s;
	 	while(it.hasNext()){
	 		a=it.next();
	 		b=course.conflictsWith(a);
	 		System.out.println(b);
	 		if(b==true){
	 			s = new ScheduleConflictException(course,a);
	 			return false;
	 		}
	 	}
		schedule.add(course);
		return true;
	}
	
	public Schedule clone(){
		Schedule s = new Schedule();
		Course element;
		Iterator<Course> it = schedule.iterator();
		while(it.hasNext()){
			element=it.next();
			s.schedule.add(element);
		}
		return s;
	}
	
	public Course getCourse(Weekday day, Time time){
		Iterator<Course> it = schedule.iterator();
		Course element;
	 	while(it.hasNext()){
	 		element=it.next();
	 		 if(element.contains(day,time))
	 			 return element;
	 	}
	 	return null;
	}
	
	public void remove(Weekday day,Time time){
		Iterator<Course> it = schedule.iterator();
		Course element,del=null;
		System.out.println("day is "+day.name());
	 	while(it.hasNext()){
	 		element=it.next();
	 		 if(element.contains(day,time))
	 			 del=element;
	 	}
	 	schedule.remove(del);
	}
	
	public int totalCredits(){
		Iterator<Course> it = schedule.iterator();
		int total=0;
		while(it.hasNext()){
			total = total+it.next().credits;
		}
		return total;
	}
	
 //===================================TEST=================================================
	 public static void main(String[] args){
		 Schedule schedule = new Schedule();
		 Set<Weekday> ds = new HashSet<Weekday>();
			Set<Weekday> ds2 = new HashSet<Weekday>();
			Weekday d3 = Weekday.MONDAY;
			Weekday d5 = Weekday.WEDNESDAY;
			String s;
			ds.add(Weekday.MONDAY);
			ds.add(Weekday.WEDNESDAY);
			ds.add(Weekday.TUESDAY);
			ds2.add(Weekday.WEDNESDAY);
			ds2.add(Weekday.MONDAY);
			ds2.add(Weekday.TUESDAY);	
			Time t = new Time(12,30,true);
			Time t2 = new Time(1,50,true);
			Time t3 = new Time(3,50,true);
			Course math = new Course("Math",4,ds,t,80);
			Course physics = new Course("Physics",5,ds2,t2,100);
			t = new Time(2,30,false);
			Course english = new Course("English",4,ds2,t,40);
			schedule.add(math);
			schedule.add(physics);
			schedule.add(english);
			schedule.remove(d3,t3);
			Schedule sch = schedule.clone();
			Iterator<Course> it = sch.schedule.iterator();
		 	while(it.hasNext()){
		 		 System.out.println("clone has "+it.next().name);
		 	}
		 	schedule.getCourse(d5,t3);
		 	int i = schedule.totalCredits();
		 	System.out.println("totla credits it "+i);
	 }
}
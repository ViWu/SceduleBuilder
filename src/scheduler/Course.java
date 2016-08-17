package scheduler;
import java.util.*;

public class Course{
	String name;
	int credits, duration;
	Time startTime;
	Set<Weekday> days = new HashSet<Weekday>();
	
	public Course(String n, int c, Set<Weekday> d,Time start, int dur){
		if((c<1 || c>5) || d.size()==0)
			throw new IllegalArgumentException();
		name=n;
		credits=c;
		startTime = start;
		days = d;
		duration = dur;
	}
	
	public boolean conflictsWith(Course c){
		Time thisEnd = this.getEndTime(); 
		Time cEnd = c.getEndTime();
		boolean check;
		System.out.println(this.name+", "+this.startTime+", "+thisEnd);
		System.out.println(c.name+", "+c.startTime+", "+cEnd);
		Iterator<Weekday> it = c.days.iterator();
		 while(it.hasNext()){
			 check = this.days.contains(it.next());
			 if(check == true){
				 if(cEnd.compareTo(thisEnd)==-1 && cEnd.compareTo(this.startTime)==1)		//if cEnd<thisEnd && cEnd > this.startTime
					 return true;
				 else if(thisEnd.compareTo(cEnd)==-1 && thisEnd.compareTo(c.startTime)==1)	//if thisEnd < cEnd && thisEnd > c.startTime
					 return true;
				 else if(c.startTime.compareTo(this.startTime)==0)				//if start times are equal, but end times equal is OK
					 return true;
				 else
					 return false;
			 }
		 }
		 return false;
	}
	
	public Time getEndTime(){
		Time thisEnd = startTime.shift(duration);
		return thisEnd;
	}
	
	public String toString(){
		String s = name;
		s=s.concat(",");
		s=s.concat(String.valueOf(credits));
		s=s.concat(",");
		Iterator<Weekday> it = days.iterator();
		while(it.hasNext()){
			s = s.concat(it.next().toshortName());
		}
		s=s.concat(",");
		s=s.concat(startTime.toString());
		s=s.concat(",");
		s=s.concat(String.valueOf(duration));
		return s;
	}
	
	public String getName(){
		return name;
	}
	public int getCredits(){
		return credits;
	}
	public int getDuration(){
		return duration;
	}
	public Time getStartTime(){
		return startTime;
	}
	
	public boolean contains(Weekday d, Time t){
		Time end = this.getEndTime(); 
		Iterator<Weekday> it = days.iterator();
		 while(it.hasNext()){
			 if (d.toshortName().equals(it.next().toshortName()))
			 {
				if((t.compareTo(startTime)==1 || t.compareTo(startTime)==0) && t.compareTo(end)==-1)
					 return true;
			 }
		 }
		 return false;
	}
	
	public boolean equals(Course o){
		if(name.equals(o.name) && credits == o.credits && startTime.compareTo(o.startTime)==0 && duration==o.duration){
			Iterator<Weekday> it = days.iterator();
		 	while(it.hasNext()){
		 		 if (!o.days.contains(it.next()))
		 			 return false;
		 	}
			return true;
		 }
		return false;
	}
	//=========================TESTING==============================
	public static void main(String[] args){
		Set<Weekday> ds = new HashSet<Weekday>();
		Set<Weekday> ds2 = new HashSet<Weekday>();
		Weekday d = Weekday.THURSDAY;
		Weekday d2 = Weekday.FRIDAY;
		Weekday d3 = Weekday.MONDAY;
		Weekday d4 = Weekday.TUESDAY;
		Weekday d5 = Weekday.WEDNESDAY;
		String s;
		ds.add(d3);
		ds.add(d5);
		ds.add(d4);
		ds2.add(d5);
		ds2.add(d3);
		ds2.add(d4);	
		Time t = new Time(12,30,true);
		Time t2 = new Time(1,49,true);
		Course math = new Course("Math",4,ds,t,80);
		Course physics = new Course("Physics",4,ds2,t,100);
		System.out.println(math.getName()+", "+math.getCredits()+", "+(math.getStartTime()).toString()+", "+math.getDuration());
		//System.out.println(physics.name+", "+physics.credits+", "+(physics.startTime).toString()+", "+physics.duration);
		math.conflictsWith(physics);
		Iterator<Weekday> it = physics.days.iterator();
		//System.out.println("Conflicts......");
	     boolean b = math.contains(d3,t2);
	     System.out.println(b);
		
	}
}
package scheduler;
import java.util.Comparator;

public class CourseTimeComparator implements Comparator<Course>{
	public CourseTimeComparator(){		
	}
	
	public int compare(Course course1,Course course2){
		if(course1.startTime.compareTo(course2.startTime)>0)
			return 1;
		else if(course1.startTime.compareTo(course2.startTime)<0)
			return -1;
		else{
			if(course1.duration > course2.duration)
				return 1;
			if(course1.duration < course2.duration)
				return -1;
			else{
				int compare = course1.name.compareTo(course2.name);
				return compare;
			}
		}
	}
}
package scheduler;
import java.util.Comparator;

public class CourseNameComparator implements Comparator<Course>{
	public CourseNameComparator(){	
	}
	
	public int compare(Course course1,Course course2){
        	int compare = course1.name.compareTo(course2.name);
		return compare;
	}
	
}
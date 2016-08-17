package scheduler;
import java.util.*;

public class ScheduleConflictException extends RuntimeException{
	public ScheduleConflictException(){		
	}
	public ScheduleConflictException(Course course1,Course course2){
		System.out.println(course1.name+" conflicts with "+course2.name);
	}

}
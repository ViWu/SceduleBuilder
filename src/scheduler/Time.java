package scheduler;
import java.util.*;


public class Time{
	int hour,minute;
	boolean pm;
	public Time(int h,int m, boolean p){
		if(h>12 || h<1)
			throw new IllegalArgumentException();
		if(m>59 || m<0)
			throw new IllegalArgumentException();
		hour = h;
		minute=m;
		pm=p;
	}
	
	public static Time fromString(String s){
		ArrayList<Integer> al = new ArrayList<Integer>();
		List<Character> chars = Arrays.asList(':',' ');
		char c='a';
		int count=0,count2=0,h=0,m=0;
		boolean p=false;
		for(int j=count;j<2;j++){
			while (c!=chars.get(j)) {
				c = s.charAt(count);
				//System.out.println(c);
				count++;
				if(c!=chars.get(j)){
					if((c-'0')>=0 &&(c-'0')<=9)
						al.add(c-'0');	
					else 
						throw new IllegalArgumentException();
				}
			}
			if(al.size()>2 ||al.size()==0)
				throw new IllegalArgumentException();
			//System.out.println("Original contents of al: ");
			for(int i=al.size()-1;i>=0;i--){
				//System.out.println(al.get(i));
				if(j==0)
					h=h+al.get(i)*(int)Math.pow(10,count2);
				if(j==1)
					m=m+al.get(i)*(int)Math.pow(10,count2);
				count2++;
			}
			al.clear();
			count2=0;
			//System.out.println("h is "+h);
			//System.out.println("m is "+m);
		}
			if(h<10 && h>0){
				if(s.charAt(4)!=' ')
					throw new IllegalArgumentException();
				else if(s.charAt(5) =='A' && s.charAt(6)=='M' && s.length()==7 && s.charAt(1)==':')
					p=false;
				else if(s.charAt(5) =='P' && s.charAt(6)=='M' && s.length()==7 && s.charAt(1)==':')
					p=true;
				else
					throw new IllegalArgumentException();
			}
			if(h>9 && h<13){
				if(s.charAt(5)!=' ')
					throw new IllegalArgumentException();
				else if(s.charAt(6) =='A' && s.charAt(7)=='M' && s.length()==8 && s.charAt(2)==':')
					p=false;
				else if(s.charAt(6) =='P' && s.charAt(7)=='M' && s.length()==8 && s.charAt(2)==':')
					p=true;
				else
					throw new IllegalArgumentException();
			}
			if(h>12 || m>59)
				throw new IllegalArgumentException();
			Time t = new Time(h,m,p);
			return t;
		//}
	}
	
	public int compareTo(Time t){
		if(this.pm!=false && t.pm==false)			//check if AM or PM
			return 1;
		else if(this.pm==false && t.pm!=false)
			return -1;	
		else {									//if both the same, check the hours, then minutes
			if(this.hour==12 && t.hour !=12)
				return -1;
			else if((this.hour!=12 && t.hour == 12) || this.hour > t.hour)
				return 1;
			else if(this.minute > t.minute && this.hour == t.hour)
				return 1;
			else if (this.minute == t.minute && this.hour == t.hour)
				return 0;
			else
				return -1;
		}
		
	}
	
	public Time clone(){
		Time t = new Time(this.hour,this.minute,this.pm);
		return t;
	}
	
	public boolean equals(Time o){
		if((this.pm!=false && o.pm != false) || (this.pm == false && o.pm ==false)){
			if((this.hour == o.hour) && (this.minute == o.minute))
				return true;
		}
		return false;
	}
	
	public String toString(){
		String str = "0";
		String h = String.valueOf(this.hour);
		String m = String.valueOf(this.minute);
		if (this.hour<10)
			str=str.concat(h);
		else
			str = h;
		str=str.concat(":");
		if(this.minute<10){
			str=str.concat("0");
			str=str.concat(m);
		}
		else
			str=str.concat(m);
		if(this.pm ==false)
			str=str.concat(" AM");
		else
			str=str.concat(" PM");	
		return str;
	}
	
	public int getHour(){
		return hour;
	}
	public int getMinute(){
		return minute;
	}
	
	public boolean isPM(){
		if (pm==false)
			return false;
		else
			return true;
	}
	
	public Time shift(int m){
		Time t = this.clone();
		//System.out.println("t is , "+t.toString());
		if(m<0)
			throw new IllegalArgumentException();
		while(m>0){
			//System.out.println("m is "+m+", this.hour is "+this.hour+", this.minute is "+this.minute);
			if(t.minute==59 && t.hour ==11){		//track am/pm
				if(t.pm==false)
					t.pm=true;
				else
					t.pm=false;
			}
			if((t.minute+1) >= 60){					//if new time goes into next hour
				t.minute=t.minute-60;
				t.hour=t.hour+1;
			}	
			t.minute=t.minute+1;					//add1 min each time
			if(t.hour>=13){							//if from 12 to 1
				t.hour = t.hour-12;
			}
			m--;
		}
		return t;
	}
	
	//=========================TESTING==============================
	public static void main(String[] args){
		Time t = new Time(1,30,true);
		String h = t.toString();
		System.out.println("before, "+h);
		Time t3 = t.shift(0);
		String j = t3.toString();
		System.out.println("after, "+j);
		for(int i=0;i<2885;i++){
			//System.out.println("Test #"+i);
			//System.out.println("before, "+h);
			t3 = t.shift(i);
			j=t3.toString();
			System.out.println("after, "+j);
			//System.out.println("===============");
			
		}
	}
}
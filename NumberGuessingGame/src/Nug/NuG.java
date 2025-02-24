package Nug;
import java.util.*;
class Number {
    
public static int user(){
       
    try (Scanner sc = new Scanner(System.in)) {
		System.out.print("Enter Your Number :- ");
		 int input = sc.nextInt();
		 return input;
	}
    }
public static void ac(int b,int user){
     byte count=0;
     
  while(user!=b){
    
  if( user<b && user>0) {
   System.out.println("Choose Bigger Number");
      user=Number.user();
      
  }
  else if(user>b && user<100) {
   System.out.println("Choose Smaller Number");
      user=Number.user();
      
  
  }
     else if(user>100 || user<0){
         System.out.println("Enter Number between 0 to 100");
         user=Number.user();
     }
  
 count++;
 } 
  System.out.println("Congratulations! You won");
        
  
  System.out.println("Steps to won game :- "+ count++);
 }
 }




public class NuG {
public static void main(String[] args) {
 Random a = new Random();
int b = a.nextInt(100);
 

 Number ac = new Number();
 Number user = new Number();
 int s = Number.user();
 Number.ac(b,s);
 System.out.println(b);
 }
}
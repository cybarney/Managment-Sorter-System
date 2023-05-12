// Assignment: 8
// Name: Collin Barney
// StudentID: 1221368187
// Lecture: T Th (1:30-2:45)
//Description: Sorts through the data when a sort is called, takes either the reviewRatingComparator or the reviewCuisineComparator based on which one the user wants to use.
import java.util.*;
public class Sorts{

	
	public static void sort(ArrayList<Restaurant> reviewList, Comparator<Restaurant> xComparator) {//Gets comparator from other class and then uses it's compare method.
		int min;
	
		for(int index=0;index<reviewList.size();index++) {
			min = index;
			
			for(int scan= index+1;scan<reviewList.size();scan++) {
				if(xComparator.compare(reviewList.get(scan),reviewList.get(min))<0) {
					
					swap(reviewList,min,index);
					}
				}
			}
		
		}
	public static void swap(ArrayList<Restaurant> reviewList, int index1, int index2) {//Swaps two restaurants if they need to be swapped.
		Restaurant temp = reviewList.get(index1);
		reviewList.get(index2);
		reviewList.get(index2).equals(temp);
		}
	}

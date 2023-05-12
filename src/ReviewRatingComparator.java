
//Description: Class that is used if user wants to sort by cuisine. sorts by Cuisine, star raying, and then restaurant location.
import java.util.*;
public class ReviewRatingComparator implements Comparator<Restaurant> {

	public int compare(Restaurant first, Restaurant second){
		int difference =0;
		//Checks if one restaurant has a higher star rating than the other, if so puts that as the dfference. 
		if (first.getStars()>second.getStars()) {
			difference = first.getStars()-second.getStars();
			}
		else if(second.getStars()>first.getStars()) {
			difference = first.getStars()-second.getStars();
		}
		//If star rating is identical goes on to order alphabetically by name
		else if(first.getRestaurantName().equals(second.getRestaurantName())==false){
			int placeHolderLength;
			
			
			if(first.getRestaurantName().length()-second.getRestaurantName().length()<0) {//Finds shorter lengths of the two to use as a placeholder value
				placeHolderLength=second.getRestaurantName().length();
			}else placeHolderLength = first.getRestaurantName().length();
			
			for(int i=0;i<placeHolderLength-1;i++) {
				difference = (int)first.getRestaurantName().charAt(i)-(int)second.getRestaurantName().charAt(i);
				if(difference!=0) break;
				
			}
		}else {//Sorts by restaurant Location
			for(int i=0;i<second.getLocation().length();i++) {
				difference = (int)first.getLocation().charAt(i)-(int)second.getLocation().charAt(i);
				if(difference !=0) break;
		}
	}
	return difference;
	}
	
}

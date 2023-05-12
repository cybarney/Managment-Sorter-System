
//Description: Compares two restaurants when user wants to sort by Cuisine, sorts by cuisine, pricerange, name, and then loaction in that order.
import java.util.*;
public class ReviewCuisineComparator implements Comparator<Restaurant> {

	public int compare(Restaurant first, Restaurant second) {
		int difference=0;
		
		
		if(first.getCuisine().getName().equals(second.getCuisine().getName())==false) {//Sorts by Cuisine Name
			if(first.getCuisine().getName().length()>second.getCuisine().getName().length()) {
				for(int i=0;i<first.getCuisine().getName().length();i++) {
					difference = (int)first.getCuisine().getName().charAt(i)-(int)second.getCuisine().getName().charAt(i);
					if(difference!=0) break;
				}
			}else {
				for(int i=0;i<second.getCuisine().getName().length();i++) {
					difference = (int)first.getCuisine().getName().charAt(i)-(int)second.getCuisine().getName().charAt(i);
					if(difference!=0) break;
				}
			}
		}
		
		else if(first.getPriceRange()!=second.getPriceRange()){//Sorts by price Range.
			difference = first.getPriceRange()-second.getPriceRange();
			
		}
		
		//if price range b/w the two restaurants is the same sorts by restaurant name.
		else if(first.getRestaurantName().equals(second.getRestaurantName())==false) {
			if(first.getRestaurantName().length()>second.getRestaurantName().length()) {
				for(int i=0;i<first.getRestaurantName().length();i++) {
					difference = (int)first.getRestaurantName().charAt(i)-(int)second.getRestaurantName().charAt(i);
					if(difference!=0) break;;
				}
			}else {
				for(int i=0;i<second.getRestaurantName().length();i++) {
					difference = (int)first.getRestaurantName().charAt(i)-(int)second.getRestaurantName().charAt(i);
					if(difference!=0) break;;
				}
			}
			
		}else {//Sorts by restaurant location
			if(first.getLocation().length()>second.getLocation().length()) {
				for(int i=0;i<first.getLocation().length();i++) {
					difference = (int)first.getLocation().charAt(i)-(int)second.getLocation().charAt(i);
					if(difference !=0) break;
				}
					
				
			}else {
				for(int i=0;i<second.getLocation().length();i++) {
					difference = (int)first.getLocation().charAt(i)-(int)second.getLocation().charAt(i);
					if(difference !=0) break;
			}
		}
		
	}
		return difference;

	}

}
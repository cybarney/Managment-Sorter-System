
// Description: ReviewManager class, has different methods that do different things to the list of reviews. Methods are called in main file.
import java.io.Serializable;
import java.util.ArrayList;
public class ReviewManager implements Serializable {
    // The serialVersionUID is used to verify compatibility of senders and
    // receivers. See the document for more details:
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html
    private static final long serialVersionUID = 205L;
    ArrayList<Restaurant> reviewList;
    	public ReviewManager() {
        reviewList = new ArrayList<>();
    }
    public boolean addReview(String restaurantName, int stars, String review, 
    		String priceRange, String cuisineName, String location, String signatureDish) {
        if (restaurantExists(restaurantName, location) == -1) {
            int price = priceRange.length();
            Cuisine newCuisine = new Cuisine(signatureDish, cuisineName);
            Restaurant newRestaurant = new Restaurant(restaurantName, stars, review, price, location, newCuisine);
            reviewList.add(newRestaurant);
            return true;
        }
        else return false;
    }
   public int restaurantExists(String name, String location) {//Checks reviewList to see if a restaurant with a certain name and location is in the list.
	   int existence=-1;
	   for(int i=0;i<reviewList.size();i++) {//Loops through the review list
		   if(reviewList.get(i).getRestaurantName().equals(name)&&reviewList.get(i).getLocation().equals(location)){
			   existence = i;
			   break;
		   }
	   
	   }
	   return existence;
   }
   public ArrayList<Integer> cuisineExists(String cuisineName){//Checks if a certain cuisine is in review list, makes an arraylist of all the indexes of where the cuisine is at/
	   
	   ArrayList<Integer> temp = new ArrayList<Integer>();
	   
	   
	   for(int i=0;i<reviewList.size();i++) {//Loops through reviewList looking for cuisine.
		   if(reviewList.get(i).getCuisine().getName().equals(cuisineName)) {
			   temp.add(i);
			   
			   
		   }
	   }
	   return temp;
   }
  
   public Restaurant getRestaurant(int number) {//Returns the restaurant at the specified index
	   return(reviewList.get(number));
   }
   
  
   public boolean removeReview(String name, String location) {//Removes review if location and name is matched, returns positive if so, returns negative if the review wanted doesn't exist.
	   boolean removeReviewBool = false;
	   for(int i=0;i<reviewList.size();i++) {
		   if(reviewList.get(i).getRestaurantName().equals(name)&&reviewList.get(i).getLocation().equals(location)) {//If review wanting to be remove is there will set the boolean equal to true and remove it.
			   reviewList.remove(i);
			   removeReviewBool = true;
			   break;
		   }else removeReviewBool = false;
	   }
	   return removeReviewBool;
   }
   
   public void sortByRating() {//Sorts the reviews by rating
	   ReviewRatingComparator rateCompare = new ReviewRatingComparator();
	   reviewList.sort(rateCompare);
   }
   public void sortByCuisine() {//Sorts the review by cuisine
	   ReviewCuisineComparator rateCuisine = new ReviewCuisineComparator();
	   reviewList.sort(rateCuisine);
   }
   public String listReviews() {//Lists all reviews in the review list
	   String reviewListString="";
	   if(reviewList.size()==0) System.out.print("No Reviews available\r\n");
	   else{for(int i=0;i<reviewList.size();i++) {
		   reviewListString = reviewListString + reviewList.get(i).toString();
	   	}
	   }
	   return reviewListString;
   }
   public void closeReviewManager() {//Clears reviewList
	   reviewList.clear();
   }
}


import java.io.Serializable;
public class Restaurant implements Serializable{
	private static final long serialVersionUID = 205L;
	private String restaurantName; 	
	private int stars;
	private String review;
	private int priceRange;
	private String location;
	private Cuisine cuisine;
		
	//Constructor for restaurant object
		
	
	public Restaurant(String restaurantName, int stars, String review, int priceRange, String location, Cuisine cuisine)	{
		this.restaurantName = restaurantName;
		this.stars=stars;
		this.review=review;
		this.priceRange=priceRange;
		this.location=location;
		this.cuisine=cuisine;
	}
	public String getRestaurantName() {//Returns the name of the restaurant
		return restaurantName;
	}
	public int getStars() {//Returns the ammount of stars of the restaurant
		return stars;
	}
	public int getPriceRange() {//Returns the price range of the restaurant
		return priceRange;
	}
	public String getLocation() {//Returns restaurant's location
		return location;
	}
	public Cuisine getCuisine() {
		return cuisine;
	}
	public String getReview() {//REturns review
		return review;
	}
	public String toString() {//Returns info on the restaurant
		String starsString = "";
		String priceRangeString = "";
		
		for(int i=0;i<getStars();i++) {//Loop to convert the stars into a string that can be used.
			starsString= starsString + "*";
		}
		for(int i=0;i<getPriceRange();i++) {//Loop to convert price range into a string that can be used.
			priceRangeString=priceRangeString + "$";
		}
		
		String info = (restaurantName + " restaurant\n" + starsString + "\t\t" + priceRangeString + "\n" +  cuisine.toString() + "Location: " + location + "\n" + "Review:\t" + review + "\n\n");
		return info;
	}
}

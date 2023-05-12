// Description: Main file, uses methods from reviewManager in order to do different things to a list of reviews. Able to read from files, write to files, as well as serialize/deserialize. User able to manually input/remove reviews as well as sort them by differing criteria. They are also able to search for certain restaurants/certain restaurants with a certain criteria.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
public class Assignment8 {
    public static void main(String[] args) {
        // Menu options
        char inputOpt = ' ';
        String inputLine;
        // Restaurant and Cuisine information
        String restaurantName, cuisineName;
        String review = null, location, signatureDish, priceRange;
        int rating;
        // Output information
        String outFilename, inFilename;
        String outMsg, inMsg;
        // Restaurant manager
        ReviewManager reviewManager = new ReviewManager();
        // Operation result
        boolean opResult;     
        
        try {
            printMenu();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader stdin = new BufferedReader(isr);
            do {
                System.out.print("\nWhat action would you like to perform?\n");
                inputLine = stdin.readLine().trim();
                if (inputLine.isEmpty()) {
                    continue;
                }
                inputOpt = inputLine.charAt(0);
                inputOpt = Character.toUpperCase(inputOpt);
                switch (inputOpt) {
                    case 'A': // Add a new Restaurant Review
                        System.out.print("Please enter the restaurant information:\n");
                        System.out.print("Enter the restaurant name:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.print("Enter the review:\n");
                        review = stdin.readLine().trim();
                        System.out.print("Enter the price range:\n");
                        priceRange = stdin.readLine().trim();
                        System.out.print("Enter the rating:\n");
                        rating = Integer.parseInt(stdin.readLine().trim());
                        System.out.print("Enter the cuisine name:\n");
                        cuisineName = stdin.readLine().trim();
                        System.out.print("Enter the location:\n");
                        location = stdin.readLine().trim();
                        System.out.print("Enter the signature dish\n");
                        signatureDish = stdin.readLine().trim();
                        
                        //If restaurant is added sucessfully will return restaurant added, if not will return that restaurant was not added.
          
                        if(reviewManager.addReview(restaurantName, rating, review, priceRange, cuisineName, location, signatureDish)==true){ 
                        	System.out.print("Restaurant added\n");
                        }else { System.out.print("Restaurant NOT added\n");}
                        break;

                    	
                    case 'D': // Search for a restaurant given the name and location
                        System.out.print("Please enter the restaurant name to search:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.print("Please enter the restaurant's location':\n");
                        location = stdin.readLine().trim();
                        int indexTemp = reviewManager.restaurantExists(restaurantName, location);
                        
                        if(reviewManager.restaurantExists(restaurantName, location)==-1) {
                        	System.out.print("Restaurant not found. Please try again\n");
                        }else {
                        	System.out.print("Restaurant found. Here's the review:\n");
                        	System.out.print(reviewManager.getRestaurant(indexTemp).getReview()+"\n");
                        	
                        }
                        	
                        
                        	
                        break;

                    case 'E': // Search for a cuisine
                        System.out.print("Please enter the cuisine name to search:\n");
                        cuisineName = stdin.readLine().trim();
                        ArrayList<Integer> tempCuisineList = reviewManager.cuisineExists(cuisineName);
                       
                       if(tempCuisineList.size()!=0) { 
                    	   System.out.print(tempCuisineList.size()+" Restaurants matching "+cuisineName+ " cuisine were found:\n");
                    	   for(int i=0;i<tempCuisineList.size();i++) {
                    		   System.out.println(reviewManager.getRestaurant(tempCuisineList.get(i)).toString());
                    		   
                    	   }
                       }else {
                    	   System.out.print("Cuisine: "+cuisineName+" was NOT found\n");
                       }
                       break;

                    case 'L': // List restaurant's reviews
                        System.out.print("\n" + reviewManager.listReviews() + "\n");
                        break;
                    case 'N': // Sorts the restaurant reviews by rating 
                    	System.out.print("sorted by rating\n");                   	
                    	reviewManager.sortByRating();
                    	break;
                    	
                    case 'P': //Sorts the restaurant reviews by cuisine name
                    	System.out.print("sorted by cuisine\n");
                    	reviewManager.sortByCuisine();
                    	break;
                    case 'Q': // Quit
                        break;
                    case 'R': // Remove a review
                        System.out.print("Please enter the restaurant name of the review to remove:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.print("Please enter the location to remove:\n");
                        location = stdin.readLine().trim();
                        
                        if(reviewManager.removeReview(restaurantName, location)) {//Checks if review is in reviewList
                        	System.out.print(restaurantName+", "+location+" was removed\n");
                        }else {
                        	System.out.print(restaurantName+", "+location+" was NOT removed\n");}
                        break;              
                        
                    case 'T': // Close reviewList
                        reviewManager.closeReviewManager();
                        System.out.print("Restaurant management system was reset\n");
                        break;
                    case 'U': // Write restaurant names and reviews to a text file
                        System.out.print("Please enter a file name that we will write to:\n");
                        outFilename = stdin.readLine().trim();
                        System.out.print("Please enter the name of the restaurant:\n");
                        restaurantName = stdin.readLine().trim();
                        System.out.println("Please enter a review to save locally:\n");
                        review = stdin.readLine().trim();
                        outMsg = restaurantName + "\n" + review + "\n";
                        
                        try {//Adds user inputted review for a restaurant
                        	FileWriter fw = new FileWriter (outFilename);
                        	BufferedWriter bw = new BufferedWriter(fw);
                        	PrintWriter outFile = new PrintWriter(bw);
                        	outFile.print(outMsg+"\n");
                        	System.out.print(outFilename+" is written\n");
                        	outFile.close();
                        }catch(IOException exception) {
                        	System.out.print("Write string in file erro\n");
                        }
                        break;                   
                    case 'V': // Read strings from a text file
                        System.out.print("Please enter a file name which we will read from:\n");
                        String line;
                        inFilename = stdin.readLine().trim();
                        try {//Reads from the file and displays the info
                        	FileReader fr = new FileReader(inFilename);
                        	BufferedReader inFile = new BufferedReader(fr);
                        	System.out.print(inFilename+" was read\nThe contents of the file are:\n");
                        	line = inFile.readLine();
                        	while(line!= null) {
                        		System.out.print(line+"\n");
                        		line=inFile.readLine();
                        	}
                        	inFile.close();
                        //Catches Ioexpcetion and file not found exception
                        }catch(FileNotFoundException exception){ 
                       		System.out.print(inFilename + " was not found\n");
                        }catch(IOException exception) {
                       		System.out.print("Read string from file error\n");
                        }	
                        
                       break;            
                    case 'W': // Serialize ReviewManager to a data file
                        System.out.print("Please enter a file name to write:\n");
                        outFilename = stdin.readLine().trim();
                        try {
                        	FileOutputStream bytesToDisk = new FileOutputStream(outFilename);
                        	ObjectOutputStream objectToBytes = new ObjectOutputStream(bytesToDisk);
                        	objectToBytes.writeObject(reviewManager);
                        	
                        	objectToBytes.close();
                        }catch(NotSerializableException exception) {
                        	System.out.print("Not serializable exception\n");
                        }catch(IOException exception) {
                        	System.out.print("Data file written exception");
                        }
                       break;
                    case 'X': // Deserialize ReviewManager from a data file
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();
                        FileInputStream diskToStreamOfBytes = new FileInputStream(inFilename);
                        ObjectInputStream ObjectToByte = new ObjectInputStream(diskToStreamOfBytes);
                        try {
                             reviewManager = (ReviewManager)ObjectToByte.readObject();                   
                             System.out.print(inFilename+" was read\n");
                             diskToStreamOfBytes.close();
                             ObjectToByte.close();
                             
                        }catch(ClassNotFoundException exception) {
                        	System.out.print("Class not found exception\n");
                        }catch(NotSerializableException exception) {
                        	System.out.print("Not serializable exception\n");
                        }catch(IOException exception) {
                        	System.out.print("Data file written exception");
                        }
                        break;
                    case '?': // Display help
                        printMenu();
                        break;
                    default:
                        System.out.print("Unknown action\n");
                        break;
                }
            } while (inputOpt != 'Q' || inputLine.length() != 1);
        }
        catch (IOException exception) {
            System.out.print("IO Exception\n");
        }
    }
    public static void printMenu() {
        System.out.println("Welcome to Kelp! ");
        System.out.println("Find or post reviews for your favorite (and not so favorite) restaurants.");
        System.out.print("Choice\t\tAction\n" + "------\t\t------\n" + "A\t\tAdd a review\n"
                + "D\t\tSearch for a restaurant\n" + "E\t\tSearch for a cuisine\n"
                + "L\t\tList all reviews\n" + "N\t\tSort by stars\n" + "P\t\tSort by cuisine\n"
                + "Q\t\tQuit\n" + "R\t\tRemove a review\n"
                + "U\t\tAdd personal review to a local file\n" + "V\t\tRetrieve personal review from a local file\n"
                + "W\t\tSave reviews to a file\n"
                + "X\t\tUpload reviews from a file\n"
                + "T\t\t(admin) reset database\n"
                + "?\t\tDisplay Help\n");
    }
}
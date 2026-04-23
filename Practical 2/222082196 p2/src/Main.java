/*
 * Total Marks Main Class: 14
 * Compilation & Correct Execution Marks: 10
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {
	private static String path = "list.dat";	
	
	/*
	 * Appends a new GroceryItem to the current binary file
	 * 7 Marks
	 */
	public static void writeGroceryItemToFile(GroceryItem item){
		//TODO: Complete
	    try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(path))))) {
			oos.writeObject(item);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Reads all the objects in the current binary file and loads them into a Single Linked List (SList)
	 * 7 Marks
	 */
	public static SList<GroceryItem> readGroceryItemsFromFile(){
		//TODO: Complete
		SList<GroceryItem> list = new SList<>();
		
		try(FileInputStream fileInputStream = new FileInputStream(new File(path))){
			while(fileInputStream.available() > 0) {
				ObjectInputStream ois = new ObjectInputStream(fileInputStream);
				GroceryItem item = (GroceryItem) ois.readObject();
				list.insertLast(item);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return list;	
	}
	
	public static void main(String[] args) {		
		String response = "";
		Scanner s = new Scanner(System.in);
		while (!response.toLowerCase().equals("quit")){
			System.out.println("==================================================");
			System.out.println("===\t\tCommand Line TODO\t\t==");
			System.out.println("==================================================\n");
			System.out.println("The current Todo List path is: "+path);
			System.out.println("Select option: ");
			System.out.println("1) Set path");
			System.out.println("2) Read and Display current Todo List");
			System.out.println("3) Write new Todo item to current Todo List");
			System.out.println("or \"quit\" to quit.");		
			response = s.nextLine();
			
			switch(response.toLowerCase()){
				case "1": {
					System.out.println("Enter path:");
					path = s.nextLine();
				}
					break;
				case "2": {
					SList<GroceryItem> list = readGroceryItemsFromFile();					
					System.out.println(list);
				}
					break;
				case "3":{
					try{
						GroceryItem pi = new GroceryItem();
						System.out.println("Enter grocery item's id: ");
						response =s.nextLine();
						pi.setId(Integer.parseInt(response));
						System.out.println("Enter grocery item's name: ");
						response = s.nextLine();
						pi.setName(response);
						System.out.println("Enter grocery item's priority: ");
						response = s.nextLine();
						pi.setPrice(Double.parseDouble(response));;
						System.out.println("Enter grocery item's description: ");
						response = s.nextLine();
						pi.setDescription(response);
						
						writeGroceryItemToFile(pi);
						System.out.println("grocery item added successfully");
					}
					catch(NumberFormatException nfe){
						System.err.println("Incorrect input provided.");
					}					
				}
					break;
				case "quit":
					break;
				default: System.out.println("Incorrect option selected. Please try again.");
			}			
		}
		s.close();
	}
}
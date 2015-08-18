package milech.poker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//TODO Create Adapter, Controller class responsible for printing data, gui etc.
public class Main {

	public static void main(String[] args) {
		Deal deal = new Deal(2);
		File file = new File("poker.txt");
		try {
			Scanner scanner = new Scanner(file);
//			while(scanner.hasNextLine()) 
			for(int i = 0; i < 10; i++){
				String inDeal = scanner.nextLine();
				try {
					deal.loadDeal(inDeal);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				deal.prtDeal();
				System.out.println();
				deal.sortHands();
				deal.prtDeal();
				System.out.println();
				System.out.println();
				//deal.getHand(0).prtHist();
				//System.out.printf("%-62s", "")
				//deal.getHand(1).prtHist();
				//evaluate deal and export outcome to new file
			}
			scanner.close();
		}
		catch(FileNotFoundException exception)
		{
			System.out.println("The file " + file.getPath() + " was not found.");
		}
		catch(IOException exception)
		{
			System.out.println(exception);
		}
	}
}

import java.util.Scanner;
//Created by Lex Whalen
public class App {

    public static void main(String[] args) 
    {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name subtitle folder's path:");
        String subFolder = scanner.nextLine();
        System.out.println("You entered: " + subFolder);
        System.out.println("Now enter the name of the new file: ");
        String newFileName = scanner.nextLine();
        System.out.println("Now creating a CSV titled " + newFileName + "...");
        MasterSub MS = new MasterSub(subFolder,newFileName);

        //MS.printHashMap();
        
        MS.createCSV();
        
    }

    
}


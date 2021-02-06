import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;
//kuromoji stuff
import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;

public class Subtitle 
{
    private String FileName;

    private ArrayList<String> linesList = new ArrayList<String>();

    //for getting the japanese tokens

    //constructor
    public Subtitle(String FileName)
    {
        this.FileName = FileName;
        ReadFile();
    }
    private void ReadFile()
    {
        try{
            File f = new File(this.FileName);
            Scanner scanner = new Scanner(f);
            Pattern pattern = Pattern.compile("[ぁ-んァ-ン一-龯]+");
            
            while(scanner.hasNextLine())
            {
                String data = scanner.nextLine();
                Tokenizer tokenizer = Tokenizer.builder().build();
                //parse the string, get only the japanese
                Matcher matcher = pattern.matcher(data);
                
                while(matcher.find())
                {
                    for(Token token: tokenizer.tokenize(matcher.group()))
                    {
                        String res = token.getBaseForm();
                        if(res!=null){
                            this.linesList.add(res);
                        }
                    }
                }
            }
            scanner.close();
        }catch(FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public ArrayList getLinesList()
    {
        return this.linesList;
    }

    public void printArrayList()
    {
        for(String i:this.linesList)
        {
            System.out.println(i);
        }
    }

}

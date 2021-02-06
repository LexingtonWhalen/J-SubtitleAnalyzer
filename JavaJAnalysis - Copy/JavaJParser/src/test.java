import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;
//kuromoji stuff
import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;
public class test {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[ぁ-んァ-ン一-龯]+");
        String data = "特製";
        Matcher matcher = pattern.matcher(data);
        boolean matchFound = matcher.find();
        if(matchFound){
            System.out.println(matcher.group(0));
        }
    }
}

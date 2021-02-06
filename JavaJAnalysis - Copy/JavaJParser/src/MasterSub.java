import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class MasterSub 
{
    private ArrayList<String> masterArray = new ArrayList<String>(); //all test from all subs in one string
    private HashMap<String,Integer> freqHash = new HashMap<String,Integer>();
    private String yourFileName;

    public MasterSub(String folderPath,String yourFileName)
    {
        this.yourFileName = yourFileName;
        File fldr = new File(folderPath);
        File[] fldrListing = fldr.listFiles();
        if(fldrListing != null)
        {
            for(File childFile: fldrListing)
            {
                Subtitle subFile = new Subtitle(String.valueOf(childFile));
                this.masterArray.addAll(subFile.getLinesList());
            }
        }
        putInHashMap();
        sortByValue();
    }
    private void putInHashMap()
    {
        for(String i:this.masterArray)
        {
            Integer count = this.freqHash.get(i);
            if(count ==null)
            {
                this.freqHash.put(i,1);
            }
            else
            {
                this.freqHash.put(i,count+1);
            }
        }
    }
    private void sortByValue()
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(this.freqHash.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                int i = o1.getValue().compareTo(o2.getValue()); 
                if(i != 0) return -i; //reverse sort
                return o1.getValue().compareTo(o2.getValue());
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        this.freqHash = temp;
    }

    public void printHashMap()
    {
        for (Map.Entry<String,Integer> entry:this.freqHash.entrySet())
        {
            System.out.println(entry.getKey()+ ":" + entry.getValue());
        }
    }

    public void createCSV()
    {
        String eol = System.getProperty("line.separator");
        try
        {
           try(Writer writer = new FileWriter(this.yourFileName+".csv"))
           {
               for(Map.Entry<String,Integer> entry: this.freqHash.entrySet())
               {
                   writer.append(entry.getKey())
                   .append(',')
                   .append((entry.getValue()).toString())
                   .append(eol);
               }
               
           }
        }
        catch(IOException ex)
        {
            ex.printStackTrace(System.err);
        }
    }
}

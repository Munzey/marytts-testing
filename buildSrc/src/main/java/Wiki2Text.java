
import java.io.*;

import info.bliki.wiki.filter.PlainTextConverter;
import info.bliki.wiki.model.WikiModel;

import org.apache.commons.io.FileUtils;

public class Wiki2Text {

    public String root_dir;
    public int page_id;
    
    public Wiki2Text(String area, String save_dir) {
        root_dir = save_dir + "/" + area;
        (new File(root_dir)).mkdirs();
        page_id = 1;
	}

    public void writeToFile(String wikiText)
    {   
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(root_dir + "/page_" + page_id + ".txt"));
            out.write(wikiText);
            out.close();
        }
        catch (IOException ex)
        {
            // FIXME: indicate the error!
        }
        finally
        {
            page_id++;
        }
    }
    
	public void getCleanText(File xml_file)
    {
        try
        {
        	WikiModel wikiModel = new WikiModel("http://www.mywiki.com/wiki/${image}", "http://www.mywiki.com/wiki/${title}");
            String wikiText = FileUtils.readFileToString(xml_file);
        	String plainStr = wikiModel.render(new PlainTextConverter(), wikiText);

            // Write to file
            writeToFile(plainStr);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } 		
	}
}

import java.io.*;

import info.bliki.wiki.filter.PlainTextConverter;
import info.bliki.wiki.model.WikiModel;

public class Wiki2Text {

	public String root_dir;
	public int page_id;

	public Wiki2Text(String area, String save_dir) {
		root_dir = save_dir + "/" + area + "/post";
		(new File(root_dir)).mkdirs();
		page_id = 1;
	}

	private void writeToFile(String wikiText) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(root_dir + "/page_" + page_id + ".txt"));
			out.write(wikiText);
			out.close();
		} catch (IOException ex) {
			System.err.println("Caught IOException: " + ex.getMessage());
		} finally {
			page_id++;
		}
	}

	public void getCleanText(String xml_page) {
			WikiModel wikiModel = new WikiModel("http://www.mywiki.com/wiki/${image}", "http://www.mywiki.com/wiki/${title}");
			String plainStr = wikiModel.render(new PlainTextConverter(), xml_page);

			writeToFile(plainStr);
	}
}

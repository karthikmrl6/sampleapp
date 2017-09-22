package JMapReducer.JMapReducer;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Page {
	
	//================================================================================
	// Constructor
	//================================================================================
		
	Page(WarcHTMLResponseRecord htmlRecord){
		
		// get our TREC ID and target URI
		pageID = htmlRecord.getTargetTrecID();
		pageUri= htmlRecord.getTargetURI();
		pageText = getTextFromPage(htmlRecord);
		uniqueTerms = frequencyCounter(pageText); 
		
		documentSize = Integer.parseInt(htmlRecord.getContentLength());
	}
	
	/***
	 * Counting the frequency of words in a given text
	 * 
	 */
	
	public Map<String, Integer> frequencyCounter(String txt){
		String words[] = null;
		// this is to keep the list sorted
		Map<String, Integer> wordCount = new TreeMap<String, Integer>();
		
		
         txt = txt.toLowerCase();
         words = txt.split("\\s+");                   
         
         for (String read : words) {
             Integer freq = wordCount.get(read);
             // it checks if the word exist in the wordCount. if I find duplicate, I just increase the frequency. otherwise, I just add the word
             wordCount.put(read, (freq == null) ? 1 : freq + 1); 
         }
         
         //System.out.println(wordCount.size() + " distinct words:");
         
         return wordCount;
		
	}

	/***
	 * Extract text from HTML file with the help of JSoup
	 * 
	 */
	
	public String getTextFromPage(WarcHTMLResponseRecord htmlRecord){
		
		String str=""; 
		Elements innerHtml = Jsoup.parse(htmlRecord.getRawRecord().getContentUTF8()).select("p");
		
		for(Element p : innerHtml){
	        str+=p.text();
	    } 
		
		return str+"\n"; 
	}
	
	
	//================================================================================
	// Methods: Setters, and Getter
	//================================================================================
		
	
	public String getPageID()
	{
		return pageID;
	}
	public String getPageText()
	{
		return pageText;
	}
	public String getPageUri()
	{
		return pageUri;
	}
	
	public int getDocumentSize()
	{
		return documentSize;
	}
	 
	public Map<String, Integer> getUniqueTerms()
	{
		return uniqueTerms;
	}
	
	public int getNbrOfUniqueTerms(){
		return uniqueTerms.size();
	}
	
	//================================================================================
	// Comparator
	//================================================================================
		
	public static Comparator<Page> pageComp = new Comparator<Page>() 
	{
		public int compare(Page a, Page b) 
		{
			return a.pageID.compareTo(b.pageID);
		}
	};

	//================================================================================
	// Private Members
	//================================================================================
			
	private String pageID;
	private String pageText;
	private String pageUri;
	private int documentSize;
	private Map<String, Integer> uniqueTerms;
		

}

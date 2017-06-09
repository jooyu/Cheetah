import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.FSDirectory;
import org.yujoo.baas.util.LuceneUtil;


public class test2 {

	
	public static void main(String[] args) {
		String queryString="2017";
		String index = "C://Users//eaves.zhu//Documents//GitHub//Cheetah//src//main//resources//index";
	    String field = "contents";
	    String queries =null;
	    int repeat = 0;
	    boolean raw = false;
	   List<String> fileNameList=new ArrayList<String>();
	    int hitsPerPage = 10;
	    try {
	    IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
	    IndexSearcher searcher = new IndexSearcher(reader);
	    Analyzer analyzer = new StandardAnalyzer();

	    BufferedReader in = null;
	    if (queries != null) {
	      in = Files.newBufferedReader(Paths.get(queries), StandardCharsets.UTF_8);
	    } else {
	      in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
	    }
	    QueryParser parser = new QueryParser(field, analyzer);

	      if (queries == null && queryString == null) {                        // prompt the user
	    	  fileNameList=null;
	      }
	      Query query = parser.parse(queryString);
	      LuceneUtil.doPagingSearch(in, searcher, query, hitsPerPage, raw, queries == null && queryString == null);

	    reader.close();
		
		} catch (Exception e) {
		
		}
		
	}
}

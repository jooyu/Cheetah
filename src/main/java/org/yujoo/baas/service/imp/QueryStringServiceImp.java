package org.yujoo.baas.service.imp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yujoo.baas.service.QueryStringService;
import org.yujoo.baas.util.LuceneUtil;

@Service
public class QueryStringServiceImp implements QueryStringService {

	private static Logger logger = LoggerFactory
			.getLogger(QueryStringServiceImp.class);
	@Value("${logfiles.index.folder}")
	public String indexPath;

	// 基于lucene的接口查询现有内存索引的结果
	@Override
	public List<String> getSearchResult(String queryString) {
		String index = indexPath;
		String field = "contents";
		String queries = null;

		boolean raw = false;
		List<String> fileNameList = new ArrayList<String>();
		int hitsPerPage = 10;
		try {
			IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths
					.get(index)));
			IndexSearcher searcher = new IndexSearcher(reader);
			Analyzer analyzer = new StandardAnalyzer();

			BufferedReader in = null;
			in = new BufferedReader(new InputStreamReader(System.in,
					StandardCharsets.UTF_8));
			QueryParser parser = new QueryParser(field, analyzer);

			if (queries == null && queryString == null) { // prompt the user
				fileNameList = null;
			}
			Query query = parser.parse(queryString);
			fileNameList = LuceneUtil.doPagingSearch(in, searcher, query,
					hitsPerPage, raw, queries == null && queryString == null);

			reader.close();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return fileNameList;
	}

}

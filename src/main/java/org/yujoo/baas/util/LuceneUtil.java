package org.yujoo.baas.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

/**
 * 
 * @author yu joo yujoo2008@gmail.com 2017年6月9日 上午11:48:42
 */
public class LuceneUtil {
	/*
	 * 该部分实现两个基本功能，建立索引和返回查询索引
	 */

	/**
	 * 创建索引
	 * 
	 * @param writer
	 * @param path
	 */
	public static void createIndex(final IndexWriter writer, Path path) {
		try {
			if (Files.isDirectory(path)) {
				Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file,
							BasicFileAttributes attrs) throws IOException {
						try {
							indexDoc(writer, file, attrs.lastModifiedTime()
									.toMillis());

						} catch (IOException ignore) {

						}
						return FileVisitResult.CONTINUE;
					}
				});
			} else {
				indexDoc(writer, path, Files.getLastModifiedTime(path)
						.toMillis());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/** 建立索引 */
	public static void indexDoc(IndexWriter writer, Path file, long lastModified)
			throws IOException {
		try (InputStream stream = Files.newInputStream(file)) {
			Document doc = new Document();

			Field pathField = new StringField("path", file.toString(),
					Field.Store.YES);
			doc.add(pathField);

			doc.add(new LongPoint("modified", lastModified));

			doc.add(new TextField("contents", new BufferedReader(
					new InputStreamReader(stream, StandardCharsets.UTF_8))));

			if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
				System.out.println("adding " + file);
				// 新增
				writer.addDocument(doc);
			} else {
				System.out.println("updating " + file);
				// 更新
				writer.updateDocument(new Term("path", file.toString()), doc);
			}
		}

	}

	/**
	 * 搜索结果索引
	 * 
	 * @param in
	 * @param searcher
	 * @param query
	 * @param hitsPerPage
	 * @param raw
	 * @param interactive
	 * @throws IOException
	 */
	public static List<String> doPagingSearch(BufferedReader in,
			IndexSearcher searcher, Query query, int hitsPerPage, boolean raw,
			boolean interactive) throws IOException {

		List<String> list = new ArrayList<String>();
		// Collect enough docs to show 5 pages 收集数据的结果
		TopDocs results = searcher.search(query, 5 * hitsPerPage);

		ScoreDoc[] scoreDocs = results.scoreDocs;

		int numTotalHits = results.totalHits;
		System.out.println(numTotalHits + " total matching documents");

		// 遍历scoreDocs，
		if (scoreDocs != null && scoreDocs.length > 0) {
			for (int i = 0; i < scoreDocs.length; i++) {
				ScoreDoc scoreDoc = scoreDocs[i];

				// 获取检索出的记录在索引库中的唯一编号，根据这个编号就可以获取需要的数据
				int doc = scoreDoc.doc;
				// IndexSearcher的doc方法可以通过刚获取的唯一编号从索引库中获取我们需要的数据
				Document document = searcher.doc(doc);
				System.out.println(document.get("path"));
				// 获取了Document对象，还需要将Document对象转成Article对象。
				list.add(document.get("path"));
			}
		}
		return list;

	}
	
	
	 private final static Analyzer analyzer = new StandardAnalyzer(); 
	    private final static Formatter highlighter_formatter = new SimpleHTMLFormatter("<span class=\"\" style=\"background-color:#FF0000\">","</span>");
	    /**
	     * 对一段文本执行语法高亮处理
	     * @param text 要处理高亮的文本
	     * @param key 高亮的关键字
	     * @return 返回格式化后的HTML文本
	     */
	    public static String highlight(String text, String key) {
	        if(StringUtils.isBlank(key) || StringUtils.isBlank(text))
	            return text;
	        String result = null;
	        try {
	            key = QueryParser.escape(key.trim().toLowerCase());
	            QueryScorer scorer = new QueryScorer(new TermQuery(new Term(null,QueryParser.escape(key))));
	            Highlighter hig = new Highlighter(highlighter_formatter, scorer);

	            TokenStream tokens = analyzer.tokenStream("", new StringReader( text)); 
	            result = hig.getBestFragment(tokens, text);
	            System.out.println("result"+result);
	        } catch (Exception e) {
	            System.out.println("Unabled to hightlight text");
	        }
	        return (result != null)?result:text;
	    }
	
}

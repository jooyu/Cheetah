import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.yujoo.baas.service.imp.CreateIndexDocsServiceImp;
import org.yujoo.baas.util.LuceneUtil;


public class test {


	private static Logger logger = LoggerFactory
			.getLogger(CreateIndexDocsServiceImp.class);

	public static void main(String[] args) {
		String indexPath="C://Users//eaves.zhu//Documents//GitHub//Cheetah//src//main//resources//index";
		String docsPath ="C://Users//eaves.zhu//Documents//GitHub//Cheetah//src//main//resources//log";
		boolean create = true;
		final Path docDir = Paths.get(docsPath);
		if (!Files.isReadable(docDir)) {
			logger.error("Document directory '"
					+ docDir.toAbsolutePath()
					+ "' does not exist or is not readable, please check the path");
			System.exit(1);
		}

		Date start = new Date();
		try {
			logger.debug("Indexing to directory '" + indexPath + "'...");

			Directory dir = FSDirectory.open(Paths.get(indexPath));
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

			if (create) {
				iwc.setOpenMode(OpenMode.CREATE);
			} else {
				iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
			}

		//设置index内存大小
			iwc.setRAMBufferSizeMB(256.0);
			IndexWriter writer = new IndexWriter(dir, iwc);
			LuceneUtil.createIndex(writer, docDir);


			writer.close();

			Date end = new Date();
			logger.debug(end.getTime() - start.getTime()
					+ " total milliseconds");

		} catch (IOException e) {
		logger.error(" caught a " + e.getClass()
					+ "\n with message: " + e.getMessage());
		}
	}

}

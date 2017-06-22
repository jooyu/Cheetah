package org.yujoo.baas.service;

import java.util.List;


public interface ElasticsearchService {

	public List<String> getMemberSeq(String queryString);
}

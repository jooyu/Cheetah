# Cheetah
关于索引方面的研究和摸索

2017.6.13 TODO 完善索引搜索优化和相关使用文档<br>
2017.6.15 TODO 完成项目基本说明<br>
2017.6.16 TODO 完善索引建立优化<br>

# 项目背景
针对相对相对中小型的的项目，在线检索日志的一些信息分析定位问题是项目中常见的场景，该项目取自实际工程场景。
之前考察过flume+kafka+storm+elasticsearch,针对非大数据量场景显得有些笨重
该项目基于lucene实现日志搜索，利用lucene的倒排索引机制定时按每日生成的log文件构建查询索引，结合springboot+freeMarker+bootstrap构建
前端查询的应用和展示，简单方便用于搜索历史日志（非实时，我们的场景日志是按日期每日生成）

# 搜索文件类型不局限于日志（.html/.log/.txt等）
使用方法：

1.将你要检索的文件放入文件夹log下面<br>
2.启动CheetSpringBoot -> run as application<br>
3.访问http://127.0.0.1/search/query 搜索你需要的内容<br>


使用方法:<br>
修改config文件夹下application-dev.properties<br>
logfiles.default.folder=src/main/resources/log<br>
logfiles.index.folder=src/main/resources/index<br>
改为指定目录<br>
运行项目mvn spring-boot:run<br>
浏览器查询：http://127.0.0.1:8080/search/query

----------------------------------------------------------------------------------------------------------------------------------------

# 新增elasticsearch查询couchbase接口Demo
elasticsearch是基于apache lucene的分布式全文搜索引擎，elk（elasticsearch+Logstash+Kinaba）是常用日志数据搜集，搜索，展示的架构<br>
这里使用的数据源是couchbase，couchbase提供了传输给elasticsearch的connector：elasticsearch-transport-couchbase<br>
展示部分这里我们做的定制化需求，使用springboot+freemaker自己开发<br>
# 1.这里我推荐使用couchbase4.5+elasticsearch2.4.0+elasticsearch-transport-couchbase-2.2.4.0-update1.zip<br>
couchbase：<br>
elasticsearch2.4.0：https://download.elastic.co/elasticsearch/elasticsearch/elasticsearch-2.4.0.deb<br>
elasticsearch-transport-couchbase-2.2.4.0-update1.zip： <br>
https://github.com/couchbaselabs/elasticsearch-transport-couchbase/releases/download/2.2.4.0-update1/elasticsearch-transport-couchbase-2.2.4.0-update1.zip<br>

当然你可以尝试其他版本<br>
https://github.com/couchbaselabs/elasticsearch-transport-couchbase<br>

# 2.elasticsearch建议离线安装插件，<br>
例子命令：<br>
cd /elasticsearch<br>
bin/plugin install file:///xxx/xxx/elasticsearch-transport-couchbase-2.2.4.0.zip<br>
安装完成就可以了，若要看详细文档请移步https://developer.couchbase.com/documentation/server/4.6/connectors/elasticsearch-2.2/install-and-config.html<br>
其他插件也一样安装<br>

# 3.启动bin/elasticsearch -Des.insecure.allow.root=true<br>
curl -XPUT http://localhost:9200/beer-sample  <br>
返回{"acknowledged":true}<br>
连接coucubase需要配置elasticsearch.yml <br>
apt-get install emacs<br>
emacs /etc/elasticsearch/elasticsearch.yml<br>
最后一行添加<br>
couchbase.password: [password]<br>
couchbase.username: [username]<br>
couchbase.maxConcurrentRequests: 1024<br>
重新启动<br>

# 4.couchbase数据复制到elasticsearch做实时搜索<br>
详细步骤https://developer.couchbase.com/documentation/server/4.6/connectors/elasticsearch-2.2/getting-started.html<br>

5.构建完成以后可以使用elasticsearch的api实现全文查询<br>
例子：http://127.0.0.1:9200/beer-sample/_search?q=2684142<br>
运行项目：http://127.0.0.1:8080esearch/query可实现可视化查询界面和结果展示<br>


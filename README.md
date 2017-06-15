# Cheetah
关于索引方面的研究和摸索

2017.6.13 TODO 完善索引搜索优化和相关使用文档
2017.6.15 TODO 完成项目基本说明
2017.6.16 TODO 完善索引建立优化

# Project Scene
针对相对相对中小型的的项目，在线检索日志的一些信息分析定位问题是项目中常见的场景，该项目取自实际工程场景。
之前考察过flume+kafka+storm+elasticsearch,针对非大数据量场景显得有些笨重
该项目基于lucene实现日志搜索，利用lucene的倒排索引机制定时按每日生成的log文件构建查询索引，结合springboot+freeMarker+bootstrap构建
前端查询的应用和展示，简单方便用于搜索历史日志（非实时，我们的场景日志是按日期每日生成）


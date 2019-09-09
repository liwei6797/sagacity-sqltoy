package org.sagacity.sqltoy.config;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sagacity.sqltoy.config.model.SqlToyConfig;
import org.sagacity.sqltoy.plugin.IFunction;

/**
 * @project sqltoy-orm
 * @description 用于检测sql文件内容发生变化,如果发生变化则重新加载文件
 * @author zhongxuchen
 * @version v1.0, Date:2019年8月26日
 * @modify 2019年8月26日,初始创建
 */
public class SqlFileModifyWatcher extends Thread {
	/**
	 * 定义全局日志
	 */
	private final static Logger logger = LogManager.getLogger(SqlFileModifyWatcher.class);

	private ConcurrentHashMap<String, SqlToyConfig> sqlCache;
	private ConcurrentHashMap filesLastModifyMap;
	private List realSqlList;

	/**
	 * 数据库类型
	 */
	private String dialect;

	/**
	 * xml解析格式
	 */
	private String encoding;

	/**
	 * 检测频率
	 */
	private int sleepSeconds = 1;

	private boolean debug = false;

	private int delayCheckSeconds;

	private List<IFunction> functionConverts;

	public SqlFileModifyWatcher(ConcurrentHashMap<String, SqlToyConfig> sqlCache, ConcurrentHashMap filesLastModifyMap,
			List realSqlList, List<IFunction> functionConverts, String dialect, String encoding, int delayCheckSeconds,
			int sleepSeconds, boolean debug) {
		this.sqlCache = sqlCache;
		this.realSqlList = realSqlList;
		this.functionConverts = functionConverts;
		this.dialect = dialect;
		this.encoding = encoding;
		this.filesLastModifyMap = filesLastModifyMap;
		this.delayCheckSeconds = delayCheckSeconds;
		this.sleepSeconds = (sleepSeconds >= 1) ? sleepSeconds : 1;
		this.debug = debug;
	}

	@Override
	public void run() {
		// 延时
		try {
			if (delayCheckSeconds >= 1) {
				Thread.sleep(1000 * delayCheckSeconds);
			}
		} catch (InterruptedException e) {
		}
		boolean isRun = true;
		while (isRun) {
			if (debug) {
				System.out.println("检测sql文件是否发生变更!");
			}
			try {
				SqlXMLConfigParse.parseXML(realSqlList, filesLastModifyMap, sqlCache, encoding, dialect,
						functionConverts);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("debug 模式下重新解析SQL对应的xml文件错误!{}", e.getMessage(), e);
			}
			try {
				// 一秒钟监测一次
				Thread.sleep(1000 * sleepSeconds);
			} catch (InterruptedException e) {
				logger.warn("sql文件变更监测程序进程异常,监测将终止!{}", e.getMessage(), e);
				isRun = false;
			}
		}

	}

}

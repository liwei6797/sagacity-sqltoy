/**
 * 
 */
package org.sagacity.quickvo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.sagacity.quickvo.model.ColumnTypeMapping;
import org.sagacity.quickvo.utils.DBUtil;
import org.sagacity.quickvo.utils.FileUtil;
import org.sagacity.quickvo.utils.StringUtil;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

/**
 * @project sagacity-quickvo
 * @description quickVO涉及的常量定义
 * @author chenrenfei <a href="mailto:zhongxuchen@hotmail.com">联系作者</a>
 * @version id:QuickVOConstants.java,Revision:v1.0,Date:Apr 18, 2009 4:54:22 PM
 */
@SuppressWarnings({ "rawtypes" })
public class QuickVOConstants implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8594672495773042796L;

	/**
	 * java vo freemarker模板
	 */
	public static String voTempate = "org/sagacity/quickvo/quickvo.ftl";

	/**
	 * java vo 抽象类freemarker模板
	 */
	public static String voAbstractTempate = "org/sagacity/quickvo/abstract-quickvo.ftl";

	/**
	 * 
	 */
	public static String daoTempate = "org/sagacity/quickvo/dao.ftl";

	/**
	 * vo中构造函数模板，用于当数据库表发生改变后修改vo中的构造函数
	 */
	public static String voConstructorTemplate = "org/sagacity/quickvo/quickvo-constructor.ftl";

	/**
	 * 
	 */
	public static String minStruct = "org/sagacity/quickvo/quickvo-minstruct.ftl";
	/**
	 * 
	 */
	public static String pkStruct = "org/sagacity/quickvo/quickvo-pkstruct.ftl";

	/**
	 * 
	 */
	public static String maxStruct = "org/sagacity/quickvo/quickvo-maxstruct.ftl";

	/**
	 * 
	 */
	public static String constructor = "org/sagacity/quickvo/quickvo-constructor.ftl";

	/**
	 * 主键默认生成策略
	 */
	public static String PK_DEFAULT_GENERATOR = "org.sagacity.sqltoy.plugins.id.DefaultIdGenerator";

	/**
	 * uuid主键策略
	 */
	public static String PK_UUID_GENERATOR = "org.sagacity.sqltoy.plugins.id.UUIDGenerator";

	/**
	 * twitter的雪花id算法
	 */
	public static String PK_SNOWFLAKE_GENERATOR = "org.sagacity.sqltoy.plugins.id.SnowflakeIdGenerator";

	/**
	 * 26位纳秒时序ID产生策略
	 */
	public static String PK_NANOTIME_ID_GENERATOR = "org.sagacity.sqltoy.plugins.id.NanoTimeIdGenerator";

	/**
	 * 基于redis产生id
	 */
	public static String PK_REDIS_ID_GENERATOR = "org.sagacity.sqltoy.plugins.id.RedisIdGenerator";

	public static String constructorBegin = "/*---begin-constructor-area---don't-update-this-area--*/";
	public static String constructorEnd = "/*---end-constructor-area---don't-update-this-area--*/";
	public static String pkStructRegs = "\\/[\\*]{1,2}\\s*pk\\s+constructor\\s*\\*\\/";

	public static String minStructRegs = "\\/[\\*]{1,2}\\s*minimal\\s+constructor\\s*\\*\\/";
	public static String maxStructRegs = "\\/[\\*]{1,2}\\s*full\\s+constructor\\s*\\*\\/";
	/**
	 * 运行时默认路径
	 */
	public static String BASE_LOCATE;

	public static String QUICK_CONFIG_FILE = "quickvo.xml";

	private static final String GLOBA_IDENTITY_NAME = "globa.identity";

	private static final String GLOBA_IDENTITY = "##{globa.identity}";

	/**
	 * 默认数据类型匹配关系定义
	 */
	public static final String[][] jdbcTypMapping = {
			// jdbc.type java.type importType precision(数据长度) scale(小数位)
			{ "REAL", "Float", "" }, { "TINYINT", "Integer", "" }, { "SHORT", "Short", "" },
			{ "SMALLINT", "Integer", "" }, { "MEDIUMINT", "Integer", "" },
			{ "BIGSERIAL", "BigInteger", "java.math.BigInteger" }, { "SERIAL8", "BigInteger", "java.math.BigInteger" },
			{ "BIGINT", "BigInteger", "java.math.BigInteger" }, { "INT", "Integer", "" }, { "INTEGER", "Integer", "" },
			{ "INT2", "Integer", "" }, { "INT4", "Integer", "" }, { "Int8", "Integer", "" }, { "Int16", "Integer", "" },
			{ "Int32", "Long", "" }, { "Int64", "BigInteger", "java.math.BigInteger" }, { "Enum8", "Integer", "" },
			{ "Enum16", "Integer", "" }, { "UInt8", "Integer", "" }, { "UInt16", "Integer", "" },
			{ "UInt32", "Long", "" }, { "UInt64", "BigInteger", "java.math.BigInteger" }, { "SERIAL", "Integer", "" },
			{ "FLOAT", "Float", "" }, { "FLOAT4", "Float", "" }, { "FLOAT8", "Float", "" }, { "FLOAT32", "Float", "" },
			{ "FLOAT64", "Double", "" }, { "DOUBLE", "Double", "" }, { "NUMBER", "BigDecimal", "java.math.BigDecimal" },
			{ "NUMERIC", "BigDecimal", "java.math.BigDecimal" }, { "DECIMAL", "BigDecimal", "java.math.BigDecimal" },
			{ "TIMESTAMP", "Timestamp", "java.sql.Timestamp" }, { "TIMESTAMP(6)", "Timestamp", "java.sql.Timestamp" },
			{ "BIGDECIMAL", "BigDecimal", "java.math.BigDecimal" }, { "DATE", "LocalDate", "java.time.LocalDate" },
			{ "DATETIME", "LocalDateTime", "java.time.LocalDateTime" }, { "TIME", "LocalTime", "java.time.LocalTime" },
			{ "YEAR", "LocalDate", "java.time.LocalDate" }, { "VARCHAR", "String", "" }, { "MEDIUMTEXT", "String", "" },
			{ "VARCHAR2", "String", "" }, { "LONG VARCHAR", "String", "" }, { "LONGVARCHAR", "String", "" },
			{ "LONGNVARCHAR", "String", "" }, { "NCHAR", "String", "" }, { "JSON", "String", "" },
			{ "STRING", "String", "" }, { "FixedSTRING", "String", "" }, { "CHAR", "String", "" },
			{ "BPCHAR", "String", "" }, { "CHARACTER", "String", "" }, { "BIT", "Boolean", "" },
			{ "BOOLEAN", "Boolean", "" }, { "BOOL", "Boolean", "" }, { "Clob", "String", "java.sql.Clob" },
			{ "NCLOB", "String", "java.sql.Clob" }, { "CLOB", "String", "oracle.sql.CLOB", "oracle" },
			{ "BLOB", "byte[]", "oracle.sql.BLOB", "oracle" }, { "Blob", "byte[]", "java.sql.Blob" },
			{ "LONGBLOB", "byte[]", "java.sql.Blob" }, { "MEDIUMBLOB", "byte[]", "java.sql.Blob" },
			{ "TEXT", "String", "" }, { "LONGTEXT", "String", "" }, { "TINYTEXT", "String" },
			{ "LONG VARGRAPHIC", "String", "" }, { "LONG VARCHAR", "String", "" }, { "IMAGE", "byte[]", "" },
			{ "VARBINARY", "byte[]", "" }, { "BINARY", "byte[]", "" }, { "BYTEA", "byte[]", "" },
			{ "LONGVARBINARY", "byte[]", "" } };

	/**
	 * 原始类型
	 */
	public static final String[][] prototype = { { "int", "1" }, { "short", "1" }, { "long", "1" }, { "float", "1" },
			{ "double", "1" }, { "char", "2" }, { "byte", "2" }, { "boolean", "2" } };
	// native type 对应java.sql.Types.xxxx
	public static final String[][] jdbcAry = { { "REAL", "REAL" }, { "YEAR", "DATE" }, { "DateTime", "DATE" },
			{ "Int", "INTEGER" }, { "Int2", "INTEGER" }, { "Int4", "INTEGER" }, { "Int8", "INTEGER" },
			{ "SMALLINT", "SMALLINT" }, { "MEDIUMINT", "INTEGER", "" }, { "Int16", "INTEGER" }, { "Int32", "INTEGER" },
			{ "Int64", "BIGINT" }, { "SERIAL8", "BIGINT" }, { "BIGSERIAL", "BIGINT" }, { "Enum8", "INTEGER" },
			{ "Enum16", "INTEGER" }, { "UInt8", "INTEGER" }, { "UInt16", "INTEGER" }, { "UInt32", "INTEGER" },
			{ "UInt64", "BIGINT" }, { "FLOAT4", "FLOAT" }, { "FLOAT8", "FLOAT" }, { "FLOAT32", "FLOAT" },
			{ "FLOAT64", "DOUBLE" }, { "STRING", "VARCHAR" }, { "FixedSTRING", "VARCHAR" },
			{ "LONG VARGRAPHIC", "CLOB" }, { "LONG VARCHAR", "VARCHAR" }, { "DATE", "DATE" }, { "DATETIME", "DATE" },
			{ "TIMESTAMP", "TIMESTAMP" }, { "TIMESTAMP(6)", "TIMESTAMP" }, { "TIME", "TIME" }, { "CHAR", "CHAR" },
			{ "CLOB", "CLOB" }, { "BLOB", "BLOB" }, { "BINARY", "BINARY" }, { "VARBINARY", "BINARY" },
			{ "LONGVARBINARY", "BINARY" }, { "BYTEA", "BINARY" }, { "LONGBLOB", "BLOB" }, { "BOOLEAN", "BOOLEAN" },
			{ "BOOL", "BOOLEAN" }, { "MEDIUMBLOB", "BLOB" }, { "LONGTEXT", "VARCHAR" }, { "MEDIUMTEXT", "VARCHAR" },
			{ "TEXT", "VARCHAR" }, { "JSON", "VARCHAR" }, { "TINYTEXT", "VARCHAR" }, { "VARCHAR", "VARCHAR" },
			{ "BPCHAR", "VARCHAR" }, { "VARCHAR2", "VARCHAR" }, { "TINYINT", "TINYINT" }, { "INT", "INTEGER" },
			{ "INTEGER", "INTEGER" }, { "BIGINT", "BIGINT" }, { "BIT", "BIT" }, { "NUMBER", "DECIMAL" },
			{ "DECIMAL", "DECIMAL" }, { "NUMERIC", "DECIMAL" }, { "IMAGE", "LONGVARBINARY" } };

	/**
	 * 全局常量map
	 */
	private static HashMap<String, String> constantMap = new HashMap<String, String>();

	public static int getMaxScale() {
		String maxScale = getKeyValue("max.scale.length");
		if (maxScale == null)
			return -1;
		return Integer.parseInt(maxScale);
	}

	/**
	 * @todo 加载xml中的参数
	 * @param paramElts
	 * @throws Exception
	 */
	public static void loadProperties(NodeList paramElts) throws Exception {
		String guid = System.getProperty(GLOBA_IDENTITY_NAME);
		if (guid == null)
			guid = "";
		// 加载任务配置文件中的参数
		if (paramElts != null && paramElts.getLength() > 0) {
			Element elt;
			for (int i = 0; i < paramElts.getLength(); i++) {
				elt = (Element) paramElts.item(i);
				if (elt.hasAttribute("name")) {
					if (elt.hasAttribute("value")) {
						constantMap.put(elt.getAttribute("name"), replaceConstants(
								StringUtil.replaceAllStr(elt.getAttribute("value"), GLOBA_IDENTITY, guid)));
					} else {
						constantMap.put(elt.getAttribute("name"), replaceConstants(
								StringUtil.replaceAllStr(StringUtil.trim(elt.getTextContent()), GLOBA_IDENTITY, guid)));
					}
				} else if (elt.hasAttribute("file")) {
					loadPropertyFile(
							replaceConstants(StringUtil.replaceAllStr(elt.getAttribute("file"), GLOBA_IDENTITY, guid)));
				}
			}
		}
	}

	/**
	 * @todo 替换常量参数
	 * @param target
	 * @return
	 */
	public static String replaceConstants(String target) {
		if (constantMap == null || constantMap.size() < 1 || target == null)
			return target;
		String result = target;
		// 简化匹配规则
		if (StringUtil.matches(result, "\\$\\{") && StringUtil.matches(result, "\\}")) {
			Iterator iter = constantMap.entrySet().iterator();
			Map.Entry entry;
			String key;
			String value;
			while (iter.hasNext()) {
				entry = (Map.Entry) iter.next();
				key = "${" + entry.getKey() + "}";
				value = (String) entry.getValue();
				result = StringUtil.replaceAllStr(result, key, value);
			}
		}
		return result;
	}

	/**
	 * @todo 加载properties文件
	 * @param propertyFile
	 * @throws IOException
	 */
	private static void loadPropertyFile(String propertyFile) throws Exception {
		if (StringUtil.isNotBlank(propertyFile)) {
			File propFile;
			// 判断根路径
			if (FileUtil.isRootPath(propertyFile)) {
				propFile = new File(propertyFile);
			} else {
				propFile = new File(FileUtil.skipPath(QuickVOConstants.BASE_LOCATE, propertyFile));
			}
			if (!propFile.exists()) {
				throw new Exception("参数文件:" + propertyFile + "不存在,请确认!");
			}
			// yml格式
			if (propertyFile.toLowerCase().endsWith("yml")) {
				yml2Properties(propFile);
			} else {
				Properties props = new Properties();
				props.load(new FileInputStream(propFile));
				Enumeration en = props.propertyNames();
				String key;
				while (en.hasMoreElements()) {
					key = (String) en.nextElement();
					constantMap.put(key, props.getProperty(key));
				}
			}
		}
	}

	/**
	 * @todo 获取常量信息
	 * @param key
	 * @return
	 */
	public static String getPropertyValue(String key) {
		if (StringUtil.isBlank(key))
			return key;
		String realKey = key.trim();
		// 简化匹配规则
		if (realKey.startsWith("${") && realKey.endsWith("}"))
			return (String) getKeyValue(realKey.substring(2, realKey.length() - 1));
		if (getKeyValue(key) != null)
			return getKeyValue(key);
		return key;
	}

	/**
	 * @todo 获取常量信息
	 * @param key
	 * @return
	 */
	public static String getKeyValue(String key) {
		if (StringUtil.isBlank(key))
			return key;
		String value = (String) constantMap.get(key);
		if (null == value) {
			value = System.getProperty(key);
		}
		return value;
	}

	public static String getJdbcType(String jdbcType, int dbType) {
		if (dbType == DBUtil.DbType.CLICKHOUSE && jdbcType.equalsIgnoreCase("datetime"))
			return "TIMESTAMP";
		for (String[] types : jdbcAry) {
			if (jdbcType.equalsIgnoreCase(types[0])) {
				return types[1];
			}
		}
		return jdbcType;
	}

	/**
	 * @todo 设置默认类型匹配
	 * @param typeMapping
	 */
	public static void addDefaultTypeMapping(List<ColumnTypeMapping> typeMapping) {
		ColumnTypeMapping typeMapping1 = new ColumnTypeMapping();
		typeMapping1.putNativeTypes(new String[] { "NUMBER", "DECIMAL", "NUMERIC" });
		typeMapping1.setJdbcType("INTEGER");
		typeMapping1.setJavaType("Integer");
		typeMapping1.setPrecisionMax(8);
		typeMapping1.setPrecisionMin(1);
		typeMapping1.setScaleMin(0);
		typeMapping1.setScaleMax(0);
		typeMapping1.setResultType("Integer");
		typeMapping.add(typeMapping1);
		ColumnTypeMapping typeMapping2 = new ColumnTypeMapping();
		typeMapping2.putNativeTypes(new String[] { "NUMBER", "DECIMAL", "NUMERIC" });
		typeMapping2.setJdbcType("INTEGER");
		typeMapping2.setJavaType("Long");
		typeMapping2.setPrecisionMax(64);
		typeMapping2.setPrecisionMin(9);
		typeMapping2.setScaleMin(0);
		typeMapping2.setScaleMax(0);
		typeMapping2.setResultType("Long");
		typeMapping.add(typeMapping2);
	}

	public static void yml2Properties(File path) {
		final String DOT = ".";
		try {
			YAMLFactory yamlFactory = new YAMLFactory();
			YAMLParser parser = yamlFactory
					.createParser(new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-8")));
			String key = "";
			String value = null;
			JsonToken token = parser.nextToken();
			while (token != null) {
				if (JsonToken.START_OBJECT.equals(token)) {
					// do nothing
				} else if (JsonToken.FIELD_NAME.equals(token)) {
					if (key.length() > 0) {
						key = key + DOT;
					}
					key = key + parser.getCurrentName();

					token = parser.nextToken();
					if (JsonToken.START_OBJECT.equals(token)) {
						continue;
					}
					value = parser.getText();
					constantMap.put(key.trim(), value.trim());
					int dotOffset = key.lastIndexOf(DOT);
					if (dotOffset > 0) {
						key = key.substring(0, dotOffset);
					}
					value = null;
				} else if (JsonToken.END_OBJECT.equals(token)) {
					int dotOffset = key.lastIndexOf(DOT);
					if (dotOffset > 0) {
						key = key.substring(0, dotOffset);
					} else {
						key = "";
					}
				}
				token = parser.nextToken();
			}
			parser.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

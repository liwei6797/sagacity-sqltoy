/**
 * 
 */
package com.sagframe.sqltoy.showcase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sagframe.sqltoy.SqlToyApplication;

/**
 * @project sqltoy-showcase
 * @description 演示elasticsearch5.x+ 版本的使用
 * @author chenrenfei <a href="mailto:zhongxuchen@gmail.com">联系作者</a>
 * @version id:ElasticCaseServiceTest.java,Revision:v1.0,Date:2019年7月12日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SqlToyApplication.class)
public class ElasticCaseServiceTest {
	@Autowired
	private SqlToyLazyDao sqlToyLazyDao;

	/**
	 * 演示普通的查询
	 */
	@Test
	public void testSearch() {

	}

	/**
	 * 演示分页查询
	 */
	@Test
	public void testFindPage() {

	}
}

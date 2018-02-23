/**
 *@Generated by QuickVO Tools 2.0
 */
package sqltoy.showcase.system.vo;

import org.sagacity.sqltoy.config.annotation.SqlToyEntity;
import java.math.BigDecimal;
import java.util.Date;
import sqltoy.showcase.system.vo.base.AbstractShardingRealVO;

/**
 * @project sqltoy-showcase
 * @author zhongxuchen
 * @version 1.0.0
 * 分片测试实时表  		
 * ShardingRealVO generated by sys_sharding_real
 */
@SqlToyEntity
public class ShardingRealVO extends AbstractShardingRealVO {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3456102673777887722L;
	
	/** default constructor */
	public ShardingRealVO() {
		super();
	}
	
	/*---begin-constructor-area---don't-update-this-area--*/
	/** pk constructor */
	public ShardingRealVO(BigDecimal id)
	{
		this.id=id;
	}

	/** minimal constructor */
	public ShardingRealVO(BigDecimal id,String staffId,Date createTime)
	{
		this.id=id;
		this.staffId=staffId;
		this.createTime=createTime;
	}

	/** full constructor */
	public ShardingRealVO(BigDecimal id,String staffId,String postType,Date createTime,String comments)
	{
		this.id=id;
		this.staffId=staffId;
		this.postType=postType;
		this.createTime=createTime;
		this.comments=comments;
	}

	/*---end-constructor-area---don't-update-this-area--*/
	
	/**
     *@todo vo columns to String
     */
	public String toString() {
		return super.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public ShardingRealVO clone() {
		try {
			// TODO Auto-generated method stub
			return (ShardingRealVO) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
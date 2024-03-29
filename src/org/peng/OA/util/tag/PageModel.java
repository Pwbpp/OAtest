package org.peng.OA.util.tag;
import org.peng.OA.util.common.OAConstants;

public class PageModel {
	//分页总数居条数
	private int recordCount;
	//当前页面
	private int pageIndex;
	//每页数据条数
	private int pageSize = OAConstants.PAGE_DEFAULT_SIZE = 4;
	//总页数
	private int totalSize;
	public int getRecordCount() {
		this.recordCount = this.recordCount <= 0 ? 0:this.recordCount;
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageIndex() {
		this.pageIndex = this.pageIndex <= 0?1:this.pageIndex;
		//若当前页超过总页数，将最后一页作为当前页
		this.pageIndex = this.pageIndex >= this.getTotalSize()?this.getTotalSize():this.pageIndex;
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		this.pageSize = this.pageSize <= OAConstants.PAGE_DEFAULT_SIZE ? OAConstants.PAGE_DEFAULT_SIZE : this.pageSize;
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalSize() {
		if(this.getRecordCount() <= 0) {
			totalSize = 0;
		}else {
			totalSize = (this.getRecordCount() - 1)/this.getPageSize() + 1;
		}
		return totalSize;
	}
	public int getFirstLimitParam() {
		return (this.getPageIndex()-1)*this.getPageSize();
	}
}

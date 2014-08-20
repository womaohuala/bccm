package com.cn.bccm.dao.base;

import java.util.List;

/**
 * 分页
 *
 */
public class Page<T> {
	public static final String ASC = "asc";

	public static final String DESC = "desc";
	
	public static final int DEFAULT_PAGE_SIZE = 20;
	
	private long totalCount;

	private int pageSize;
	
	private int pageNo;

	private boolean autoCount;
	
	private List<T> pageData = null;

	public Page() {
		this.totalCount = 0;
		this.pageSize = DEFAULT_PAGE_SIZE;
		this.pageNo = 1;
		this.autoCount = false;
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	public Page(int pageSize, boolean autoCount) {
		this.pageSize = pageSize;
		this.autoCount = autoCount;
	}

	public List<T> getPageData() {
		return this.pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize <= 0){
			this.pageSize = DEFAULT_PAGE_SIZE;
		}else{
			this.pageSize = pageSize;
		}
	}

	public boolean isPageSizeSetted() {
		return (this.pageSize > 0);
	}

	public int getTotalPages() {
		if (this.totalCount <= 0)
			return 0;

		int count = (int)(this.totalCount + this.pageSize - 1) / this.pageSize;
		
		return count;
	}

	public int getPageNo() {
		if(this.getTotalPages() <= 0){
			return 0;
		}else if(this.pageNo <= 0){
			return 1;
		}else if(pageNo > this.getTotalPages()){
			return this.getTotalPages();
		}
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getFirPage(){
		if(this.getTotalPages() <= 0){
			return 0;
		}else {
			return 1;
		}
	}
	
	public boolean isHasPre() {
		return (this.getPageNo() > 1);
	}

	public int getPrePage() {
		if (isHasPre())
			return (this.getPageNo() - 1);

		return this.getPageNo();
	}
	
	public boolean isHasNext() {
		return (this.getPageNo() < getTotalPages());
	}

	public int getNextPage() {
		if (isHasNext())
			return (this.getPageNo() + 1);

		return this.getPageNo();
	}

	public boolean isAutoCount() {
		return this.autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}
}

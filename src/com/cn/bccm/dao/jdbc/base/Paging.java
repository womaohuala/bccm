package com.cn.bccm.dao.jdbc.base;
import java.util.List;

public class Paging<T>{
	
	private List<T> list;
	private int page;
	private int pageSize;
	private long total;

	public List<T> getList() {
		return list;
	}

	public int getListSize() {
		if(list == null)
			return 0;
		return list.size();
	}

	public int getPage() {
		return page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setList(List<T> list) {
		this.list = list;	
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPageSize(int size) {
		this.pageSize = size;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageTotal() {
		return (int) (((total - 1) / getPageSize()) + 1);
	}

	public boolean isFirst() {
		return this.page == 1;
	}

	public boolean isLast() {
		return this.page == getPageTotal();
	}

	
	public String getLimitInfo() {
		if(page <= 0 || pageSize <= 0)
			return "";
		String info = " limit ";
		info += (page - 1) * pageSize+",";
		info += pageSize+"";
		return info;
	}

}

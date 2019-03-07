package store.domain;

import java.util.List;

public class PageBean<T> {

	private int currentPageNum;
	private int prePageNum;
	private int nextPageNum;
	private int pageSize;
	private int totalRecords;
	private int totalPages;
	private List<T> list;
	
	private int startPage;
	private int endPage;
	
	//used in page_bar.jsp
	private String url;

	public PageBean() {
		
	}

	public PageBean(int currentPageNum, int pageSize, int totalRecords) {
		this.currentPageNum = currentPageNum;
		this.pageSize = pageSize;
		this.totalRecords = totalRecords;
		
		totalPages = (int) Math.ceil(1.0*totalRecords/pageSize);
		
		if(currentPageNum > 1) {
			prePageNum = currentPageNum - 1;
		} else {
			prePageNum = 1;
		}
		if(currentPageNum < totalPages) {
			nextPageNum = currentPageNum + 1;
		} else {
			nextPageNum = totalPages;
		}
		
		
		startPage = currentPageNum - 4;
		endPage = currentPageNum + 4;
		
		//The maximum pages to display is 9
		if(totalPages > 9) {
			if(startPage < 1) {
				startPage = 1;
				endPage = startPage + 8;
			}
			if(endPage > totalPages) {
				endPage = totalPages;
				startPage = endPage - 8;
			}
		} else {
			startPage = 1;
			endPage = totalPages;
		}
		
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getPrePageNum() {
		return prePageNum;
	}

	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}

	public int getNextPageNum() {
		return nextPageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}

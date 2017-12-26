package kr.go.ngii.edu.main.board.model;

public class BbsPageCriteria {
	
	int firstPage;
	int limit;
	int prevPage;
	int startPage;
	int currentPage;
	int endPage;	
	int nextPage;	
	int finalPage;
	int recordsNum;
	int pagingSize;
	
	public BbsPageCriteria(int currentPage) {
		this.firstPage = 1;
		this.currentPage = currentPage;
		this.pagingSize = 10;
		this.limit = 10; 
	}
	
	
	public BbsPageCriteria(int currentPage, int limit) {
		this.firstPage = 1;
		this.currentPage = currentPage;
		this.pagingSize = 10;
		this.limit = (limit != 0) ? limit : 10; 
	}

	public BbsPageCriteria(int currentPage, int pagingSize, int limit) {
		this.firstPage = 1;
		this.currentPage = currentPage;
		this.pagingSize = pagingSize;
		this.limit = (limit != 0) ? limit : 10; 
	}
	
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getFinalPage() {
		return finalPage;
	}

	public void setFinalPage(int finalPage) {
		this.finalPage = finalPage;
	}

	public int getRecordsNum() {
		return recordsNum;
	}

	public void setRecordsNum(int recordsNum) {
		this.recordsNum = recordsNum;
		this.caclulatePage();
	}
	
	public int getOffset() {
		return (this.currentPage - 1) * this.limit;
	}

	public void caclulatePage() {	
		if(recordsNum == 0) {	
			return;	
		}
		
		if(currentPage == 0) {
			setCurrentPage(1);	
		}
		
		int finalPage = (recordsNum + (limit -1)) / limit;
		
		if(currentPage > finalPage) {
			setCurrentPage(finalPage);
		}
		
		if(currentPage < 0) {
			currentPage = 1;
		}
		
		boolean isFirst = currentPage == 1 ? true : false;
		boolean isFinal = currentPage == finalPage ? true : false;
		
		int startPage = ((currentPage -1) / pagingSize) * pagingSize + 1;
		
		int endPage = startPage + pagingSize -1; 
		
		if(endPage > finalPage) {
			endPage = finalPage;
		}
		
		if(!isFirst) {
			setPrevPage(((startPage -1) < 1 ? 1 : (startPage -1)));
		}
		
		setStartPage(startPage);
		setEndPage(endPage);
		
		if(!isFinal)	{
			setNextPage(currentPage+1);
//			setNextPage(((endPage +1 > finalPage ? finalPage : (endPage +1))));
		}
		
		setFinalPage(finalPage);
	}	

}

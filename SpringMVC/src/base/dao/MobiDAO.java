package base.dao;

import base.util.Pagination;

public interface MobiDAO {

	public Pagination searchMobi(String content,int currentPage);
}

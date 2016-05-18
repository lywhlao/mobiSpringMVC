package base.dao;

import base.util.Pagination;

public interface IMobiDAO {

	public Pagination searchMobi(String content,int currentPage);
}

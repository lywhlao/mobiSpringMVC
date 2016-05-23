package base.dao;

import org.springframework.ui.Model;

import base.util.Pagination;

public interface IMobiDAO {

	public String searchMobi(String content,int currentPage,Model model);
}

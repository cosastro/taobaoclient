package com.android.taobaoclient.dao;

import java.util.List;
import com.android.taobaoclient.model.Category;


public interface ICategoryService {
	
	public List<Category> getAllCategory();
	
	public Category getCategoryById(int id);
}

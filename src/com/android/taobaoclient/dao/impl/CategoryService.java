package com.android.taobaoclient.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.android.taobaoclient.dao.ICategoryService;
import com.android.taobaoclient.model.Category;

public class CategoryService implements ICategoryService {

	List<Category> categories = new ArrayList<Category>();
	
	@Override
	public List<Category> getAllCategory() {
		
		categories.add(new Category(1, "手机"));
		categories.add(new Category(2, "电器"));
		categories.add(new Category(3, "服装"));
		categories.add(new Category(4, "图书"));
		
		return categories;
	}

	@Override
	public Category getCategoryById(int id) {
		if(id <= 0 || id > 4)
		{
			return null;
		}
		
		if(categories != null){
			return this.categories.get(id - 1);
		}
		
		return null;
	}

}

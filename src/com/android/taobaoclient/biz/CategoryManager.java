package com.android.taobaoclient.biz;

import java.util.List;

import com.android.taobaoclient.dao.ICategoryService;
import com.android.taobaoclient.dao.impl.CategoryService;
import com.android.taobaoclient.model.Category;

public class CategoryManager {
	
	private ICategoryService daoCategoryService;
	
	public CategoryManager(){
		daoCategoryService = new CategoryService();
	}
	
	public List<Category> getAllCategory(){
		return daoCategoryService.getAllCategory();
	}
	
	public Category getCategoryById(int id){
		return daoCategoryService.getCategoryById(id);
	}
}

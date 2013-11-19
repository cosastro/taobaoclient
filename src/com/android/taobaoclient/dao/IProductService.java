package com.android.taobaoclient.dao;

import java.util.List;

import com.android.taobaoclient.model.Product;

public interface IProductService {
	
	public List<Product> getAllProduct();
	
	public List<Product> getProductByPage(int pageIdx, int pageSize);
	
	public Product getProductById(int id);
	
	public List<Product> getProductByName(String name);
	
	public void add(Product product);
	
	public void modify(Product product);
	
	public void delete(int productId);
}

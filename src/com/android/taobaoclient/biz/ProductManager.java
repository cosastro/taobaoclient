package com.android.taobaoclient.biz;

import java.util.List;

import android.graphics.Point;

import com.android.taobaoclient.dao.IProductService;
import com.android.taobaoclient.dao.impl.ProductService;
import com.android.taobaoclient.model.Product;

public class ProductManager {
	
	private IProductService daoIProductService;
	
	public ProductManager(){
		daoIProductService = new ProductService();
	}
	
	public List<Product> getAllProduct(){
		return daoIProductService.getAllProduct();
	}
	
	public List<Product> getProductByPage(int pageIdx, int pageSize){
		return daoIProductService.getProductByPage(pageIdx, pageSize);
	}
	
	public Product getProductById(int id){
		return daoIProductService.getProductById(id);
	}
	
	public List<Product> getProductByName(String name){
		return daoIProductService.getProductByName(name);
	}
	
	public boolean add(Product product) throws Exception{
		try {
			daoIProductService.add(product);
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
	
	public boolean add(int id, 
			String name, 
			int categoryId, 
			int photo, 
			double unitPrice, 
			String comment) throws Exception{
		Product product = this.getProductById(id);
		if(product == null)
			return false;
		
		product.setName(name);
		product.setCategoryId(categoryId);
		product.setPhoto(photo);
		product.setUnitPrice(unitPrice);
		product.setComment(comment);
		
		return add(product);
	}
	
	public boolean modify(Product product) throws Exception{
		try {
			daoIProductService.modify(product);
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
	
	public boolean modify(int id, 
			String name, 
			int categoryId, 
			int photo, 
			double unitPrice, 
			String comment) throws Exception{
		Product product = this.getProductById(id);
		if(product == null)
			return false;
		
		product.setName(name);
		product.setCategoryId(categoryId);
		product.setPhoto(photo);
		product.setUnitPrice(unitPrice);
		product.setComment(comment);
		
		return modify(product);
	}
	
	public void delete(int productId){
		daoIProductService.delete(productId);
	}

}

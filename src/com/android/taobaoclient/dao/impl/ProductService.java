package com.android.taobaoclient.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.android.taobaoclient.R;
import com.android.taobaoclient.dao.IProductService;
import com.android.taobaoclient.model.Product;

public class ProductService implements IProductService {

	private static Product[] products = {
			new Product(1, "三星 GT-S5830", 1, R.drawable.p1, 1630,
					"网络类型:WCDMA(3G) 操作系统:ANDROID "),
			new Product(2, "HTC A510e", 1, R.drawable.p2, 1514,
					"网络类型:WCDMA(3G) 操作系统:ANDROID "),
			new Product(3, "三星 I9100", 1, R.drawable.p3, 3266,
					"网络类型:WCDMA(3G) 操作系统:ANDROID "),
			new Product(4, "中兴 U880（TD版）", 1, R.drawable.p4, 989,
					"网络类型:TD-SCDMA(3G) 操作系统:ANDROID  "),
			new Product(5, "Sony Ericsson索尼爱立信 ", 1, R.drawable.p5, 2584,
					"网络类型:WCDMA(3G) 操作系统:ANDROID "),
			new Product(6, "摩托罗拉 Defy", 1, R.drawable.p6, 1851,
					"网络类型:WCDMA(3G) 操作系统:ANDROID "),
			new Product(7, "中兴 V880", 1, R.drawable.p7, 957,
					"网络类型:WCDMA(3G) 操作系统:ANDROID "),
			new Product(8, "HTC S710e", 1, R.drawable.p8, 2671,
					"网络类型:WCDMA(3G) 操作系统:ANDROID "),
			new Product(9, "摩托罗拉 ME525", 1, R.drawable.p9, 1853,
					"网络类型:WCDMA(3G) 操作系统:ANDROID "),
			new Product(10, "G12", 1, R.drawable.p10, 2470,
					"网络类型:WCDMA(3G) 操作系统:ANDROID "),
			new Product(11, "摩托罗拉 ME525+", 1, R.drawable.p11, 1923,
					"网络类型:WCDMA(3G) 操作系统:ANDROID "),
			new Product(12, "Sony Ericsson", 1, R.drawable.p12, 2231,
					"网络类型:WCDMA(3G) 操作系统:ANDROID "),
			new Product(13, "Lenovo", 1, R.drawable.p13, 997,
					"网络类型:WCDMA(3G) 操作系统:ANDROID ") };

	private List<Product> lst_product = new ArrayList<Product>();
	
	public ProductService(){
		for(Product product : products){
			lst_product.add(product);
		}
	}
	
	@Override
	public List<Product> getAllProduct() {
		return lst_product;
	}

	@Override
	public List<Product> getProductByPage(int pageIdx, int pageSize) {
		if(pageSize <= 0){
			return new ArrayList<Product>();
		}
		
		if(pageIdx < 1){
			pageIdx = 1;
		}
		
		int all_page_count = 1;
		if(lst_product.size() % pageSize == 0){
			all_page_count = lst_product.size() / pageSize;
		}else{
			all_page_count = lst_product.size() / pageSize + 1;
		}
		
		if(pageIdx > all_page_count){
			return new ArrayList<Product>();
		}
		
		List<Product> res = new ArrayList<Product>();
		int baseIdx = (pageIdx - 1) * pageSize;
		for(int i = baseIdx; i < baseIdx + pageSize && i < this.lst_product.size(); i++){
			res.add(lst_product.get(i));
		}
		return res;
	}

	@Override
	public Product getProductById(int id) {
		for(Product product : lst_product){
			if(product.getId() == id){
				return product;
			}
		}
		return null;
	}

	@Override
	public List<Product> getProductByName(String name) {
		List<Product> res = new ArrayList<Product>();
		for(Product product : lst_product){
			if(product.getName().indexOf(name) != -1){
				res.add(product);
			}
		}
		return res;
	}

	@Override
	public void add(Product product) {
		if(product != null)
			this.lst_product.add(product);
	}

	@Override
	public void modify(Product product) {
		if(product == null)
			return;
		
		for(int i = 0; i < lst_product.size(); i++){
			if(product.getId() == lst_product.get(i).getId()){
				lst_product.set(i, product);
			}
		}
	}

	@Override
	public void delete(int productId) {
		if(productId <= 0){
			return;
		}
		
		Product tmp_prod = getProductById(productId);
		if(tmp_prod != null){
			lst_product.remove(tmp_prod);
		}
	}

}

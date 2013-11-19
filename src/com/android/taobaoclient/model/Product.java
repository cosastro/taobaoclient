package com.android.taobaoclient.model;

import com.android.taobaoclient.R;

public class Product {
	public Product(){
		
	}
	
	public Product(int id, String name, int categoryId, int photo,
			double unitPrice, String comment) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.photo = photo;
		this.unitPrice = unitPrice;
		this.comment = comment;
	}
	
	private int id;
	private String name;
	private int categoryId;
	private int photo;
	private double unitPrice;
	private String comment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getPhoto() {
		if(id == 0){
			return R.drawable.p1;
		}
		else if(this.id == 1)
			return R.drawable.p1;
		else if(id == 2){
			return R.drawable.p2;
		}else if(id == 3){
			return R.drawable.p3;
		}else if(id == 4){
			return R.drawable.p4;
		}else if(id == 5){
			return R.drawable.p5;
		}else if(id == 6){
			return R.drawable.p6;
		}else if(id == 7){
			return R.drawable.p7;
		}else if(id == 8){
			return R.drawable.p8;
		}else if(id == 9){
			return R.drawable.p9;
		}else if(id == 10){
			return R.drawable.p10;
		}else if(id == 11){
			return R.drawable.p11;
		}else if(id == 12){
			return R.drawable.p12;
		}else if(id == 13){
			return R.drawable.p13;
		}else{
			return 0;
		}
	}

	public void setPhoto(int photo) {
		this.photo = photo;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}

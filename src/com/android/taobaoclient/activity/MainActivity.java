package com.android.taobaoclient.activity;

import java.util.List;
import java.util.Random;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.taobaoclient.R;
import com.android.taobaoclient.biz.ProductManager;
import com.android.taobaoclient.biz.UserManager;
import com.android.taobaoclient.model.Product;
import com.android.taobaoclient.model.User;

public class MainActivity extends ListActivity {

	private Context context = null;
	private LayoutInflater inflater = null;
	private User loginUser = null;
	private UserManager userManager = null;
	
	//对话框类型
	private final static int DIALOG_LOGIN = 1;		//登陆对话框
	private final static int DIALOG_EXIT = -1;		//确认退出对话框
	private final static int DIALOG_REG = 2;
	
	//
	private final static int PAGE_SIZE = 5;
	private int cur_page_idx = 1;					//当前页数
	private ProductManager productManager = null;
	private List<Product> products = null;
	private ListView lvProducts = null;
	private MyAdapter adapter = null;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		
		showDialog(DIALOG_LOGIN);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			showDialog(DIALOG_EXIT);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void init() {
		context = this;
		inflater = LayoutInflater.from(this);
		userManager = new UserManager();
	}
	
	private void initMain() {
		productManager = new ProductManager();
		products = productManager.getProductByPage(cur_page_idx, PAGE_SIZE);
		
		setContentView(R.layout.main);
		
		adapter = new MyAdapter(context);
		setListAdapter(adapter);
		lvProducts = getListView();
		lvProducts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
				if(id == -1){
					Utilities.showMessage(context, "添加新商品");
				}else if(id == -2){
					//Utilities.showMessage(context, "更多");
					adapter.more();
					
				}else{
					Utilities.showMessage(context, String.valueOf(id));
				}
			}
			
		});
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = buildDialog(id);
		return dialog;
	}

	private Dialog buildDialog(int flag){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		switch (flag) {
		case DIALOG_LOGIN:
			createLoginDialog(builder);
			break;
		case DIALOG_EXIT:
			createExitDialog(builder);
			break;
		case DIALOG_REG:
			createRegisterDialog(builder);
			break;
		default:
			break;
		}
		
		return builder.create();
	}
	
	private void createRegisterDialog(AlertDialog.Builder builder) {
		View view;
		view = inflater.inflate(R.layout.register, null);
		builder.setTitle("注册").setIcon(R.drawable.ic_launcher).setView(view);
		
		final EditText reg_et_username = (EditText)view.findViewById(R.id.reg_et_username);
		final EditText reg_et_password = (EditText)view.findViewById(R.id.reg_et_password); 
		final EditText reg_et_pwsure = (EditText)view.findViewById(R.id.reg_et_pwsure);
		final EditText reg_et_chkcode = (EditText)view.findViewById(R.id.reg_et_checkcode);
		final TextView txt_chkcode = (TextView)view.findViewById(R.id.reg_tv_chkcode);
		txt_chkcode.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((TextView)v).setText(getCheckCode());
			}
		});
		
		Button btn_reg = (Button)view.findViewById(R.id.reg_btn_reg);
		btn_reg.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				if(!txt_chkcode.getText().toString().equals(reg_et_chkcode.getText().toString())){
					Utilities.showMessage(context, "验证码不正确");
					txt_chkcode.setText(getCheckCode());
					return;
				}
				
				if(!reg_et_password.getText().toString().equals(reg_et_pwsure.getText().toString())){
					Utilities.showMessage(context, "两次输入的密码不一致，请重新输入");
					return;
				}
				
				User user = new User(reg_et_username.getText().toString(), reg_et_password.getText().toString());
				User reg_res = userManager.Register(user);
				if(reg_res != null){
					dismissDialog(DIALOG_REG);
				}else{
					Utilities.showMessage(context, "注册失败");
				}
			}
		});
		
		Button btn_exit = (Button)view.findViewById(R.id.reg_btn_exit);
		btn_exit.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {
				dismissDialog(DIALOG_REG);
			}
		});
	}
	
	//每次在OnCreateDialog以后都会调用
	@Override
	@Deprecated
	protected void onPrepareDialog(int id, Dialog dialog) {
		if(id == DIALOG_REG){
			String chkCode = getCheckCode();
			
			TextView tv = (TextView)dialog.findViewById(R.id.reg_tv_chkcode);
			tv.setText(chkCode);
		}
	}

	private String getCheckCode(){
		String res = "";
		Random rd = new Random();
		for(int i = 0; i < 4; i++){
			res += rd.nextInt(10);
		}
		
		return res;
	}

	private void createExitDialog(AlertDialog.Builder builder) {
		builder.setTitle("警告")
				.setIcon(R.drawable.ic_launcher)
				.setMessage("您确定要退出吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				}).setNegativeButton("取消", null);
	}

	private void createLoginDialog(AlertDialog.Builder builder) {
		View view;
		view = inflater.inflate(R.layout.login, null);
		builder.setTitle("登陆").setIcon(R.drawable.ic_launcher).setView(view);
		
		final TextView tv_reg = (TextView)view.findViewById(R.id.tv_reg);
		final EditText et_username = (EditText)view.findViewById(R.id.et_username);
		final EditText et_password = (EditText)view.findViewById(R.id.et_password); 
		Button btn_login = (Button)view.findViewById(R.id.btn_login);
		btn_login.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {
				
				loginUser = userManager.Login(et_username.getText().toString(), et_password.getText().toString());
				if(loginUser != null){
					dismissDialog(DIALOG_LOGIN);
					initMain();
				}else{
					Utilities.showMessage(context, "账号或密码不正确，登陆失败！");
				}
			}
		});
		
		Button btn_exit = (Button)view.findViewById(R.id.btn_exit);
		btn_exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		tv_reg.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(DIALOG_REG);
			}
		});
	}
	
	class MyAdapter extends BaseAdapter{
		private Context context;
		public MyAdapter(Context context){
			this.context = context;
		}
		
		public void more(){
			cur_page_idx++;
			
			List<Product> more_Products = productManager.getProductByPage(cur_page_idx, PAGE_SIZE);
			if(more_Products.size() > 0){
				products.addAll(more_Products);
				this.notifyDataSetChanged();
			}else{
				Utilities.showMessage(context, "没有更多数据了！");
			}
		}
		
		@Override
		public int getCount() {
			return products.size() + 2;
		}

		@Override
		public Product getItem(int position) {
			return products.get(position - 1);
		}

		@Override
		public long getItemId(int position) {
			//return products.get(position - 1).getId();
			
			if (position == 0)	//选中第一项
			{
				return -1;		//第一项
			} else if (position > 0 && (position < this.getCount() - 1)) {
				return products.get(position - 1).getId();		//中间项
			} else {
				return -2;		//最后一项
			}
		}

		@Override
		public View getView(int position, View contentView, ViewGroup parent) {
			
			//第一项
			if(position == 0){
				View view = inflater.inflate(R.layout.addproduct, null);
				return view;
			}
			
			//最后一项
			if(position == this.getCount() - 1){
				View view = inflater.inflate(R.layout.moreitem, null);
				return view;
			}
			
			if(contentView == null 
					|| contentView.findViewById(R.id.addproduct) != null
					|| contentView.findViewById(R.id.linemore) != null){
				contentView = inflater.inflate(R.layout.product_item, parent, false);
			}
			
			ImageView iv_img = (ImageView) contentView.findViewById(R.id.main_iv_proc);
			iv_img.setImageResource(products.get(position - 1).getPhoto());
			TextView tv_name = (TextView)contentView.findViewById(R.id.main_tv_proc_title);
			tv_name.setText(products.get(position - 1).getName());
			TextView tv_price = (TextView)contentView.findViewById(R.id.main_tv_proc_price);
			tv_price.setText(String.valueOf(products.get(position - 1).getUnitPrice()));
			
			return contentView;
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

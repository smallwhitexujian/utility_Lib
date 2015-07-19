package net.dev.mylib.netWorkUtil;

import java.util.Map;

import net.dev.mylib.DebugLogs;
import net.dev.mylib.ToastUtils;
import net.dev.mylib.view.LoadingDialog;
import android.content.Context;
import android.util.DebugUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * @author x.j 
 * volley(网络请求)
 */
public class GetJson {
	private GetJson.Callback mCallback;
	private Context mContext;
	
	public GetJson(){
		
	}

	public GetJson(GetJson.Callback callback) {
		setCallback(callback);
	}

	public GetJson(Context context, GetJson.Callback callback) {
		this.mContext = context;
		setCallback(callback);
	}
	
	public GetJson(Context context, GetJson.Callback callback,boolean isLoading){
		this.mContext = context;
		setCallback(callback);
		if (isLoading) {
			LoadingDialog.showSysLoadingDialog(mContext,"");
		}
	}
	
	public GetJson(Context context, GetJson.Callback callback,boolean isLoading,String title){
		this.mContext = context;
		setCallback(callback);
		if (isLoading) {
			LoadingDialog.showSysLoadingDialog(mContext, title);
		}
	}
	
	private void setCallback(GetJson.Callback callback){
		this.mCallback = callback;
	}

	@SuppressWarnings("rawtypes")
	public void setConnection(int Method, String url, final Map params) {
		RequestManager manager = RequestManager.getInstance(mContext);// 创建管理线程池
		StringRequest request = new StringRequest(Method, url,new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						if (mCallback != null) {
							mCallback.onFinish(response);
							LoadingDialog.cancelLoadingDialog();
						}
					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						if (DebugLogs.isDebug) {
							ToastUtils.showToast(mContext, "网络请求错误"+error.toString());
						}
						LoadingDialog.cancelLoadingDialog();
						if (mCallback != null) {
							mCallback.onError(error);
						}
					}
				}) {
			@SuppressWarnings("unchecked")
			protected Map getParams() throws AuthFailureError {
				return params;
			}
		};
		manager.addToRequestQueue(request, getClass().getSimpleName());
	}

	public interface Callback {
		/** 请求结果返回 **/
		public void onFinish(String response);
		/** 请求错误返回 **/
		public void onError(VolleyError error);
	}
}

package net.dev.mylib.netWorkUtil;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestManager {
	private final String tag = getClass().getSimpleName();
	private static Context context;
	private static RequestManager myRequestManager;
	private static RequestQueue myRequestQueue;


	public static RequestManager getInstance(Context context) {
		if (myRequestManager == null) {
			myRequestManager = new RequestManager(context);
		}
		return myRequestManager;
	}

	@SuppressWarnings("static-access")
	private RequestManager(Context context) {
		this.context = context;
		init();
	}

	private static void init() {
		myRequestQueue = Volley.newRequestQueue(context);
	}

	public static RequestQueue getRequestQueue() {
		if (myRequestQueue == null) {
			init();
		}
		return myRequestQueue;
	}

	@SuppressWarnings("rawtypes")
	public void addToRequestQueue(Request request) {
		addToRequestQueue(request, tag);
	}

	@SuppressWarnings("rawtypes")
	public void addToRequestQueue(Request request, String tag) {
		request.setTag(tag);
		getRequestQueue().add(request);
	}

	public void cancelRequests(String tag) {
		if (myRequestQueue != null) {
			myRequestQueue.cancelAll(tag);
		}
	}	

}

package net.dev.mylib;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author xu.jian
 * _Json转化
 */
public class JsonUtil {
	private static String json = "";
	
	/**
	 * 
	 * @param list
	 * @return _Json（单层）；
	 * @throws Exception
	 */
	public static String getJson(List<Map<String,String>>list) throws Exception{
		String data = "";
		if(list != null){
			data = "";
			for(Map<String,String>map : list){
				StringBuffer m = new StringBuffer();
				m.append("{");
				for(String key : map.keySet()){
					m.append("\"" +key + "\":\""+map.get(key)+ "\",");
				}
				String subString = m.substring(0, m.length() -1)+"}";
				data += subString + ",";
			}
			data = data.substring(0,data.length() - 1);
		}else{
			data = "";
		}
		return data;
	}
	
	/**
	 * @param list
	 * @return 返回数组类型的 _Json（单层）；
	 * @throws Exception
	 */
	public static String getListJson(List<Map<String,String>>list) throws Exception{
		String data = "";
		if(list != null){
			data = "";
			for(Map<String,String>map : list){
				StringBuffer m = new StringBuffer();
				m.append("{");
				for(String key : map.keySet()){
					m.append("\"" +key + "\":\""+map.get(key)+ "\",");
				}
				String subString = m.substring(0, m.length() -1)+"}";
				data += subString + ",";
			}
			data = data.substring(0,data.length() - 1);
		}else{
			data = "";
		}
		json = "[" + data + "]";
		return json;
	}
	
	/**
	 * 使用：创建一个list对象，对象里面直接放入add(bean) 
	 * @param arrayListJson
	 * @return 传入一个list直接转化为_json对象 支持多层 
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private static String toListJson(List arrayListJson){
		try {
	           return (new Gson()).toJson(arrayListJson);
	        } catch (Exception e){
	            e.printStackTrace();
	        }
	        return null;
	}
	
	public static String toJson(Object src, Type typeOfSrc){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        try {
            return gson.toJson(src, typeOfSrc);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
	
	/**
	 * bean 解析
	 * @param json
	 * @param claxx
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> claxx) {
        try {
           return new Gson().fromJson(json, claxx);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
	
   	/**
   	 * 支持多层解析 只需要传入对应type
   	 * @param json
   	 * @param typeOfT
   	 * @return
   	 */
   	public static <T> T fromJson(String json, Type typeOfT) {
        try {
           return new Gson().fromJson(json, typeOfT);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

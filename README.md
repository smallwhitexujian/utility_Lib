# utility_Lib
Android_快速开发框架
为了解决在项目开发中重复写代码问题，所以利用空余时间整理了这个lib.
Utility_Lib集成了封装了Google_Volley请求，将复杂的请求简单化，下面看一个简单地post请求示例。在项目普遍用post请求居多
源文件请看：net.dev.mylib.netWorkUtil.GitJson.java（名字有点丑别见怪哈）

 Map<String, String> params = new HashMap<String, String>();
 params.put("userId", "userId");
 GetJson.Callback callback = new GetJson.Callback() {
            @Override
            public void onFinish(String response) {
               //TODO 这里直接处理返回结果response
            }

            @Override
            public void onError(VolleyError error) {
                //TODO Volley错误处理
                DebugLogs.e("------error-" + error.toString());
            }
        };
  GetJson GetJson = new GetJson(Context, callback, true, "正在努力加载中...");
  GetJson.setConnection(Request.Method.POST, "www.baidu.com", params);
  
  看了以上代码有没有觉得，哇请求原来也可以这么简单。（不必要重复去每次都要复制很多，并且也容易错，）
  ------->光有了请求是无法满足我们这群懒人的，嘿嘿。当然大家都懂 需要一个解析json工具，当然我们怎么会错误放弃这个必要重要的东西呢。
  我们来看如何进行简单JSON解析这里简化了json解析，原先每次请求过来都需要一层一层的解析 还得做好防护，现在福利来了。请看JsonUtil.java
  这里也是直接将Google提供的Gson进行简单封装了。方便做解析。

  public static <T> T fromJson(String json, Type typeOfT) {
        try {
           return new Gson().fromJson(json, typeOfT);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

  那么如何使用呢：
  //简单Model

  public class ModifyEntity implements Serializable{
      private String userId;
      public String getUserId(){
          return userId;
      }
      public void setUserId(String userId){
          this.userId = userId;
      }
  }
  ModifyEntity entity = JsonUtil.fromJson(response, ModifyEntity.class);
  String userId = entity.getUserId().toString();

//看这样我们就可以进行一层解析，方便吧 神奇吧，嘿嘿，
  
  多层解析-----.
  public class Entity implements Serializable{
    public String state ;
    public String message ;
  }
  CommonListResult<Entity> result = JsonUtil.fromJson(response, new TypeToken<CommonListResult<Entity>>() {}.getType());
      String state = result.state;
      String message = result.message;
}

重要的请求和解析都搞完了。剩下就是愉快的写代码的。
一下就是一些简单目录
/**
 * @author xujian
 * 常用工具
 */
public class Utility 
/**
 * @author xujian
 * 消息提示toast
 */
public class ToastUtils 
/**
 * 获得屏幕相关的辅助类
 */
public class ScreenUtils {
/**
 * Log工具，类似android.util.Log。 tag自动产生，格式:
 * customTagPrefix:className.methodName(Line:lineNumber),
 * customTagPrefix为空时只输出：className.methodName(Line:lineNumber)。
 */
public class DebugLogs {
/**
 * @author xu.jian
 * _Json转化
 */
public class JsonUtil {
/**
 * @author xujian 时间格式转化工具
 */
@SuppressLint("SimpleDateFormat")
public class DateFormat {
/**
 * @author xujian
 * 网络检测工具类
 */
public class NetWorkUtil {

/**
 * @author xujian
 * SharedPreferencesUtil
 */
public class SharedPreferencesUtil {
/**
 * @author xujian 加载进度条
 */
@SuppressLint("NewApi")
public class LoadingDialog {
/**
 * @author xujian
 * listView 和 ScrollView加载更多和刷新
 */
public class PullToRefreshView extends LinearLayout {
/**
 * @author xujian
 * 圆形头像
 */
public class CircularImage extends MaskedImage {
/**
 * @author xujian
 * 数字选择器
 */
public class NumberPicker extends LinearLayout {
/**
 * @author xujian
 * 文件缓存
 */
public class FileCache {
/**
 * @author xujian
 * 时间选择器
 */
public class DateTimePicker extends FrameLayout {
/** * 本应用数据清除管理器 */
@SuppressLint("SdCardPath")
public class DataCleanManager {

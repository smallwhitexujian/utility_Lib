package net.dev.mylib.fileCache;

import java.io.File;  

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
/**
 * @author xujian
 * 文件缓存
 */
public class FileCache {
	private File cacheDir;  
	  
    public FileCache(Context context) {  
        // 判断外存SD卡挂载状态，如果挂载正常，创建SD卡缓存文件夹  
        if (android.os.Environment.getExternalStorageState().equals( android.os.Environment.MEDIA_MOUNTED)) {  
            cacheDir = new File(android.os.Environment.getExternalStorageDirectory(),"xjCacheDir");  
        } else {  
            // SD卡挂载不正常，获取本地缓存文件夹（应用包所在目录）  
            cacheDir = context.getCacheDir();  
        }  
        if (!cacheDir.exists()) {  
            cacheDir.mkdirs();  
        }  
    }  
  
    /**
     * 判断两个路径是否相等
     * @param url
     * @return
     */
    public File getFile(String url) {  
        String fileName = String.valueOf(url.hashCode());  
        File file = new File(cacheDir, fileName);  
        return file;  
    }  
  
    /**
     * 删除当前文件夹
     */
    public void clear() {  
        File[] files = cacheDir.listFiles();  
        for (File f : files)  {
            f.delete();  
        }
    }  
  
    /**
     * 获取当前目录缓存大小 
     * @return 单位M
     */
    public String getCacheSize() {  
        long size = 0;  
        if (cacheDir.exists()) {  
            File[] files = cacheDir.listFiles();  
            for (File f : files) {  
                size += f.length();  
            }  
        }  
        String cacheSize = String.valueOf(size / 1024 / 1024) + "M";  
        return cacheSize;  
    }  
    
     
    /**
     * 文件递归算法
     * @param name
     */
    static int blank = 0; 
    public void traverseDirectory(String name){  
        blank++;  
        File directory = new File(name);  
        File back[] = directory.listFiles();  
        for(int j=0;j<back.length;j++){  
            if(back[j].isFile()){  
                for(int i=0;i<blank;i++){  
                    System.out.print("--");  
                }  
                System.out.println(back[j].getName());  
            }  
        }  
        for(int i=0;i<back.length;i++){  
            if(back[i].isDirectory()){  
                for(int j=0;j<blank;j++){  
                    System.out.print("--");  
                }  
                System.out.println(back[i].getName());  
                traverseDirectory(back[i].getAbsolutePath());  
                blank--;  
            }  
        }  
    }  
    
	/**
	 * 判断SDCard是否可用
	 * 
	 * @return
	 */
	public static boolean isSDCardEnable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取SD卡路径
	 * 
	 * @return
	 */
	public static String getSDCardPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	}

	/**
	 * 获取SD卡的剩余容量 单位byte
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static long getSDCardAllSize() {
		if (isSDCardEnable()) {
			StatFs stat = new StatFs(getSDCardPath());
			// 获取空闲的数据块的数量
			long availableBlocks = (long) stat.getAvailableBlocks() - 4;
			// 获取单个数据块的大小（byte）
			long freeBlocks = stat.getAvailableBlocks();
			return freeBlocks * availableBlocks;
		}
		return 0;
	}

	/**
	 * 获取指定路径所在空间的剩余可用容量字节数，单位byte
	 * 
	 * @param filePath
	 * @return 容量字节 SDCard可用空间，内部存储可用空间
	 */
	@SuppressWarnings("deprecation")
	public static long getFreeBytes(String filePath) {
		// 如果是sd卡的下的路径，则获取sd卡可用容量
		if (filePath.startsWith(getSDCardPath())) {
			filePath = getSDCardPath();
		} else {// 如果是内部存储的路径，则获取内存存储的可用容量
			filePath = Environment.getDataDirectory().getAbsolutePath();
		}
		StatFs stat = new StatFs(filePath);
		long availableBlocks = (long) stat.getAvailableBlocks() - 4;
		return stat.getBlockSize() * availableBlocks;
	}

	/**
	 * 获取系统存储路径
	 * 
	 * @return
	 */
	public static String getRootDirectoryPath() {
		return Environment.getRootDirectory().getAbsolutePath();
	}
}

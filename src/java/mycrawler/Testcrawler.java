package mycrawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 import mycrawler.Myhbase;

public class Testcrawler {

    private static final String URL = "https://detail.ju.taobao.com/home.htm?spm=608.2291429.102212b.2.A8zq9q&id=10000019514946&item_id=27352928976";  
    private static final String ECODING = "GBK";  
    // 获取img标签正则  
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";  
    // 获取src路径的正则  
    private static final String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)";  

public static void main(String[] args) throws Exception {  
	Testcrawler tc = new Testcrawler();  
		String HTML = tc.getHTML(URL);
        List<String> Imgurls = tc.getImageUrl(HTML);
        if(HTML.isEmpty())
        	System.out.println("there is nothing");
        else System.out.println(HTML);
        
//        if(Imgurls.isEmpty())
//        	System.out.println("there is nothing");
//        else 
//        int index=0;
//        	while(index < Imgurls.size()){
//        		System.out.println(Imgurls.get(index)+"\n");
//        		index++;
//        	}
//        Myhbase.put("webpage", "", "f", "testcolumn", "testdata");
    }  

/*** 
     * 获取HTML内容 
     *  
     * @param url 
     * @return 
     * @throws Exception 
     */  
    private String getHTML(String url) throws Exception {  
        URL uri = new URL(url);
        URLConnection connection = uri.openConnection();
        InputStream in = connection.getInputStream();
        byte[] buf = new byte[1024];
        int length = 0;
        StringBuffer sb = new StringBuffer();
        while ((length = in.read(buf, 0, buf.length)) > 0) {
            sb.append(new String(buf, ECODING));
        }
        in.close();
        return sb.toString();
    }
    
    /*** 
     * 获取ImageUrl地址 
     *  
     * @param HTML 
     * @return 
     */  
    private List<String> getImageUrl(String HTML) {  
        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(HTML);  
        List<String> listImgUrl = new ArrayList<String>();  
        while (matcher.find()) {  
            listImgUrl.add(matcher.group());  
        }  
        return listImgUrl;
    }
}
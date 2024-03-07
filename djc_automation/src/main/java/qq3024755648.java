
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class qq3024755648 {
    private static PublicKey RSA() {
        String baseDir = System.getProperty("user.dir");
        byte[] bArr;
        Exception e;
        try {
            InputStream open = new FileInputStream(baseDir + "\\djc_rsa_public_key_new.der");
            try {
                bArr = new byte[open.available()];
                do {
                    try {
                    } catch (Exception e2) {
                        e = e2;
                        System.out.println("Got exception while is -> bytearr conversion: " + e);
                        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr));
                    }
                } while (open.read(bArr) != -1);
            } catch (Exception e3) {
                e = e3;
                bArr = null;
            }
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr));
        } catch (Exception e4) {
            System.out.println("cuole");
            return null;
        }
    }

    public static byte[] AES(String str, String str2) throws Exception {
        if (str == null) {
            return null;
        }
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
        instance.init(1, new SecretKeySpec(str2.getBytes(StandardCharsets.UTF_8), "AES"));
        return instance.doFinal(str.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] toRSA(byte[] bArr) throws Exception {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(1, RSA());
        return instance.doFinal(bArr);
    }

    public static String hex2String(String hexString) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < hexString.length(); i+=2) {
            sb.append((char) Integer.parseInt(hexString.substring(i, i + 2), 16));
        }
        return sb.toString();
    }

    public static String bytesToHex3(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for(byte b : bytes) { // 使用String的format方法进行转换
            buf.append(String.format("%02x", Integer.valueOf(b & 0xff)));
        }

        return buf.toString();
    }

    public static String p_tk = "1666291783";
    public static String openid = "054261307B42A3D14C07D9C74CBB6366";
    public static String open_access = "openid=054261307B42A3D14C07D9C74CBB6366; access_token=72825BA3333473975345A3C995E3AB9D";
    public static String docid = "";
    public static String title = "";
    public static String pic = "";


//    和平精英名称(黑狂)
    public static String RoleName = "%E7%88%B5%E5%A3%AB%E6%B1%89%E5%85%8B673";
    public static String RoleId = "4666097377";
    public static String lRoleId = "5000000000003470623";
    public static String rolename = "%E7%AC%AC%E4%B8%80%E7%BB%9D%E5%AF%B9%E4%B8%8D%E8%83%BD%E6%B0%AA%E9%87%91";

    public static String sKeyId = "";

    //    (第一绝对不能氪金)
    public static String info = "%7B%22gameName%22%3A%22%E5%91%BD%E8%BF%90%E6%96%B9%E8%88%9F%22%2C%22bizCode%22%3A%22fz%22%2C%22roleName%22%3A%22%E7%AC%AC%E4%B8%80%E7%BB%9D%E5%AF%B9%E4%B8%8D%E8%83%BD%E6%B0%AA%E9%87%91%22%2C%22roleCode%22%3A%225000000000003470623%22%2C%22accountId%22%3A%22%22%2C%22type%22%3A0%2C%22isHasService%22%3A0%2C%22areaName%22%3A%22%E5%AE%89%E5%BF%86%E8%B0%B7%22%2C%22areaID%22%3A%222%22%2C%22serviceName%22%3A%22%E5%8D%A2%E4%BD%A9%E6%81%A9%22%2C%22serviceID%22%3A%22%22%2C%22systemKey%22%3A%22pc%22%2C%22systemID%22%3A%222%22%2C%22channelKey%22%3A%22%22%2C%22channelID%22%3A%2250%22%2C%22channelName%22%3A%22%E5%8D%A2%E4%BD%A9%E6%81%A9%22%2C%22ext_param%22%3A%22%22%2C%22version%22%3A3%2C%22platid%22%3A%222%22%2C%22area%22%3A%2250%22%2C%22partition%22%3A%222%22%2C%22showAreaName%22%3A%22%E5%AE%89%E5%BF%86%E8%B0%B7%22%7D";

    public static String sendPost() {
        //        获取年月
        Calendar instance = Calendar.getInstance();
        String year = String.valueOf(instance.get(Calendar.YEAR));
        int month = instance.get(Calendar.MONTH) + 1;
        String months = "";
        if (month<=9){
            months = "0" + month;
        }
        String months1 = String.valueOf(year + months);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            String time = String.valueOf(System.currentTimeMillis());
            URL realUrl = new URL("https://comm.ams.game.qq.com/ams/ame/amesvr?ameVersion=0.3&sServiceType=dj&iActivityId=11117&sServiceDepartment=djc&set_info=newterminals&w_ver=20&w_id=149&appSource=android&appVersion=148&ch=10010&sDeviceID=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b&osVersion=Android-29&p_tk=" + p_tk +"&sVersionName=v4.7.2.0");
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2029&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            conn.setRequestProperty("charset", "UTF-8");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("cookie", "djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            String param ="djcRequestId=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b-"+time+"-"+new Random().nextInt(1000)+"&appVersion=148&sign_version=1.0&ch=10010&iActivityId=11117&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&sDeviceID=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b&p_tk=" + p_tk + "&month=" + months1 + "&osVersion=Android-29&iFlowId=96939&sVersionName=v4.7.2.0&sServiceDepartment=djc&sServiceType=dj&appSource=android&g_tk=1842395457";
//            System.out.println(param);
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }


    //连续签到三天任务
    public static String sendsantian() {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            String time = String.valueOf(System.currentTimeMillis());
//
            URL realUrl = new URL("https://comm.ams.game.qq.com/ams/ame/amesvr?ameVersion=0.3&sServiceType=dj&iActivityId=11117&sServiceDepartment=djc&set_info=newterminals&w_ver=30&w_id=45&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&osVersion=Android-29&p_tk=165797542&sVersionName=v4.7.2.0");
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2029&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            conn.setRequestProperty("charset", "UTF-8");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("cookie", "djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; openid=054261307B42A3D14C07D9C74CBB6366; access_token=72825BA3333473975345A3C995E3AB9D");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            String param ="djcRequestId=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b-"+time+"-"+new Random().nextInt(1000)+"&appVersion=148&ch=10010&iActivityId=11117&sDjcSign=" + bytesToHex3(toRSA(AES("054261307B42A3D14C07D9C74CBB6366+36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&sDeviceID=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b&p_tk=165797542&osVersion=Android-29&iFlowId=322021&sVersionName=v4.7.2.0&sServiceDepartment=djc&sServiceType=dj&appSource=android&g_tk=1842395457";
//            System.out.println(param);
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }



    public static String app_activity_list() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.activity.list&iAppId=1001&_app_id=1001&act_type=38467&origin_page=fullPage&page_size=30&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_task_report() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.task.report&iAppId=1001&_app_id=1001&task_type=activity_center&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_act_doc_today() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String str = dateFormat.format(new Date());
            String encodeddate = java.net.URLEncoder.encode(dateFormat.format(new Date()), "UTF-8");

            Calendar instance = Calendar.getInstance();
            String hour3  = String.valueOf(instance.get(Calendar.HOUR_OF_DAY));
            String minute3 = String.valueOf(instance.get(Calendar.MINUTE));
            int second3 = instance.get(Calendar.SECOND);
            String second4 = String.valueOf(second3);
            if (second3<=9){
                second4 = "0" + second3;
            }

            String hms = hour3 + ":" + minute3 + ":" + second4;

            String encodeddate1 = java.net.URLEncoder.encode(hms, "UTF-8");
            String end = encodeddate + "%20" + encodeddate1;

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.act_doc.today&iAppId=1001&_app_id=1001&since=" + end + "&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" +p_tk+ "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_biz_list_recent() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.biz.list.recent&iAppId=1001&_app_id=1001&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES( openid+ "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_act_doc_calendar_recommend() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.act_doc.calendar_recommend&iAppId=1001&_app_id=1001&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_act_doc_today_1() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.act_doc.today&iAppId=1001&_app_id=1001&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES( openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String activity_center_receive() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=welink.usertask.swoole&optype=receive_usertask&_app_id=1001&output_format=json&iAppId=1001&_app_id=1001&iruleId=100040&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }




    public static String app_activity_comment_list() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.activity.comment_list&iAppId=1001&_app_id=1001&docid=" + docid + "&page=1&_biz_code=actdaoju&sort=ai_djc_release_score_int&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_activity_detail() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.activity.detail&iAppId=1001&_app_id=1001&actId=" + docid+ "&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk +"&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_act_doc_raffle() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());
            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.act_doc.raffle&iAppId=1001&_app_id=1001&act_doc_id=" +docid + "&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES( openid+ "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String welink_msg_user_rss() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=welink.msg.user.rss&opt=0&_app_id=1001&iAppId=1001&_app_id=1001&iTypeId=2&output_format=json&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES( openid+ "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_act_doc_viewer_list() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());
            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.act_doc.viewer_list&iAppId=1001&_app_id=1001&act_doc_id=" + docid +"&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String sendGetliulan4 () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.act_doc.raffle&iAppId=1001&_app_id=1001&act_doc_id=948035&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=165797542&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES("054261307B42A3D14C07D9C74CBB6366+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; openid=054261307B42A3D14C07D9C74CBB6366; access_token=72825BA3333473975345A3C995E3AB9D");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_team_text () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.team.text&iAppId=1001&_app_id=1001&act_doc_id=" + docid + "&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(  openid+"+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_biz_client_scene_txt () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.biz.client_scene_txt&iAppId=1001&_app_id=1001&page_id=113&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk +"&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_act_doc_vote () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.act_doc.vote&iAppId=1001&_app_id=1001&act_doc_id=" + docid +"&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_praise_list () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.praise.list&&weexVersion=0.28.0.1&platform=android&deviceModel=MI%2B8&&&cate_id=6&article_ids=" + docid + "&biz=dj&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk +"&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String sendGetliulan7 () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.praise.list&&weexVersion=0.28.0.1&platform=android&deviceModel=MI%2B8&&&cate_id=6&article_ids=948067&biz=dj&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=165797542&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES("054261307B42A3D14C07D9C74CBB6366+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; openid=054261307B42A3D14C07D9C74CBB6366; access_token=72825BA3333473975345A3C995E3AB9D");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_activity_assistance_assistance_zt_list () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.activity.assistance.assistance_zt_list&iAppId=1001&_app_id=1001&iActionDocId=" + docid + "&stype=2&_biz_code=actdaoju&sBizCode=actdaoju&page=1&serviceType=assistanceType3&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_acttalk_mark () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.acttalk.mark&iAppId=1001&_app_id=1001&actids=" + docid + "&opt=0&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk +"&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String sendGetliulan10 () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.act_doc.raffle&iAppId=1001&_app_id=1001&act_doc_id=948035&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=165797542&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES("054261307B42A3D14C07D9C74CBB6366+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; openid=054261307B42A3D14C07D9C74CBB6366; access_token=72825BA3333473975345A3C995E3AB9D");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_act_doc_act_recommend () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.act_doc.act_recommend&iAppId=1001&_app_id=1001&act_doc_id=" +docid + "&_biz_code=actdaoju&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_feeds_userrecordV2 () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.feeds.userrecordV2&iAppId=1001&_app_id=1001&id=948035&title=" +title + "&pic=" + pic + "&_biz_code=actdaoju&record_type=activity&comment_num=100&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_praise_user_like_list () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());
            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.praise.user_like_list&iAppId=1001&_app_id=1001&article_ids=5105537997789013628%2C15532536378946099921%2C15731921994808889827%2C10764267809538973062%2C14393393534473116719%2C6808844701297893543%2C11799855124158952736%2C3360769661799629807%2C12826749511348852862%2C13326322649295127369&cate_id=4&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_global_hot_upgrade () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.global.hot_upgrade&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_square_msg_query () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.square_msg.query&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&max_msgid=0&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&s_type=query&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String welink_msg_user_info () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=welink.msg.user.info&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&needBind=1&ch=10010&minBindMsgId=0&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&p_tk=" + p_tk + "&osVersion=Android-29&app_set=1001&sVersionName=v4.7.2.0&appSource=android");

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String app_reddot_home () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.reddot.home&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&needBind=1&ch=10010&minBindMsgId=0&sDjcSign=" + bytesToHex3(toRSA(AES(  openid +"+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&p_tk=" + p_tk + "&osVersion=Android-29&app_set=1001&sVersionName=v4.7.2.0&appSource=android");

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String sendGetliulan21 () {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.task.report&iAppId=1001&_app_id=1001&task_type=activity_detail&w_ver=35&w_id=113&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&needBind=1&ch=10010&minBindMsgId=0&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade106471fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&p_tk=" + p_tk +"&osVersion=Android-29&app_set=1001&sVersionName=v4.7.2.0&appSource=android");

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }





    public static String sendlingqu3() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=welink.usertask.swoole&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&ch=10010&_app_id=1001&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&optype=receive_usertask&p_tk=" + p_tk + "&output_format=json&osVersion=Android-29&sVersionName=v4.7.2.0&iruleId=327102&appSource=android");

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String xuyuanlingqu() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=welink.usertask.swoole&iAppId=1001&_app_id=1001&optype=receive_usertask&output_format=json&iruleId=302124&w_ver=30&w_id=45&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String small_treasure_chest() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=welink.usertask.swoole&optype=receive_usertask&_app_id=1001&output_format=json&iAppId=1001&_app_id=1001&iruleId=100001&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk +"&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String big_treasure_chest() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=welink.usertask.swoole&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&ch=10010&_app_id=1001&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&optype=receive_usertask&p_tk=" + p_tk +"&output_format=json&osVersion=Android-29&sVersionName=v4.7.2.0&iruleId=100002&appSource=android");

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String xuyuan() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.goods.buy_precheck&iAppId=1001&_app_id=1001&biz=lol&getRoleId=2278359167&getRoleName=%E4%BC%8F%E9%AD%94%E8%80%97%E5%91%BD&propid=40826&p_tk=165797542&areaid=2&getUin=141617956683655&w_ver=16&w_id=74&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=165797542&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES("054261307B42A3D14C07D9C74CBB6366+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; openid=054261307B42A3D14C07D9C74CBB6366; access_token=72825BA3333473975345A3C995E3AB9D");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String xuyuan1() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.demand.create&iAppId=1001&_app_id=1001&iActionId=3&iGoodsId=40826&sBizCode=lol&p_tk=165797542&iZoneId=2&sRoleId=3341776223839712&sRoleName=%E4%BC%8F%E9%AD%94%E8%80%97%E5%91%BD&sGetterDream=%E4%B8%93%E6%B3%A8%E5%9C%9F%E8%B1%AA%E5%A4%A7%E8%85%BF%E4%BA%8C%E5%8D%81%E5%B9%B4%EF%BC%8C%E5%B0%B1%E6%98%AF%E8%BF%99%E4%B9%88%E7%9A%84%E4%B8%93%E4%B8%9A~&w_ver=16&w_id=74&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=165797542&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES("054261307B42A3D14C07D9C74CBB6366+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; openid=054261307B42A3D14C07D9C74CBB6366; access_token=72825BA3333473975345A3C995E3AB9D");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    public static String hpxuyuan() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.goods.buy_precheck&iAppId=1001&_app_id=1001&biz=lol&getRoleId=2278359167&getRoleName=%E4%BC%8F%E9%AD%94%E8%80%97%E5%91%BD&propid=40826&p_tk=" + p_tk +"&areaid=2&getUin=141617956683655&w_ver=16&w_id=74&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES( openid+ "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String hpxuyuan1() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.demand.create&iAppId=1001&_app_id=1001&iActionId=3&iGoodsId=22154&sBizCode=cjm&p_tk=" + p_tk + "&iZoneId=2&platid=1&sZoneDesc=%E6%89%8BQ&sRoleId=" + RoleId + "&sRoleName=" + RoleName + "&sGetterDream=%E4%B8%93%E6%B3%A8%E5%9C%9F%E8%B1%AA%E5%A4%A7%E8%85%BF%E4%BA%8C%E5%8D%81%E5%B9%B4%EF%BC%8C%E5%B0%B1%E6%98%AF%E8%BF%99%E4%B9%88%E7%9A%84%E4%B8%93%E4%B8%9A~&w_ver=16&w_id=74&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES( openid+ "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String delxuyuan() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.demand.delete&output_format=jsonp&iAppId=1001&_app_id=1001&sKeyId=" + sKeyId + "&w_ver=2&w_id=89&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES( openid+ "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    public static String jiaoyipai1() {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            String time = String.valueOf(System.currentTimeMillis());
//
            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.role.bind_action&_biz_code=fz&output_format=json&w_ver=156&w_id=4&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&osVersion=Android-29&p_tk=" + p_tk + "&sVersionName=v4.7.2.0");
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2029&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            conn.setRequestProperty("charset", "UTF-8");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("cookie", "djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            String param ="sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b-"+time+"-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&role_info=" + info + "&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&sDeviceID=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b&p_tk=" + p_tk + "&osVersion=Android-29&iFlowId=322021&sVersionName=v4.7.2.0&sServiceDepartment=djc&sServiceType=dj&appSource=android&g_tk=1842395457";
//            System.out.println(param);
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String jiaoyipai2() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.goods.buy_precheck&iAppId=1001&_app_id=1001&biz=lol&getRoleId=2278359167&getRoleName=%E4%BC%8F%E9%AD%94%E8%80%97%E5%91%BD&propid=40826&p_tk=165797542&areaid=2&getUin=141617956683655&w_ver=16&w_id=74&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=165797542&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES("054261307B42A3D14C07D9C74CBB6366+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String jiaoyipai3() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=buy.plug.swoole.judou&iAppId=1001&_app_id=1003&_output_fmt=1&_plug_id=9800&_from=app&iGoodsSeqId=4375&iActionId=29657&iActionType=26&_biz_code=fz&biz=fz&platid=2&iZone=50&partition=2&lRoleId=" + lRoleId + "&rolename=" + rolename + "&p_tk=" + p_tk + "&_cs=2&w_ver=156&w_id=4&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=465636007&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(  openid + "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String unicodeDecode(String string) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(string);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            string = string.replace(matcher.group(1), ch + "");
        }
        return string;
    }


    public static String sendlingdou(){
        //        获取年月
        Calendar instance = Calendar.getInstance();
        String year = String.valueOf(instance.get(Calendar.YEAR));
        int month = instance.get(Calendar.MONTH) + 1;
        String months = "";
        if (month<=9){
            months = "0" + month;
        }
        String months1 = String.valueOf(year + months);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            String time = String.valueOf(System.currentTimeMillis());
//
            URL realUrl = new URL("https://comm.ams.game.qq.com/ams/ame/amesvr?ameVersion=0.3&sServiceType=dj&iActivityId=11117&sServiceDepartment=djc&set_info=newterminals&w_ver=30&w_id=45&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&osVersion=Android-29&p_tk=" + p_tk + "&sVersionName=v4.7.2.0");
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2029&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            conn.setRequestProperty("charset", "UTF-8");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("cookie", "djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            String param ="djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-"+time+"-"+new Random().nextInt(1000)+"&appVersion=148&sign_version=1.0&ch=10010&iActivityId=11117&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&p_tk=" + p_tk + "&month=" + months1 + "&osVersion=Android-29&iFlowId=96938&sVersionName=v4.7.2.0&sServiceDepartment=djc&sServiceType=dj&appSource=android&g_tk=1842395457";
//            System.out.println(param);
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    public static String sendlingdou0(){
        //        获取年月
        Calendar instance = Calendar.getInstance();
        String year = String.valueOf(instance.get(Calendar.YEAR));
        int month = instance.get(Calendar.MONTH) + 1;
        String months = "";
        if (month<=9){
            months = "0" + month;
        }
        String months1 = String.valueOf(year + months);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            String time = String.valueOf(System.currentTimeMillis());
//
            URL realUrl = new URL("https://comm.ams.game.qq.com/ams/ame/amesvr?iActivityId=540074&iFlowId=933072&g_tk=1842395457&sServiceType=dj&sServiceDepartment=djc&w_ver=30&w_id=45&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&osVersion=Android-29&p_tk=" + p_tk + "&sVersionName=v4.7.2.0");
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2029&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            conn.setRequestProperty("charset", "UTF-8");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("cookie", "djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            String param ="djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-"+time+"-"+new Random().nextInt(1000)+"&appVersion=148&ch=10010&iActivityId=540074&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&p_tk=" + p_tk + "&osVersion=Android-29&iFlowId=933072&sVersionName=v4.7.2.0&sServiceDepartment=djc&sServiceType=dj&appSource=android&g_tk=1842395457";
//            System.out.println(param);
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    public static String sendlingdou2(){
        //        获取年月
        Calendar instance = Calendar.getInstance();
        String year = String.valueOf(instance.get(Calendar.YEAR));
        int month = instance.get(Calendar.MONTH) + 1;
        String months = "";
        if (month<=9){
            months = "0" + month;
        }
        String months1 = String.valueOf(year + months);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            String time = String.valueOf(System.currentTimeMillis());
//
            URL realUrl = new URL("https://comm.ams.game.qq.com/ams/ame/amesvr?ameVersion=0.3&sServiceType=dj&iActivityId=11117&sServiceDepartment=djc&set_info=newterminals&w_ver=30&w_id=45&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&osVersion=Android-29&p_tk=" + p_tk + "&sVersionName=v4.7.2.0");
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2029&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            conn.setRequestProperty("charset", "UTF-8");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("cookie", "djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            String param ="djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-"+time+"-"+new Random().nextInt(1000)+"&appVersion=148&sign_version=1.0&ch=10010&iActivityId=11117&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&p_tk=" + p_tk +"&month=" + months1 + "&osVersion=Android-29&iFlowId=96938&sVersionName=v4.7.2.0&sServiceDepartment=djc&sServiceType=dj&appSource=android&g_tk=1842395457";
//            System.out.println(param);
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    public static String sendlingdou3(){
        //        获取年月
        Calendar instance = Calendar.getInstance();
        String year = String.valueOf(instance.get(Calendar.YEAR));
        int month = instance.get(Calendar.MONTH) + 1;
        String months = "";
        if (month<=9){
            months = "0" + month;
        }
        String months1 = String.valueOf(year + months);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            String time = String.valueOf(System.currentTimeMillis());
//
            URL realUrl = new URL("https://comm.ams.game.qq.com/ams/ame/amesvr?ameVersion=0.3&sServiceType=dj&iActivityId=11117&sServiceDepartment=djc&set_info=newterminals&w_ver=30&w_id=45&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&osVersion=Android-29&p_tk=" + p_tk + "&sVersionName=v4.7.2.0");
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2029&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            conn.setRequestProperty("charset", "UTF-8");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("cookie", "djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            String param ="djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-"+time+"-"+new Random().nextInt(1000)+"&appVersion=148&sign_version=1.0&ch=10010&iActivityId=11117&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&p_tk=" + p_tk + "&month=" + months1 + "&osVersion=Android-29&iFlowId=96939&sVersionName=v4.7.2.0&sServiceDepartment=djc&sServiceType=dj&appSource=android&g_tk=1842395457";
//            System.out.println(param);
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    public static String sendlingdou4(){
        //        获取年月
        Calendar instance = Calendar.getInstance();
        String year = String.valueOf(instance.get(Calendar.YEAR));
        int month = instance.get(Calendar.MONTH) + 1;
        String months = "";
        if (month<=9){
            months = "0" + month;
        }
        String months1 = String.valueOf(year + months);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            String time = String.valueOf(System.currentTimeMillis());
//
            URL realUrl = new URL("https://comm.ams.game.qq.com/ams/ame/amesvr?ameVersion=0.3&sServiceType=dj&iActivityId=11117&sServiceDepartment=djc&set_info=newterminals&w_ver=30&w_id=45&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&osVersion=Android-29&p_tk=" + p_tk + "&sVersionName=v4.7.2.0");
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2029&cpu=AArch64 Processor rev 13 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            conn.setRequestProperty("charset", "UTF-8");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("cookie", "djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            String param ="djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-"+time+"-"+new Random().nextInt(1000)+"&appVersion=148&ch=10010&iActivityId=11117&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&p_tk=" + p_tk + "&osVersion=Android-29&iFlowId=324410&sVersionName=v4.7.2.0&sServiceDepartment=djc&sServiceType=dj&appSource=android&g_tk=1842395457";
//            System.out.println(param);
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    public static void sign_in(){
        String lingdou0 = sendlingdou0();
//        System.out.println("签到结果：" + unicodeDecode(lingdou0));
        String lingdou = sendlingdou();
//        System.out.println("签到领取结果：" + unicodeDecode(lingdou));
        String lingdou2 = sendlingdou2();
//        System.out.println("签到领取结果：" + unicodeDecode(lingdou2));
        String end = sendPost();
        System.out.println("签到结果：" + unicodeDecode(end));
        String lingdou3 = sendlingdou3();
//        System.out.println("签到领取结果：" + unicodeDecode(lingdou3));
        String lingdou4 = sendlingdou4();
//        System.out.println("签到领取结果：" + unicodeDecode(lingdou4));
        lingdou4 = sendlingdou4();
//        System.out.println("签到领取结果：" + unicodeDecode(lingdou4));
        lingdou0 = sendlingdou0();
//        System.out.println("签到结果：" + unicodeDecode(lingdou0));
    }

    /*
    打卡活动中心
    领取活动中心奖励
     */
    public static void activity_center(){
        String app_activity_list = app_activity_list();
//        System.out.println("app_activity_list:" + app_activity_list);
        String app_task_report = app_task_report();
//        System.out.println(app_task_report);
        String huodong2 = app_act_doc_today();
//        System.out.println(huodong2);
        String huodong3 = app_biz_list_recent();
//        System.out.println(huodong3);
        String huodong4 = app_act_doc_calendar_recommend();
//        System.out.println(huodong4);
        String huodong5 = app_act_doc_today_1();
//        System.out.println(huodong5);
        String huodong6 = app_activity_list();
//        System.out.println(huodong6);
        String huodong7 = app_activity_list();
//        System.out.println(huodong7);
        String huodong8 = app_activity_list();
//        System.out.println(huodong8);
        String huodong9 = app_activity_list();
//        System.out.println(huodong9);
        String huodong10 = app_activity_list();
//        System.out.println(huodong10);


//        领取活动中心奖励（可以，修改opid和token）
        String huodongjiangl = activity_center_receive();
        System.out.println("打卡活动中心奖励领取结果：" + unicodeDecode(huodongjiangl));
    }

    /*
    浏览三个新活动
     */
    public static void new_3_activity(){
        String liulan17 = app_global_hot_upgrade();
//        System.out.println(liulan17);
        String liulan = app_activity_comment_list();
//        System.out.println(liulan);
        String liulan1 = app_activity_detail();
//        System.out.println(liulan1);
        String liulan2 = app_act_doc_raffle();
//        System.out.println(liulan2);
        String liulan13 = welink_msg_user_rss();
//        System.out.println(liulan13);

        String liulan3 = app_act_doc_viewer_list();
//        System.out.println(liulan3);
        liulan2 = app_act_doc_raffle();
//        System.out.println(liulan2);
        String liulan14 = app_team_text();
//        System.out.println(liulan14);
        String liulan18 = welink_msg_user_info();
//        System.out.println(liulan18);
        String liulan19 = app_square_msg_query();
//        System.out.println(liulan19);
        String liulan20 = app_reddot_home();
//        System.out.println(liulan20);
        String liulan16 = app_praise_user_like_list();
//        System.out.println(liulan16);
        String liulan15 = app_biz_client_scene_txt();
//        System.out.println(liulan15);
        String liulan11 = app_act_doc_act_recommend();
//        System.out.println(liulan11);
        String liulan8 = app_activity_assistance_assistance_zt_list();
//        System.out.println(liulan8);
        String liulan5 = app_act_doc_vote();
//        System.out.println(liulan5);
        String liulan6 = app_praise_list();
//        System.out.println(liulan6);
        liulan6 = app_praise_list();
//        System.out.println(liulan6);
        liulan8 = app_activity_assistance_assistance_zt_list();
//        System.out.println(liulan8);
        liulan2 = app_act_doc_raffle();
//        System.out.println(liulan2);
        String liulan9 = app_acttalk_mark();
//        System.out.println(liulan9);
        String liulan12 = app_feeds_userrecordV2();
//        System.out.println(liulan12);
        liulan = app_activity_comment_list();
//        System.out.println(liulan);
        String liulan21 = sendGetliulan21();
//        System.out.println(liulan21);
    }

    public static String dou3() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());
            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=welink.usertask.swoole&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&ch=10010&_app_id=1001&sDjcSign=" + bytesToHex3(toRSA(AES( openid+ "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&optype=receive_usertask&p_tk=" + p_tk + "&output_format=json&osVersion=Android-29&sVersionName=v4.7.2.0&iruleId=327091&appSource=android");

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String xianshi1(String ruleid) {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=welink.usertask.swoole&optype=report_usertask_rushtime&output_format=json&iAppId=1001&_app_id=1001&ruleid=" + ruleid + "&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=978b0d18e43f6cb7fbed73656180a08dc14d84865eb8c00a18e5251ddfd02c69-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String xianshi2() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.act_doc.today&iAppId=1001&_app_id=1001&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=978b0d18e43f6cb7fbed73656180a08dc14d84865eb8c00a18e5251ddfd02c69-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String xianshi3() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=welink.msg.user.rss&opt=0&_app_id=1001&iAppId=1001&_app_id=1001&iTypeId=2&output_format=json&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=978b0d18e43f6cb7fbed73656180a08dc14d84865eb8c00a18e5251ddfd02c69-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String xianshi4() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=app.activity.list&iAppId=1001&_app_id=1001&act_type=38467&page_size=30&origin_page=calendar&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=978b0d18e43f6cb7fbed73656180a08dc14d84865eb8c00a18e5251ddfd02c69-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String lingquxianshi1(String ruleid) {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=welink.usertask.swoole&optype=receive_usertask&_app_id=1001&output_format=json&iAppId=1001&_app_id=1001&iruleId=" + ruleid + "&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=978b0d18e43f6cb7fbed73656180a08dc14d84865eb8c00a18e5251ddfd02c69-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("user-agent", "TencentDaojucheng=v4.7.2.0&appSource=android&appVersion=148&ch=10010&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&firmwareVersion=10&phoneBrand=Xiaomi&phoneVersion=MI+8&displayMetrics=1080 * 2115&cpu=AArch64 Processor rev 12 (aarch64)&net=wifi&sVersionName=v4.7.2.0&plNo=133");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("referer","https://daoju.qq.com/index.shtml");
            connection.setRequestProperty("cookie","djc_appSource=android; djc_appVersion=148; acctype=qc; appid=1101958653; " + open_access);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static void xianshi(String ruleid){
        String xianshi1 = xianshi1(ruleid);
//        System.out.println("xianshi1" + xianshi1);
        String xianshi2 = xianshi2();
//        System.out.println("xianshi2" + xianshi2);
        String xianshi3 = xianshi3();
//        System.out.println("xianshi3" + xianshi3);
        String xianshi4 = xianshi4();
//        System.out.println("xianshi4" + xianshi4);
        String lingquxianshi1 = lingquxianshi1(ruleid);
        System.out.println("限时活动领取结果：" + lingquxianshi1);
    }


    public static void main(String[] args) throws Exception {

//access_token的有效期为90天，有了access_token就有openid
//拼接字符串第一个参数为openid

//        签到可以用
        sign_in();
//

        //        浏览限时活动
        xianshi("600088");
        xianshi("600089");
        xianshi("600090");
        xianshi("600091");


//        打卡活动中心（p_tk必须要）
        activity_center();

//        累计三天签到奖励（完成）
//        String santian = sendsantian();
//        System.out.println(santian);

//      浏览三个新活动
        String liulan = app_activity_list();
        JSONObject js = JSON.parseObject(liulan);
        JSONObject jsonObj = js.getJSONObject("data");
        JSONArray jsonObj1 = jsonObj.getJSONArray("list");
        for (int i = 0;i < 3;i++){
            String jsarry = jsonObj1.getString(i);
            JSONObject js2 = JSON.parseObject(jsarry);
            docid = js2.getString("iInfoId");
            title = java.net.URLEncoder.encode(js2.getString("sTitle"), "UTF-8");
            pic = java.net.URLEncoder.encode(js2.getString("sChannelIcon"), "UTF-8");
            new_3_activity();

//            docid = "948040";
//            new_3_activity();
//
//            docid = "948076";
//            new_3_activity();
        }

//        领取浏览三个任务奖励
        String lingqu3 = sendlingqu3();
        System.out.println("浏览三个新活动奖励领取结果:" + unicodeDecode(lingqu3));

//        打开第一个宝箱
        String baoxiang1 = small_treasure_chest();
        System.out.println("打开小宝箱领取结果：" + unicodeDecode(baoxiang1));
//

//        许愿(LOL许愿英雄)
//        String xuyuan = xuyuan();
//        System.out.println(xuyuan);
//        String xuyuan1 = xuyuan1();
//        System.out.println(xuyuan1);

//        许愿(和平精英)
//        String hpxuyuan = hpxuyuan();
//        System.out.println(hpxuyuan);


        String hpxuyuan1 = hpxuyuan1();
        System.out.println("许愿结果：" + unicodeDecode(hpxuyuan1));

        JSONObject jsonObject = JSON.parseObject(hpxuyuan1);
        String data = jsonObject.getString("data");
        JSONObject jsonObject1 = JSON.parseObject(data);
        sKeyId = jsonObject1.getString("sKeyId");



//        删除许愿()
        String del = delxuyuan();
        System.out.println("删除许愿结果" + unicodeDecode(del));
//

//      许愿奖励领取
        String xuyuanlingqu = xuyuanlingqu();
        System.out.println("许愿奖励领取结果：" + unicodeDecode(xuyuanlingqu));
//

//        第二个宝箱
        String baoxiang2 = big_treasure_chest();
        System.out.println("大宝箱领取结果：" + unicodeDecode(baoxiang2));
//

//        兑换交易牌
        String jiaoyipai1 = jiaoyipai1();
//        System.out.println(jiaoyipai1);
        String jiaoyipai3 = jiaoyipai3();
        System.out.println("交易牌兑换结果:" + unicodeDecode(jiaoyipai3));
//
//

//        兑换三个豆
        String dou3 = dou3();
        System.out.println("兑换有礼结果:" + unicodeDecode(dou3));


        int j = 0;
    }
}

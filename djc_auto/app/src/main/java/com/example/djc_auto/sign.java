package com.example.djc_auto;

import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
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

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class sign {


    public static String docid = "";
    public static String title = "";
    public static String pic = "";
    public static String sKeyId = "";

    public static String p_tk = "";
    public static String openid = "";
    public static String open_access = "";
    //    和平精英（名称id）
    public static String RoleName = "";
    public static String RoleId = "";
    //    兑换交易牌id和名称
    public static String lRoleId = "";
        public static String lRolename = "";
    public static String role_info = "";
    public static String user = "";

    static AssetManager assetManager = null;

    public sign(String p_tk,String openid,String open_access,String RoleName,String RoleId,String lRoleId,String lRolename,String role_info,String user,AssetManager assetManager){
        sign.p_tk = p_tk;
        sign.openid = openid;
        sign.open_access = open_access;
        sign.RoleName = RoleName;
        sign.RoleId = RoleId;
        sign.lRoleId = lRoleId;
        sign.lRolename = lRolename;
        sign.role_info = role_info;
        sign.user = user;
        sign.assetManager = assetManager;
    }


    private static PublicKey RSA() {
        byte[] bArr;
        Exception e;
        try {
            InputStream open = assetManager.open("djc_rsa_public_key_new.der");
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


    /*
    * 签到
    */
    public static String sendSign() {
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
            String param ="djcRequestId=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b-"+time+"-"+new Random().nextInt(1000)+"&appVersion=148&sign_version=1.0&ch=10010&iActivityId=11117&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&sDeviceID=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b&p_tk=" + p_tk + "&month=" + months1 +"&osVersion=Android-29&iFlowId=96939&sVersionName=v4.7.2.0&sServiceDepartment=djc&sServiceType=dj&appSource=android&g_tk=1842395457";
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
    public static String sign_legumes_0(){
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
    public static String sign_legumes_1(){
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
    public static String sign_legumes_2(){
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
    public static String sign_legumes_3(){
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
    public static String sign_legumes_4(){
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
    public static String sign_in(){
        Log.d("djc",sign_legumes_1());
        Log.d("djc",sign_legumes_0());
        Log.d("djc",sign_legumes_2());
        String end = sendSign();
        Log.d("djc",sign_legumes_3());
        Log.d("djc",sign_legumes_4());
        Log.d("djc",sign_legumes_4());
        Log.d("djc",sign_legumes_1());
        Log.d("djc签到结果：",unicodeDecode(end));
        String msg = "";
        try{
            JSONObject signjson = new JSONObject(unicodeDecode(end));
            JSONObject modRet = signjson.getJSONObject("modRet");
            msg = modRet.getString("msg");
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }
    public String sign(){
        String signend = "";
        String sign_in_end = sign_in();
        signend += sign_in_end;
//        return user + "\n" + p_tk + "\n" + openid + "\n" + open_access + "\n" + RoleName + "\n" + RoleId + "\n" + lRoleId + "\n" + lRolename + "\n" + role_info + "\n";
        return user + "\n" + "签到结果：" + signend + "\n";
    }


    /*
    * 限时活动
    */
    public static String Limited_time_1(String ruleid) {
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
    public static String Limited_time_2() {
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
    public static String Limited_time_3() {
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
    public static String Limited_time_4() {
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
    public static String receive_Limited_time(String ruleid) {
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
    public static String xianshi(String ruleid){
        Log.d("djc",Limited_time_1(ruleid));
        Log.d("djc",Limited_time_2());
        Log.d("djc",Limited_time_3());
        Log.d("djc",Limited_time_4());
        String receive = receive_Limited_time(ruleid);
        String Limited = "";
        try{
            JSONObject jsonObject = new JSONObject(receive);
            Limited = jsonObject.getString("msg");
        }catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println("限时活动领取结果：" + lingquxianshi1);
        return Limited;
    }
    public String Limited_time(String ruleid){
        //        浏览限时活动
        String Limited_time_end = xianshi(ruleid);
        return Limited_time_end;
    }

    /*
    * 打卡活动中心
    */
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
    public static String activity_center(){
        Log.d("djc",app_activity_list());
        Log.d("djc",app_task_report());
        Log.d("djc",app_act_doc_today());
        Log.d("djc",app_biz_list_recent());
        Log.d("djc",app_act_doc_calendar_recommend());
        Log.d("djc",app_act_doc_today_1());
        Log.d("djc",app_activity_list());
        Log.d("djc",app_activity_list());
        Log.d("djc",app_activity_list());
        Log.d("djc",app_activity_list());
        Log.d("djc",app_activity_list());


//        领取活动中心奖励
        String activity_center_receive = activity_center_receive();
        String activity_center_receive_end = "";
        try {
            JSONObject jsonObject = new JSONObject(activity_center_receive);
            activity_center_receive_end = jsonObject.getString("msg");
        }catch (Exception e){
            e.printStackTrace();
        }
        return activity_center_receive_end;
//        System.out.println("打卡活动中心奖励领取结果：" + unicodeDecode(huodongjiangl));
    }



    /*
    * 浏览三个新活动
    */
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
    public static String app_task_report_21 () {
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

    public static String receive_3_activity() {
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
    public static void run_new_3_activity(){
        Log.d("djc",app_global_hot_upgrade());
        Log.d("djc",app_activity_comment_list());
        Log.d("djc",app_activity_detail());
        Log.d("djc",app_act_doc_raffle());
        Log.d("djc",welink_msg_user_rss());
        Log.d("djc",app_act_doc_viewer_list());
        Log.d("djc",app_act_doc_raffle());
        Log.d("djc",app_team_text());
        Log.d("djc",welink_msg_user_info());
        Log.d("djc",app_square_msg_query());
        Log.d("djc",app_reddot_home());
        Log.d("djc",app_praise_user_like_list());
        Log.d("djc",app_biz_client_scene_txt());
        Log.d("djc",app_act_doc_act_recommend());
        Log.d("djc",app_activity_assistance_assistance_zt_list());
        Log.d("djc",app_act_doc_vote());
        Log.d("djc",app_praise_list());
        Log.d("djc",app_praise_list());
        Log.d("djc",app_activity_assistance_assistance_zt_list());
        Log.d("djc",app_act_doc_raffle());
        Log.d("djc",app_acttalk_mark());
        Log.d("djc",app_feeds_userrecordV2());
        Log.d("djc",app_activity_comment_list());
        Log.d("djc",app_task_report_21());
    }
    public static String new_3_activity(){
        String end_3_activity = "";
        try{
            //      浏览三个新活动
            String liulan = app_activity_list();
            JSONObject js = new JSONObject(liulan);
            JSONObject jsonObj = js.getJSONObject("data");
            JSONArray jsonObj1 = jsonObj.getJSONArray("list");
            for (int i = 0;i < 3;i++){
                String jsarry = jsonObj1.getString(i);
                JSONObject js2 = new JSONObject(jsarry);;
                docid = js2.getString("iInfoId");
                title = java.net.URLEncoder.encode(js2.getString("sTitle"), "UTF-8");
                pic = java.net.URLEncoder.encode(js2.getString("sChannelIcon"), "UTF-8");
                run_new_3_activity();
//                领取三次任务奖励
                String receive_3_activity = receive_3_activity();
                JSONObject jsonObject = new JSONObject(receive_3_activity);
                end_3_activity  = jsonObject.getString("msg");
//                System.out.println("浏览三个新活动奖励领取结果:" + unicodeDecode(receive_3_activity));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return end_3_activity;
    }


    /*
    * 许愿
    */


    public static String hpwish() {
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
    public String Make_a_wish(){
        String hpxuyuan1 = hpwish();
        String hpwish = "";
        System.out.println("许愿结果：" + unicodeDecode(hpxuyuan1));
        try{
            JSONObject jsonObject = new JSONObject(hpxuyuan1);
            String data = jsonObject.getString("data");
            JSONObject jsonObject1 = new JSONObject(data);
            sKeyId = jsonObject1.getString("sKeyId");

            hpwish = jsonObject.getString("msg");
        }catch (Exception e){
            e.printStackTrace();
        }
        return hpwish;
    }
    /*
    * 删除许愿
    */
    public String Delete_Wish(){
//        删除许愿()
        String del = delxuyuan();
        String delhp = "";
        try{
            JSONObject jsonObject = new JSONObject(del);
            delhp = jsonObject.getString("msg");
        }catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println("删除许愿结果" + unicodeDecode(del));
        return delhp;
    }
    /*
    * 领取许愿奖励
    */
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
    public String receive_Wish(){
        //      许愿奖励领取
        String xuyuanlingqu = xuyuanlingqu();
        String receive_Wish = "";
        try{
            JSONObject jsonObject = new JSONObject(xuyuanlingqu);
            receive_Wish = jsonObject.getString("msg");
        }catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println("许愿奖励领取结果：" + unicodeDecode(xuyuanlingqu));
        return receive_Wish;
    }


    /*
    * 小宝箱领取
    */
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

    public String small(){
        String baoxiang1 = small_treasure_chest();
        String small_treasure = "";
        try{
            JSONObject jsonObject = new JSONObject(baoxiang1);
            small_treasure = jsonObject.getString("msg");
//            System.out.println("打开小宝箱领取结果：" + unicodeDecode(baoxiang1));
        }catch (Exception e){
            e.printStackTrace();
        }
        return small_treasure;
    }
    /*
    * 大宝箱领取
    */
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
    public String big(){
        String baoxiang2 = big_treasure_chest();
        String big_treasure = "";
        try{
            JSONObject jsonObject = new JSONObject(baoxiang2);
            big_treasure = jsonObject.getString("msg");
//            System.out.println("大宝箱领取结果：" + unicodeDecode(baoxiang2));
        }catch (Exception e){
            e.printStackTrace();
        }

        return big_treasure;
    }


    /*
    * 交易牌兑换
    */

    public static String Trading_Cards_1() {
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
            String param ="sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b-"+time+"-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=" + p_tk + "&osVersion=Android-29&role_info=" + role_info + "&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(openid + "+36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&sDeviceID=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b&p_tk=" + p_tk + "&osVersion=Android-29&iFlowId=322021&sVersionName=v4.7.2.0&sServiceDepartment=djc&sServiceType=dj&appSource=android&g_tk=1842395457";
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
    public static String Trading_Cards_2() {
        String result = "";
        BufferedReader in = null;
        try {
            String time = String.valueOf(System.currentTimeMillis());

            URL realUrl = new URL("https://djcapp.game.qq.com/daoju/igw/main/?_service=buy.plug.swoole.judou&iAppId=1001&_app_id=1003&_output_fmt=1&_plug_id=9800&_from=app&iGoodsSeqId=4375&iActionId=29657&iActionType=26&_biz_code=fz&biz=fz&platid=2&iZone=50&partition=2&lRoleId=" + lRoleId + "&rolename=" + lRolename + "&p_tk=" + p_tk + "&_cs=2&w_ver=156&w_id=4&sDeviceID=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a&djcRequestId=5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a-" +time+ "-"+new Random().nextInt(1000)+"&appVersion=148&p_tk=465636007&osVersion=Android-29&ch=10010&sVersionName=v4.7.2.0&appSource=android&sDjcSign=" + bytesToHex3(toRSA(AES(  openid + "+5d73310d28a1a02929aea12667e64edb0dd356c592261249d47bf75e134efe2a+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))));

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
    public String Trading_Cards(){
        String Trading_Cards = "";
        String jiaoyipai1 = Trading_Cards_1();
        String jiaoyipai3 = Trading_Cards_2();
        try{
            JSONObject jsonObject = new JSONObject(jiaoyipai3);
            Trading_Cards = jsonObject.getString("msg");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("交易牌兑换结果:" + unicodeDecode(jiaoyipai3));
        return Trading_Cards;
    }

    /*
    * 兑换有礼
    */
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
    public String exchange(){
        String dou3 = dou3();
        String exchange = "";
        try{
            JSONObject jsonObject = new JSONObject(dou3);
            exchange = jsonObject.getString("msg");
        }catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println("兑换有礼结果:" + unicodeDecode(dou3));
        return exchange;
    }


}

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

    public static String p_tk = "271186872";
    public static String openid = "EC765EA3A44E7224CE2E9A4DC73997C9";
    public static String open_access = "openid=EC765EA3A44E7224CE2E9A4DC73997C9; access_token=C442B243CA4F3F930668219C9880B282";
    public static String docid = "";
    public static String title = "";
    public static String pic = "";
    //    和平精英名称（浪仔）
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
    public static String bytesToHex(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for(byte b : bytes) { // 使用String的format方法进行转换
            buf.append(String.format("%02x", Integer.valueOf(b & 0xff)));
        }

        return buf.toString();
    }
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
            String param ="djcRequestId=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b-"+time+"-"+new Random().nextInt(1000)+"&appVersion=148&sign_version=1.0&ch=10010&iActivityId=11117&sDjcSign=" + bytesToHex(toRSA(AES(openid + "+36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b+" + time + "+" + new Random().nextInt(1000),"se35d32s63r7m23m"))) + "&sDeviceID=36faffea6eade625598fd1074a63f01d4bdf26e4d42a62689277877f55428e0b&p_tk=" + p_tk + "&month=" + months1 +"&osVersion=Android-29&iFlowId=96939&sVersionName=v4.7.2.0&sServiceDepartment=djc&sServiceType=dj&appSource=android&g_tk=1842395457";
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

    public static void main(String arg[]){
        System.out.println(unicodeDecode(sendPost()));
    }
}


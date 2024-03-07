package com.example.djc_auto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.JsonReader;
import android.util.Log;
import android.Manifest;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class MainActivity extends AppCompatActivity {
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

    public static String textViewString = "";
    TextView textView = null;
    Button button = null;
    private Handler handler = null;
    private Handler handler1 = null;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_WIFI_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setColor();
        setContentView(R.layout.activity_main);
        handler=new Handler();
        handler1=new Handler();
//        动态申请权限
        init();
        button = findViewById(R.id.sign);
        textView = findViewById(R.id.textView);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("正在进行自动流程");
                button.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //        读文件并保存
                        readFileAndSign("/data/local/tmp/sign.config");

                    }
                }).start();
            }
        });

    }

    //修改状态栏为白色，并把状态栏图标修改为黑色
    public void setColor(){
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }
    public void init(){
        if ((ContextCompat.checkSelfPermission(this,PERMISSIONS_STORAGE[1]) == PackageManager.PERMISSION_DENIED)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,PERMISSIONS_STORAGE[1])) {
//                Toast.makeText(this, "若不通过此权限则无法获取内存或sd卡的内容", Toast.LENGTH_LONG).show();
            }
            ActivityCompat.requestPermissions(this, new String[]{PERMISSIONS_STORAGE[1]}, 100);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 100:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "用户成功授予读写权限", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "用户拒绝授予读写权限(不给权限会报错，听劝)", Toast.LENGTH_LONG).show();

                }
        }
    }

    public void readFileAndSign(String file){
        String filePath = file; // 要读取的文件路径

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))){
            String line;
            while ((line = reader.readLine()) != null){
                JSONObject jsonObject = new JSONObject(line);
                user = jsonObject.getString("账号");
                p_tk = jsonObject.getString("p_tk");
                openid = jsonObject.getString("openid");
                open_access = jsonObject.getString("open_access");
                RoleName = jsonObject.getString("RoleName");
                RoleId = jsonObject.getString("RoleId");
                lRoleId = jsonObject.getString("lRoleId");
                lRolename = jsonObject.getString("lRolename");
                role_info = jsonObject.getString("role_info");
                // 获取AssetManager对象
                AssetManager assetManager = getAssets();
                sign sign = new sign(p_tk,openid,open_access,RoleName,RoleId,lRoleId,lRolename,role_info,user,assetManager);
//                签到
                String endString = sign.sign();
                textViewString += endString;
                handler.post(runnableUi);
//                限时活动
                String Limited_time = sign.Limited_time("600088");
                textViewString += "限时任务1：" + Limited_time + "\n";
                String Limited_time_1 = sign.Limited_time("600089");
                textViewString += "限时任务2：" + Limited_time_1 + "\n";
                String Limited_time_2 = sign.Limited_time("600090");
                textViewString += "限时任务3：" + Limited_time_2 + "\n";
                String Limited_time_3 = sign.Limited_time("600091");
                textViewString += "限时任务4：" + Limited_time_3 + "\n";
                handler.post(runnableUi);
                //打卡活动中心
                String activity_center_receive = sign.activity_center();
                textViewString += "打卡活动中心任务：" + activity_center_receive + "\n";
                handler.post(runnableUi);
//               浏览三个活动
                String new_3_activity = sign.new_3_activity();
                textViewString += "打卡3次活动任务：" + new_3_activity + "\n";
                handler.post(runnableUi);
//              许愿
                String Wish = sign.Make_a_wish();
                textViewString += "许愿结果：" + Wish + "\n";
                handler.post(runnableUi);
//               删除许愿
                String Delete_Wish = sign.Delete_Wish();
                textViewString += "删除许愿结果：" + Delete_Wish + "\n";
                handler.post(runnableUi);
//              领取许愿奖励
                String receive_Wish = sign.receive_Wish();
                textViewString += "许愿奖励领取结果：" + receive_Wish + "\n";
                handler.post(runnableUi);
//              小宝箱领取
                String small_treasure = sign.small();
                textViewString += "小宝箱领取结果：" + small_treasure + "\n";
                handler.post(runnableUi);
//              大宝箱领取
                String big_treasure = sign.big();
                textViewString += "大宝箱领取结果：" + big_treasure + "\n";
                handler.post(runnableUi);
//              交易牌领取
                String Trading_Cards = sign.Trading_Cards();
                textViewString += "交易牌领取结果：" + Trading_Cards + "\n";
                handler.post(runnableUi);
//              兑换有礼领取
                String exchange  = sign.exchange();
                textViewString += "兑换有礼领取结果：" + exchange + "\n";
                handler.post(runnableUi);
            }
            handler1.post(runnableUi1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


    // 构建Runnable对象，在runnable中更新界面
    Runnable runnableUi = new Runnable() {
        @Override
        public void run() {
            textView.setText(textViewString);
//            int offset=textView.getLineCount()*textView.getLineHeight();
//            if (offset > textView.getHeight()) {
//                textView.scrollTo(0, offset - textView.getHeight());
//            }
//            Toast.makeText(getBaseContext(),"自动流程全部完成！",Toast.LENGTH_SHORT).show();
        }

    };
    Runnable runnableUi1 = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(getBaseContext(),"自动流程全部完成！",Toast.LENGTH_SHORT).show();
            button.setEnabled(true);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

    };
}

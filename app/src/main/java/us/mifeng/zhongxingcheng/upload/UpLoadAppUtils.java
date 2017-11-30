package us.mifeng.zhongxingcheng.upload;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by user on 2017/10/16.
 */

public class UpLoadAppUtils implements OnDownloadListener {
    private Activity activity;
    private int verCode;
    private ProgressDialog pBar;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private long contentLength;

    public UpLoadAppUtils(Activity activity) {
        this.activity = activity;
    }

    //安装apk
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 2) {
                File file = (File) msg.obj;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Uri contentUri = FileProvider.getUriForFile(activity, "us.mifeng.zhongxingcheng.fileprovider", file);
                    intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
                } else {
                    intent.setDataAndType(Uri.fromFile(file),
                            "application/vnd.android.package-archive");
                }
                activity.startActivity(intent);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    };

    /**
     * 请求服务器
     */
    private void requestUrl(String path, Callback callback) {
        Request request = new Request.Builder()
                .url(path)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 显示升级信息的对话框
     */
    public void showUpdateDialog(final Activity a) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("请升级APP版本");
        builder.setMessage("新版本已发布请下载更新");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    downApk();
                } else {
                    Toast.makeText(activity, "SD卡不可用，请插入SD卡", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //用户点击了取消
                a.finish();
            }
        });
        builder.create().show();
    }

    /**
     * 下载apk文件
     */
    private void downApk() {
        pBar = new ProgressDialog(activity);
        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pBar.setCancelable(false);
        pBar.setTitle("正在下载...");
        pBar.setMessage("请稍候...");
        pBar.setProgress(0);
        pBar.show();
        //apk下载网址
        //http://www.taogt.cn/xth2017929.apk
        requestUrl("http://www.zxhyvip.com/xth2.apk", new Callback() {
            private File file;

            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] bytes = new byte[2048];
                int length = 0;
                FileOutputStream out = null;
                is = response.body().byteStream();
                contentLength = response.body().contentLength();
                if (contentLength > 0) {
                    file = getPath();
                }
                out = new FileOutputStream(file);
                long sum = 0;
                while ((length = is.read(bytes)) != -1) {
                    out.write(bytes, 0, length);
                    sum += length;
                    final int progress = (int) (sum * 1.0f / contentLength * 100);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            onDownloading(progress);
                        }
                    });
                }
                is.close();
                out.close();
                out.flush();
                Message msg = handler.obtainMessage();
                msg.what = 2;
                msg.obj = file;
                handler.sendMessage(msg);
            }
        });

    }


    /**
     * 获取当前版本号
     */
    public int getVersionCode(Activity activity) {
        try {
            PackageManager packageManager = activity.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(activity.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private File getPath() {
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "/zhongxingcheng");//文件夹名
        if (!file.exists()) {
            file.mkdirs();
        }
        File file1;
        file1 = new File(file, "zhongxingcheng.apk");//文件名
        if (!file1.exists()) {
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file1;
    }

    /**
     * 获取当前版本名称
     */
    private String getVersionName(Activity activity) throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = activity.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(activity.getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

    @Override
    public void onDownloadSuccess() {

    }

    @Override
    public void onDownloading(int progress) {
        pBar.setProgress(progress);
    }

    @Override
    public void onDownloadFailed() {

    }


}

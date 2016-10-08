package com.maiml.updatelibrary.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.maiml.updatelibrary.common.CommonCons;
import com.maiml.updatelibrary.utils.SPUtils;

/**
 * 安装下载接收器
 * Created by maimingliang on 2016/8/11.
 */

public class InstallReceiver extends BroadcastReceiver {

    private static final String TAG = "InstallReceiver";


    // 安装下载接收器
    @Override public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
         long downloadApkId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            installApk(context,downloadApkId);
        }
    }

    // 安装Apk
    private void installApk(Context context,long downloadApkId) {

        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            String filePath = CommonCons.APP_FILE_NAME;
            i.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }catch (Exception e){
            Log.e(TAG,"安装失败");
            e.printStackTrace();
        }

//                 获取存储ID
//        long id = (long) SPUtils.get(context, CommonCons.DOWNLOAD_APK_ID_PREFS,-1l);
//
//        if (downloadApkId == id) {
//            DownloadManager dManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//            //Intent install = new Intent(Intent.ACTION_VIEW);
//            Intent install=new Intent(Intent.ACTION_VIEW);
//            Uri downloadFileUri = dManager.getUriForDownloadedFile(downloadApkId);
//
//             if (downloadFileUri != null) {
//                install.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
//                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(install);
//
//            } else {
////                LogUtil.e(TAG, "------------下载失败");
//            }
//        }

    }
}
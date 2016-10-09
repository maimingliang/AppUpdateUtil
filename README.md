# [UpdateDemo](https://github.com/maimingliang/UpdateDemo)

## 简介
使用DownloadManager 下载完 apk 自动提示安装的功能

![这里写图片描述](https://github.com/maimingliang/UpdateDemo/blob/master/recoder.gif)

## 使用

    >compile 'com.maiml.updatedemo:updatelibrary:1.0.0'



### 配置manifest

```xml

      <!-- 写入扩展存储，向扩展卡写入数据， -->
      <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
      <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    
      <!-- SD卡读取权限 -->
      <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
      <uses-permission android:name="android.permission.INTERNET" />
        
      <!-- 广播注册 -->
      <receiver android:name="com.maiml.updatelibrary.receiver.InstallReceiver">
          <intent-filter android:priority="20" >
               <action android:name="android.intent.action.DOWNLOAD_COMPLETE" />
          </intent-filter>
      </receiver>
        
```
 

* 调用 WechatRecoderActivity
```code

       UpdateAppManager.downloadApk(MainActivity.this,version.getUri(),"版本升级","AppName");
       
```
* 如果 targetSdkVersion >= 23
```code

 @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
			/*
			 * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            // 读写权限
            if (addPermission(permissions, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.READ_EXTERNAL_STORAGE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)){
                return true;
            }else{
                permissionsList.add(permission);
                return false;
            }

        }else{
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
    
```
END.
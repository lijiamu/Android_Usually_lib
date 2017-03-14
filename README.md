# Android_Usually_lib
 目前版本处于内测（自己用状态）
 
 导入方式
 allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

compile 'com.github.lijiamu:Android_Usually_lib:1.0'
 主要作用是用于Android 开发的一些工具类，为了节约开发时间和提高效率
 
 1.BaseActivity （方式  继承）
   initWidthAndHeight(): 初始记录 手机宽高（分辨率）   可以通过BaseApplication的getScreen_width（）, getScreen_height(),getStateHeight()获取。
   setIsNeedExit（boolean isNeedExit）: 是否需要启动连按2次关闭Activity
   exitAPP（）： 关闭所有已打开Activity
   关于没数据和没网络，XML 引入布局 sublayout_no_data，sublayout_no_internet  初始化 initNoData（），initrefresh（），setContentView（）设置内容布局；显示ShowNoData（），ShowRefresh（），ShowContent（）
   
   2.F_IOS_Dialog 仿IOS的对话框

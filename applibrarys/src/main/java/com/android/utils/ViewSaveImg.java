package com.android.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.widget.ScrollView;
import android.widget.Toast;


import com.android.Dialog.LoadingDialog;
import com.android.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ViewSaveImg {
    private Context context;
    private ScrollView view;
    private LoadingDialog loadingDialog;
    private String image_path;

    public ViewSaveImg(Context context, ScrollView view,LoadingDialog loadingDialog) {
        this.context = context;
        this.view = view;
        //this.loadingDialog =  new LoadingDialog(false,context, R.style.loading_dialog);
        this.loadingDialog =loadingDialog;
    }

    public Boolean SaveViewToImage(){
        loadingDialog.show();
        try {
            Bitmap bitmap = createViewBitmap(view);
            String path = APPFilePath.getImageSavePath(context)+context.getResources().getString(R.string.app_name)+"/";
            String image_name = System.currentTimeMillis()+".PNG";
            File temp_file = new File(path);
            if(!temp_file.exists()){
                temp_file.mkdirs();
            }
            File file =new File(path ,image_name);
            image_path= file.getPath();
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
            fos.close();

            //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), image_name, null);
            Intent intent1 = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent1.setData(uri);
            context.sendBroadcast(intent1);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            loadingDialog.dismiss();
            Toast.makeText(context,context.getResources().getString(R.string.save_error),Toast.LENGTH_SHORT).show();
            return false;
        } catch (IOException e) {
            //e.printStackTrace();
            loadingDialog.dismiss();
            Toast.makeText(context,context.getResources().getString(R.string.save_error),Toast.LENGTH_SHORT).show();
            return false;

        }
        loadingDialog.dismiss();
        Toast.makeText(context,context.getResources().getString(R.string.save_ok)+" "+image_path,Toast.LENGTH_SHORT).show();
        return true;
    }
    public Bitmap createViewBitmap(ScrollView scrollView) {
        int h = 0;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
        }
        Bitmap bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(context.getResources().getColor(R.color.APPColor));
        scrollView.draw(canvas);
        return bitmap;
    }
}

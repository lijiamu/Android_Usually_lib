package lijiamu.com.android_usually_utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.activity.WebViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url","<img src=\"/Uploads/Editor/2017-03-14/58c74a2783dab.png\" alt=\"\" />");
                startActivity(intent);
            }
        });
    }

}

package error.licen.com.erroredittext.custom_widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import error.licen.com.erroredittext.DownloadImageTask;
import error.licen.com.erroredittext.R;

public class UrlImageView extends AppCompatImageView {

    private int loadingImageId = R.drawable.blue;
    private int errorImageId = R.mipmap.ic_launcher;

    public UrlImageView(Context context) {
        super(context);
    }

    public UrlImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UrlImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void startDownloadUrl(String url) {
        new DownloadImageTask(new DownloadImageTask.OnImageListener() {
            @Override
            public void onLoadingUrl() {
                setImageResource(loadingImageId);
            }

            @Override
            public void onSuccess(Bitmap result) {
                setImageBitmap(result);
            }

            @Override
            public void onFailed() {
                setImageResource(errorImageId);
            }
        }).execute(url);
    }
}

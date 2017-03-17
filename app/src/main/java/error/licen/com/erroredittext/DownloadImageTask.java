package error.licen.com.erroredittext;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;

/**
 * Created by Julie115 on 2017/3/17.
 */

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private OnImageListener imageListener;

    public DownloadImageTask(OnImageListener imageListener) {
        this.imageListener = imageListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        imageListener.onLoadingUrl();
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        if(result == null){
            imageListener.onFailed();
        }else {
            imageListener.onSuccess(result);
        }
    }

    public interface OnImageListener{
        void onLoadingUrl();

        void onSuccess(Bitmap result);

        void onFailed();
    }
}

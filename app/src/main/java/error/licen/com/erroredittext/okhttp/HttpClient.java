package error.licen.com.erroredittext.okhttp;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClient {

    private final static String hostUrl = "YOUR URL";

    private static final MediaType TEXT = MediaType.parse("text/plain; charset=utf-8");
    // application/json; charset=utf-8

    private static OkHttpClient client = new OkHttpClient();

    public HttpClient() {
    }

    @SuppressWarnings("unchecked")
    public static void requestTask(String postData, final RequestCallBack callBack) throws IOException {

        Call call = createPostCall(postData);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onRequestFail(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String outputString = response.body().string();
                if (TextUtils.isEmpty(outputString)) {
                    callBack.onRequestFail("");
                    return;
                }

                try {
                    Object result = new Gson().fromJson(outputString, callBack.getType());

                    callBack.onRequestSuccess(result);
                } catch (JsonSyntaxException e) {
                    callBack.onRequestFail("");
                }

            }
        });
    }


    private static Call createPostCall(String string) throws IOException {
        RequestBody body = RequestBody.create(TEXT, string);
        Request request = new Request.Builder()
                .url(hostUrl)
                .post(body)
                .build();
        return client.newCall(request);
    }

}

package error.licen.com.erroredittext.okhttp;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class RequestCallBack<T> {

    public abstract void onRequestFail(String errorMessage);

    public abstract void onRequestSuccess(T object);

    // 獲取Json的类型，因为資料可能集合也可能是Object
    Type getType() {
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (type instanceof Class) {
            return type; //如果是Object直接返回
        } else {
            return new TypeToken<T>() {
            }.getType(); //如果是集合，獲取集合的類型map或list
        }
    }

}

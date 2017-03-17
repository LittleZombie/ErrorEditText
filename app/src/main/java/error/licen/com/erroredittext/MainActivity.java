package error.licen.com.erroredittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import error.licen.com.erroredittext.custom_widget.TextInputLayout;
import error.licen.com.erroredittext.custom_widget.UrlImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout myTextInputLayout;
    private TextInputLayout myTextInputLayoutForTextView;

    private String imageUrl = "https://firebasestorage.googleapis.com/v0/b/teststorage-7c50d.appspot.com/o/photos%2Fimage%3A34?alt=media&token=cda7198a-d79c-406c-bd14-0d538f482427";
    private String wrong_imageUrl = "https://firebasestorage.googleapis.com/v0/b/teststorage-7c50d.appspot.com/o/photos%2Fimage%3A34?alt=media&token=cda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextInputLayout = (TextInputLayout) findViewById(R.id.myTextInputLayout);
        myTextInputLayoutForTextView = (TextInputLayout) findViewById(R.id.myTextInputLayout_forTextView);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        findViewById(R.id.myTextView).setOnClickListener(this);

        ((UrlImageView)findViewById(R.id.correctImageView)).startDownloadUrl(imageUrl);
        ((UrlImageView)findViewById(R.id.wrongImageView)).startDownloadUrl(wrong_imageUrl);
    }

    @Override
    public void onClick(View v) {
        String text = ((TextView)findViewById(R.id.myTextView)).getText().toString();

        if(v.getId() == R.id.myTextView){
            if(TextUtils.isEmpty(text)){
                ((TextView)findViewById(R.id.myTextView)).setText("Hello Hello Hello");
            }else{
                ((TextView)findViewById(R.id.myTextView)).setText("");
            }
            return;
        }

        // editText
        String message = getEditTextString();
        if(TextUtils.isEmpty(message)){
            myTextInputLayout.showMessage("Please enter your name");
        }else{
            Toast.makeText(this, "Hi !" + message, Toast.LENGTH_SHORT).show();
        }

        //TextView
        if(TextUtils.isEmpty(text)){
            myTextInputLayoutForTextView.showMessage("Please enter in TextView");
        }
    }

    private String getEditTextString(){
        return ((EditText)findViewById(R.id.myEditText)).getText().toString();
    }
}

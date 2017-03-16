package error.licen.com.erroredittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout myTextInputLayout;
    private TextInputLayout myTextInputLayoutForTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextInputLayout = (TextInputLayout) findViewById(R.id.myTextInputLayout);
        myTextInputLayoutForTextView = (TextInputLayout) findViewById(R.id.myTextInputLayout_forTextView);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        findViewById(R.id.myTextView).setOnClickListener(this);
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

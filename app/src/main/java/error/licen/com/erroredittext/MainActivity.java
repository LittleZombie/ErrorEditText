package error.licen.com.erroredittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout myTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextInputLayout = (TextInputLayout) findViewById(R.id.myTextInputLayout);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String message = getEditTextString();
        if(TextUtils.isEmpty(message)){
            myTextInputLayout.showMessage("Please enter your name");
            return;
        }

        Toast.makeText(this, "Hi !" + message, Toast.LENGTH_SHORT).show();
    }

    private String getEditTextString(){
        return ((EditText)findViewById(R.id.myEditText)).getText().toString();
    }
}

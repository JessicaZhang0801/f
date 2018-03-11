package compr.example.a1694603.finalproject1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Answer extends AppCompatActivity {

    TextView answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        answer=findViewById(R.id.answer);
        Intent receivePackage=getIntent();
        int getIndex=receivePackage.getIntExtra("Result",-1);
        if(getIndex==0)
        {
            answer.setText("first film:");
        }
        else
        {
            answer.setText("No requies is matched yours.");
        }
    }
}

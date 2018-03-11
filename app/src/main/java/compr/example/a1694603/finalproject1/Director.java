package compr.example.a1694603.finalproject1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Director extends AppCompatActivity implements CallBackMe{
    TextView position;
    TextView staff;
    public ArrayList<String> positionList=new ArrayList<>();
    public ArrayList<String> staffList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_director);

        position=findViewById(R.id.position);
        staff=findViewById(R.id.staff);

        Intent receivePackage1=getIntent();
        int result1=receivePackage1.getIntExtra("film_id",0);

        String url="https://api.themoviedb.org/3/movie/"+result1+"/credits?api_key=e64e9b43a2bd5741a2e856b21f7e83ab";
        JsonRetriever.RetrieveFromURL(this,url,this);
    }

    @Override
    public void CallThis(String jsonText) {
        try{
            JSONObject json=new JSONObject(jsonText);
            JSONArray array=json.getJSONArray("crew");
            for(int count=0;count<array.length();count++)
            {
                JSONObject object=array.getJSONObject(count);
                positionList.add(object.getString("job"));
                staffList.add(object.getString("name"));

                position.append(positionList.get(count)+"\n\n");
                staff.append(staffList.get(count)+"\n\n");


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

package compr.example.a1694603.finalproject1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Character extends AppCompatActivity implements CallBackMe{
    TextView character;
    TextView cast;
    public ArrayList<String> characterList=new ArrayList<>();
    public ArrayList<String> castList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        character=findViewById(R.id.character);
        cast=findViewById(R.id.cast);

        Intent receivePackage=getIntent();
        int result= receivePackage.getIntExtra("film_id",0);

        String url="https://api.themoviedb.org/3/movie/"+result+"/credits?api_key=e64e9b43a2bd5741a2e856b21f7e83ab";
        JsonRetriever.RetrieveFromURL(this,url,this);
    }

    @Override
    public void CallThis(String jsonText) {
        try{
            JSONObject json=new JSONObject(jsonText);
            JSONArray array=json.getJSONArray("cast");
            for(int count=0;count<array.length();count++)
            {
                JSONObject object=array.getJSONObject(count);
                characterList.add(object.getString("character"));
                castList.add(object.getString("name"));

                character.append(characterList.get(count)+"\n\n");
                cast.append(castList.get(count)+"\n\n");


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

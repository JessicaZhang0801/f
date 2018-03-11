package compr.example.a1694603.finalproject1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;

public class NewDescription extends AppCompatActivity implements CallBackMe{

    public int film_id;

    TextView title1=null;
    ImageView poster1=null;
    TextView releaseDate=null;
    TextView voteAverage=null;
    TextView characterName=null;
    TextView Director=null;
    TextView overView=null;
    public int getIndex;
    public int getURL;

    public ArrayList<String> titleList=new ArrayList<>();
    public ArrayList<String> posterList=new ArrayList<>();
    public ArrayList<String> releaseDateList=new ArrayList<>();
    public ArrayList<String> voteAverageList=new ArrayList<>();
    public ArrayList<String> overViewList=new ArrayList<>();
    public ArrayList <Integer> filmIDList=new ArrayList<>();

    public String ImageBaseURL ="https://image.tmdb.org/t/p/w500/";
    public String pageNum=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_description);


        title1=findViewById(R.id.title1);
        poster1=findViewById(R.id.poster1);
        releaseDate=findViewById(R.id.releaseDate);
        voteAverage=findViewById(R.id.voteAverage);
        characterName=findViewById(R.id.characterName);
        Director=findViewById(R.id.Director);
        overView=findViewById(R.id.overView);

        Intent receivePackage=getIntent();
        getIndex=receivePackage.getIntExtra("Result",-1);
        getURL=receivePackage.getIntExtra("url",-1);
        pageNum=receivePackage.getStringExtra("pageNum");


        String url="https://api.themoviedb.org/3/movie/popular?api_key=e64e9b43a2bd5741a2e856b21f7e83ab&page="+pageNum;
        JsonRetriever.RetrieveFromURL(this,url,this);
    }
    @Override
    public void CallThis(String jsonText) {
        try{
            JSONObject json=new JSONObject(jsonText);
            JSONArray array=json.getJSONArray("results");
            for(int count=0;count<array.length();count++)
            {
                JSONObject object=array.getJSONObject(count);
                titleList.add(object.getString("title"));
                posterList.add(object.getString("poster_path"));
                releaseDateList.add(object.getString("release_date"));
                voteAverageList.add(object.getString("vote_average"));
                overViewList.add(object.getString("overview"));
                filmIDList.add(object.getInt("id"));
                for(int a=0;a<titleList.size();a++)
                {
                    if(getIndex==a ||getURL==a)
                    {
                        title1.setText(titleList.get(a));
                        releaseDate.setText("Release Date: "+releaseDateList.get(a));
                        voteAverage.setText("Vote Average: "+voteAverageList.get(a));
                        overView.setText("Overview: \n"+overViewList.get(a));
                        characterName.setText("Character");
                        Director.setText("Staff");

                        new DownloadImageTask(poster1)
                                .execute(ImageBaseURL+posterList.get(a));

                        film_id=filmIDList.get(a);
                    }

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void Director(View view)
    {
        Intent myIntent4=new Intent(this,Director.class);
        myIntent4.putExtra("film_id",film_id);
        startActivity(myIntent4);
    }
    public void Character(View view)
    {
        Intent myIntent3=new Intent(this,Character.class);
        myIntent3.putExtra("film_id",film_id);
        startActivity(myIntent3);
    }
}

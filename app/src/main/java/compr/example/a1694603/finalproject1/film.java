package compr.example.a1694603.finalproject1;

import android.app.NotificationChannel;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class film extends AppCompatActivity implements CallBackMe {

    public int index;
    EditText SearchText;
    TextView mark;

    public String ImageBaseURL ="https://image.tmdb.org/t/p/w500/";
    public ArrayList<messageHolder> arrayList=new ArrayList<>();
    public ArrayList<String> arrayListSearch=new ArrayList<>();
    public ArrayList<String> urlList=new ArrayList<>();
    public String pageNum=null;
    public String TotalPage=null;
    public double number;
    public int IntNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        mark=(TextView)findViewById(R.id.mark);

        Intent receivePackage=getIntent();
        pageNum=receivePackage.getStringExtra("pageNum");

        String url="https://api.themoviedb.org/3/movie/popular?api_key=e64e9b43a2bd5741a2e856b21f7e83ab&page="+pageNum;
        JsonRetriever.RetrieveFromURL(this,url,this);

        SearchText=(EditText)findViewById(R.id.SearchText);

    }


    public void Search(View view)
    {
        Intent myIntentSearch =new Intent(this,NewDescription.class);
        for(int count=0;count<arrayListSearch.size();count++)
        {
            if(arrayListSearch.contains(SearchText.getText().toString().toLowerCase()));
            {
                index=arrayListSearch.indexOf(SearchText.getText().toString().toLowerCase());
                myIntentSearch.putExtra("Result",index);
                myIntentSearch.putExtra("pageNum",pageNum);
            }
        }
        startActivity(myIntentSearch);
        index=-1;
    }

    public void CallThis (String jsonText)
    {
        try{
            JSONObject json=new JSONObject(jsonText);
            JSONArray array=json.getJSONArray("results");
            TotalPage=json.getString("total_pages");

            try{
                number=Double.parseDouble(pageNum);
                IntNumber=(int)number;
            }
            catch(Exception e)
            {
                number=1;
                IntNumber=(int)number;

            }
            mark.setText("Page: "+IntNumber);

            for (int count=0;count<array.length();count++)
            {
                JSONObject object=array.getJSONObject(count);
                String title = object.getString("title");
                String url = ImageBaseURL + object.getString("poster_path");
               // String pathURL = "https://api.themoviedb.org/3/movie/" + object.getString("id") + "?api_key=e64e9b43a2bd5741a2e856b21f7e83ab";
                String pathURL = (object.getString("id"));
                messageHolder messageHolder = new messageHolder(title, url, pathURL);

                arrayList.add(messageHolder);
                arrayListSearch.add(title.toLowerCase());
                urlList.add(object.getString("id"));
                ListView LV=findViewById(R.id.myStuff);
                messageAdapter myAdapter= new messageAdapter(this,arrayList);
                LV.setAdapter(myAdapter);

                LV.setOnItemClickListener(
                        new AdapterView.OnItemClickListener(){

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                messageHolder myFeed= (messageHolder) parent.getItemAtPosition(position);
                                //INTENT
                                DetailedInfo(myFeed);
                            }
                        }
                );


            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void DetailedInfo(messageHolder gotClicked)
    {
        Intent myIntent1= new Intent(this,NewDescription.class);
        myIntent1.putExtra("pageNum",pageNum);
        for (int i=0 ; i< urlList.size();i++)
        {
            if (urlList.contains(gotClicked.pathURL))
            {
                myIntent1.putExtra("url", urlList.indexOf(gotClicked.pathURL));
            }
        }

        //myIntent1.putExtra("url", gotClicked.pathURL);
        startActivity(myIntent1);
    }
    public void nextPage(View view)
    {
        try{
            Intent myIntent6=new Intent(this,film.class);
            double page=Double.parseDouble(pageNum);
            double page1=page+1;
            double total=Double.parseDouble(TotalPage);

           if(page1>total)
           {
               startActivity(myIntent6);
            }
            else
          {
                String NextNum=Double.toString(page1);
                myIntent6.putExtra("pageNum",NextNum);
                startActivity(myIntent6);
           }
        }
        catch(Exception e )
        {
            Intent myIntent7=new Intent(this,film.class);
            myIntent7.putExtra("pageNum","2");
            startActivity(myIntent7);
        }

    }
}

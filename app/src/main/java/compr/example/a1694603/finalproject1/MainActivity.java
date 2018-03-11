package compr.example.a1694603.finalproject1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

public class MainActivity extends AppCompatActivity {

    SliderLayout sliderShow;
    @Override
    protected void onStop(){
        //sliderShow.stopAutoCycle();
        super.onStop();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SliderLayout sliderShow = findViewById(R.id.slider);

        TextSliderView textSliderView = new TextSliderView(this);
        textSliderView
                .description("Zootopia")
                .image("https://image.tmdb.org/t/p/w500///sM33SANp9z6rXW8Itn7NnG1GOEs.jpg");

        sliderShow.addSlider(textSliderView);

        TextSliderView textSliderView1 = new TextSliderView(this);
        textSliderView1
                .description("Beauty and the Beast")
                .image("https://image.tmdb.org/t/p/w500///tWqifoYuwLETmmasnGHO7xBjEtt.jpg");

        sliderShow.addSlider(textSliderView1);

        TextSliderView textSliderView3 = new TextSliderView(this);
        textSliderView3
                .description("Minions")
                .image("https://image.tmdb.org/t/p/w500///q0R4crx2SehcEEQEkYObktdeFy.jpg");

        sliderShow.addSlider(textSliderView3);

        TextSliderView textSliderView4 = new TextSliderView(this);
        textSliderView4
                .description("Fifty Shades of Grey")
                .image("https://image.tmdb.org/t/p/w500///jV8wnk3Jgz6f7degmT3lHNGI2tK.jpg");

        sliderShow.addSlider(textSliderView4);

    }
    public void Start(View view)
    {
        Intent myIntent=new Intent(this,film.class);
        startActivity(myIntent);
    }
}

package compr.example.a1694603.finalproject1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 1694603 on 2/28/2018.
 */

public class messageAdapter extends ArrayAdapter<messageHolder> {

    public messageAdapter(@NonNull Context context, @NonNull ArrayList<messageHolder> objects) {
        super(context, R.layout.message, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater myInflator= LayoutInflater.from(getContext());

        View customerFeedView=myInflator.inflate(R.layout.message,parent,false);

        ImageView myimage=customerFeedView.findViewById(R.id.poster);
        TextView mytext=customerFeedView.findViewById(R.id.film_description);

        String pictureID=getItem(position).imageURL;
        String description=getItem(position).title;

        mytext.setText(description);
        new DownloadImageTask(myimage)
            .execute(pictureID);

        return customerFeedView;
    }
}

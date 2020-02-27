package com.example.coronaaware;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.coronaaware.info.Article;

import java.util.List;

/**
 * @author DedUndead
 * @version 1.0
 */
public class ArticleAdapter extends ArrayAdapter<Article> {
    private Context mContext;
    private int mResource;

    /**
     * Constructor for the custom news adapted
     * @param context Refers to a current context
     * @param resource Custom layout resource
     * @param object List of the objects, that are going to be shown
     */
    public ArticleAdapter(Context context, int resource, List<Article> object) {
        super(context, resource, object);
        mContext = context;
        mResource = resource;
    }

    /**
     * Communicating with custom layout widgets
     * @param position Position of the current ListView element
     * @param convertView The view to fill the row
     * @param parent ViewGroup to be used
     * @return The view to fill the ListView row
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get topic of the article in a current row
        String topic = getItem(position).getTopic();
        Article article = new Article(topic);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvTopic = convertView.findViewById(R.id.tvArticle);
        tvTopic.setText(topic);

        return convertView;
    }
}


package com.itrainasia.mymaterial;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {


    public CardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        RecyclerView recylerView = (RecyclerView) inflater.inflate(R.layout.fragment_card, container, false);
        ContentAdapter adapter = new ContentAdapter(getActivity());
        recylerView.setAdapter(adapter);
        recylerView.setHasFixedSize(true);
        recylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recylerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView titleView;
        public TextView descTextView;


        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.custom_row, parent, false));

            imageView = itemView.findViewById(R.id.card_image);
            titleView = itemView.findViewById(R.id.card_title);
            descTextView = itemView.findViewById(R.id.card_text);

        }

    }
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder>{


        private static String[] mPlaces;
        private static String[] mDescriptions;
        private static Drawable[] mPictures;


        public ContentAdapter(Context context){
            Resources resources = context.getResources();
            mPlaces = resources.getStringArray(R.array.places);
            mDescriptions = resources.getStringArray(R.array.place_desc);

            TypedArray a = resources.obtainTypedArray(R.array.places_picture);
            mPictures = new Drawable[mPlaces.length];
            for (int i = 0 ; i< mPlaces.length; i++){
                mPictures[i] = a.getDrawable(i);
            }
           // a.recycle();

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.imageView.setImageDrawable( mPictures[position]);
            holder.titleView.setText(mPlaces[position]);
            holder.descTextView.setText(mDescriptions[position]);
        }

        @Override
        public int getItemCount() {
            return mPlaces.length;
        }
    }
}

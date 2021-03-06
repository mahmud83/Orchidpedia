package com.orchidpedia;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by ezra on 1/4/18.
 */

public class Ensiklopedia_1_Card extends Fragment implements View.OnClickListener {

    View v;
    String cardID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.card_ensiklopedia_1, container, false);

        // initialize Card
        initCard();

        return v;
    }

    private void initCard() {
        cardID = getResources().getString(R.string.card1_ensiklopedia);
        CardView cardView = v.findViewById(R.id.card1_ensiklopedia);
        cardView.setOnClickListener(this);
        ImageView imageView = v.findViewById(R.id.img_card1_ensiklopedia);
        Picasso.with(imageView.getContext())
                .load(R.drawable.img1_ensiklopedia)
                .into(imageView);
        TextView textView = v.findViewById(R.id.text_card1_ensiklopedia);
        Typeface tf= Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(),"fonts/Honeyguide.otf");
        textView.setTypeface(tf);
        textView.setText(getResources().getString(R.string.card1_ens_title));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card1_ensiklopedia:
                Intent intent = new Intent(getActivity(), EnsiklopediaActivity.class);
                intent.putExtra("cardEnsiklopediaID", cardID);
                startActivity(intent);
                /*showToast();*/
                break;
        }
    }

    /*private void showToast() {
        Toast.makeText(getActivity(), getResources().getString(R.string.wip), Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public void onDestroy() {
        unbindDrawables(v);
        if (v != null) {
            v = null;
        }
        System.gc();
        super.onDestroy();
    }

    protected void unbindDrawables(View view) {
        if (view != null) {
            if (view.getBackground() != null) {
                view.getBackground().setCallback(null);
            }
            if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    unbindDrawables(((ViewGroup) view).getChildAt(i));
                }
                ((ViewGroup) view).removeAllViews();
            }
        }
    }
}

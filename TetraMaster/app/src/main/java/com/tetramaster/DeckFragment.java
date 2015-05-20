package com.tetramaster;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeckFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeckFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeckFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeckFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeckFragment newInstance(String param1, String param2) {
        DeckFragment fragment = new DeckFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DeckFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deck, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void readDeck()
    {
        String filename = "read.txt";
        player = new Player();
        newDeck = new ArrayList<Card>();
        int count = 0;
        int[] buffer = new int[64]; //16 cards per deck

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(getActivity().openFileInput(filename));
            int data = inputStreamReader.read();
            char c = (char)data;
            int num = Character.getNumericValue(c);
            if(data != -1) {
                while (data != -1) {
                    if(num==-2)
                        break;
                    buffer[count] = num;
                    count++;
                    if(count % 4 == 0) {
                        newDeck.add(new Card(buffer[count-4], buffer[count-3], buffer[count-2], buffer[count-1]));
                    }
                    data = inputStreamReader.read();
                    c = (char)data;
                    num = Character.getNumericValue(c);
                }
                player.setDeck(newDeck);
            }
            inputStreamReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeDeck()
    {
        String filename = "read.txt";
        int index = 0;

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getActivity().openFileOutput(filename, Context.MODE_PRIVATE));
            while(player.getDeck().getSize() > index)
            {
                String s = player.getDeck().getDeck().get(index).toString();
                try {
                    outputStreamWriter.write(s, 1, 4);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                index++;
            }
            if(player.getDeck().getSize()==0)
            {
                try {
                    outputStreamWriter.write("-2", 1, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private ImageView leftHeadView;
    private ImageView rightHeadView;
    private ArrayList<Card> newDeck;
    private Player player;

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        ImageView backgroundView = (ImageView)getView().findViewById(R.id.backgroundView);
        backgroundView.setImageDrawable(getResources().getDrawable(R.drawable.background));
        backgroundView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        leftHeadView = (ImageView)getView().findViewById(R.id.leftHeadView);
        rightHeadView = (ImageView)getView().findViewById(R.id.rightHeadView);

        Drawable drawable = getResources().getDrawable(R.drawable.deckselected);
        leftHeadView.setImageDrawable(drawable);
        leftHeadView.setScaleType(ImageView.ScaleType.FIT_XY);

        drawable = getResources().getDrawable(R.drawable.trunk);
        rightHeadView.setImageDrawable(drawable);
        rightHeadView.setScaleType(ImageView.ScaleType.FIT_XY);

        //get width and height of screen
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        //set ImageView width programmatically
        leftHeadView.getLayoutParams().width = displaymetrics.widthPixels/2;
        rightHeadView.getLayoutParams().width = displaymetrics.widthPixels/2;

        leftHeadView.setOnClickListener(this);
        rightHeadView.setOnClickListener(this);

        //auto load deck frag
        Fragment f = new ManageDeckFragment();
        android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragC, f);
        ft.commit();

        readDeck();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.leftHeadView)
        {
            leftHeadView.setImageDrawable(getResources().getDrawable(R.drawable.deckselected));
            rightHeadView.setImageDrawable(getResources().getDrawable(R.drawable.trunk));

            Fragment f = new ManageDeckFragment();
            android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragC, f);
            ft.commit();
        }
        else if(view.getId()==R.id.rightHeadView)
        {
            leftHeadView.setImageDrawable(getResources().getDrawable(R.drawable.deck));
            rightHeadView.setImageDrawable(getResources().getDrawable(R.drawable.trunkselected));

            Fragment f = new ManageTrunkFragment();
            android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragC, f);
            ft.commit();
        }
    }

}

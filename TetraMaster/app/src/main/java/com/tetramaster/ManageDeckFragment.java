package com.tetramaster;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ManageDeckFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ManageDeckFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageDeckFragment extends Fragment implements View.OnClickListener{
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
     * @return A new instance of fragment ManageDeckFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageDeckFragment newInstance(String param1, String param2) {
        ManageDeckFragment fragment = new ManageDeckFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ManageDeckFragment() {
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
        return inflater.inflate(R.layout.fragment_manage_deck, container, false);
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

    private ArrayList<Card> newDeck;
    private Player player;
    private LinearLayout linearLayout;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

        readDeck();

        linearLayout = (LinearLayout)getView().findViewById(R.id.mdLinear);
        linearLayout.removeAllViews();
        int size = player.getDeck().getSize();
        int index = 0;
        while(index < size)
        {
            Card c = player.getDeck().getDeck().get(index);
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("com.tetramaster:drawable/" +
                    c.getName(), null, null)));
            imageView.setTag(c.getName());
            imageView.setOnClickListener(this);
            linearLayout.addView(imageView);
            index++;
        }
    }

    @Override
    public void onClick(View view) {
        if(view instanceof ImageView)
        {
            int index = 0;
            for(int i=0; i<player.getDeck().getSize(); i++)
            {
                String v = (String)view.getTag();
                if(v.equals(player.getDeck().getDeck().get(i).getName()))
                {
                    index = i;
                    break;
                }
            }

            Fragment f = MoveFragment.newInstance("deck",index);
            android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragC, f);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

}

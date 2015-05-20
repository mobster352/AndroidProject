package com.tetramaster;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MoveFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoveFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private int index;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment MoveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoveFragment newInstance(String param1, int param2) {
        MoveFragment fragment = new MoveFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2,param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MoveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            index = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_move, container, false);
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


    public void readTrunk()
    {
        String filename = "trunk.txt";
        trunk = new Trunk();
        int count = 0;
        int[] buffer = new int[64]; //16 cards per deck

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(getActivity().openFileInput(filename));
            int data = inputStreamReader.read();
            char c = (char)data;
            int num = Character.getNumericValue(c);
            if(data != -1) {
                while (data != -1) {
                    buffer[count] = num;
                    count++;
                    if(count % 4 == 0) {
                        trunk.add(new Card(buffer[count-4], buffer[count-3], buffer[count-2], buffer[count-1]));
                    }
                    data = inputStreamReader.read();
                    c = (char)data;
                    num = Character.getNumericValue(c);
                }
            }
            inputStreamReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeTrunk()
    {
        String filename = "trunk.txt";
        int index = 0;

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getActivity().openFileOutput(filename, Context.MODE_PRIVATE));
            while(trunk.getSize() > index)
            {
                String s = trunk.getDeck().get(index).toString();
                try {
                    outputStreamWriter.write(s, 1, 4);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                index++;
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

    private ImageView move;
    private ImageView yes;
    private ImageView no;
    private Player player;
    private ArrayList<Card> newDeck;
    private Trunk trunk;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        yes = (ImageView)getView().findViewById(R.id.yes);
        yes.setImageDrawable(getResources().getDrawable(R.drawable.yes));
        yes.setOnClickListener(this);

        no = (ImageView)getView().findViewById(R.id.no);
        no.setImageDrawable(getResources().getDrawable(R.drawable.no));
        no.setOnClickListener(this);

        move = (ImageView)getView().findViewById(R.id.move);

        if(mParam1.equals("trunk"))
        {
            move.setImageDrawable(getResources().getDrawable(R.drawable.movetodeck));
        }
        else if(mParam1.equals("deck"))
        {
            move.setImageDrawable(getResources().getDrawable(R.drawable.movetotrunk));
        }

        readDeck();
        readTrunk();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.no)
        {
            android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
        }
        else if(view.getId() == R.id.yes)
        {
            if(mParam1.equals("deck")) {
                //put in trunk indexed card
                Card c = player.getDeck().getDeck().get(index);

                player.getDeck().getDeck().remove(c);
                writeDeck();

                trunk.getDeck().add(c);
                writeTrunk();
            }
            else if(mParam1.equals("trunk"))
            {
                //put in deck indexed card
                Card c = trunk.getDeck().get(index);

                player.getDeck().getDeck().add(c);
                writeDeck();

                trunk.getDeck().remove(c);
                writeTrunk();
            }

            android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
        }
    }
}

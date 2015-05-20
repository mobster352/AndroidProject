package com.tetramaster;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EndGameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EndGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EndGameFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment EndGameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EndGameFragment newInstance(String param1) {
        EndGameFragment fragment = new EndGameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public EndGameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_end_game, container, false);
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

    private ImageView endBkgdView;
    private ImageView resultsView;
    private ImageButton continueButton;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        endBkgdView = (ImageView)getView().findViewById(R.id.endBkgdView);
        endBkgdView.setImageDrawable(getResources().getDrawable(R.drawable.background));
        endBkgdView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        resultsView = (ImageView)getView().findViewById(R.id.resultsView);
        if(mParam1.equals("win"))
        {
            resultsView.setImageDrawable(getResources().getDrawable(R.drawable.youwin));
        }
        else if(mParam1.equals("lose"))
        {
            resultsView.setImageDrawable(getResources().getDrawable(R.drawable.youlose));
        }
        else
        {
            resultsView.setImageDrawable(getResources().getDrawable(R.drawable.draw));
        }
        resultsView.setScaleType(ImageView.ScaleType.FIT_XY);

        continueButton = (ImageButton)getView().findViewById(R.id.continueButton);
        continueButton.setImageDrawable(getResources().getDrawable(R.drawable.cimage));
        continueButton.setScaleType(ImageButton.ScaleType.FIT_XY);
        continueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.continueButton)
        {
            Fragment f = new MainActivity.PlaceholderFragment();
            android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, f);
            ft.commit();
        }

    }

}

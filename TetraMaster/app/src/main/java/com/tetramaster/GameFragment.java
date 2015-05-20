package com.tetramaster;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String name;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment GameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFragment newInstance(String param1) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
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

    private Player player;                            //player 1
    private AI ai;                     //player 2
    private boolean taken1 = false;
    private boolean taken2 = false;
    private boolean taken3 = false;
    private boolean taken4 = false;
    private boolean taken5 = false;
    private boolean taken6 = false;
    private boolean taken7 = false;
    private boolean taken8 = false;
    private boolean taken9 = false;
    private boolean taken10 = false;
    private boolean taken11 = false;
    private boolean taken12 = false;
    private boolean taken13 = false;
    private boolean taken14 = false;
    private boolean taken15 = false;
    private boolean taken16 = false;
    private int maxL = 0;
    private int maxR = 0;
    private int maxT = 0;
    private int maxD = 0;
    private ArrayList<Card> onBoard = new ArrayList<Card>();
    private ArrayList<CardAI> aiBoard = new ArrayList<CardAI>();
    private boolean pspot1,pspot2,pspot3,pspot4,pspot5,pspot6,pspot7,pspot8,pspot9,pspot10,pspot11,pspot12,pspot13,pspot14,pspot15,pspot16; //player card in spot
    private boolean aspot1,aspot2,aspot3,aspot4,aspot5,aspot6,aspot7,aspot8,aspot9,aspot10,aspot11,aspot12,aspot13,aspot14,aspot15,aspot16; //ai card in spot
    private int spot;           //location on board
    private boolean myTurn;
    private int pscore = 0;
    private int ascore = 0;
    private int selected = -1;

    //Views
    private ImageView boardView1,boardView2,boardView3,boardView4,boardView5,boardView6,boardView7,boardView8,
            boardView9,boardView10,boardView11,boardView12,boardView13,boardView14,boardView15,boardView16;
    private ImageView pHandView0,pHandView1,pHandView2,pHandView3,aHandView0,aHandView1
            ,aHandView2,aHandView3,pscoreView,ascoreView,ascoreText,pscoreText;

    private Drawable p0,p1,p2,p3;
    private final int TIME = 2000;
    private MediaPlayer chipSound;
    private MediaPlayer drawSound;
    private MediaPlayer gameSound;
    private ArrayList<Card> newDeck;




    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        //set background image view
        ImageView backgroundView = (ImageView)getView().findViewById(R.id.backgroundView);
        Drawable drawable = getResources().getDrawable(R.drawable.background);
        backgroundView.setImageDrawable(drawable);
        backgroundView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        pscoreView = (ImageView)getView().findViewById(R.id.pscoreView);
        ascoreView = (ImageView)getView().findViewById(R.id.ascoreView);
        pscoreText = (ImageView)getView().findViewById(R.id.pscoreText);
        ascoreText = (ImageView)getView().findViewById(R.id.ascoreText);

        pscoreText.setImageDrawable(getResources().getDrawable(R.drawable.score));
        ascoreText.setImageDrawable(getResources().getDrawable(R.drawable.ascore));
        pscoreView.setImageDrawable(getResources().getDrawable(R.drawable.s0));
        ascoreView.setImageDrawable(getResources().getDrawable(R.drawable.s0));

        chipSound = MediaPlayer.create(getView().getContext(),R.raw.poker_chip);
        drawSound = MediaPlayer.create(getView().getContext(),R.raw.draw_card);
        gameSound = MediaPlayer.create(getView().getContext(),R.raw.game_music);

        gameSound.setLooping(true);
        gameSound.start();

        readDeck();

        initializePlayerHand();
        ai = new AI(name);
        initializeAIHand();
        initializeBoard();

        p0 = null;
        p1 = null;
        p2 = null;
        p3 = null;

        myTurn = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        gameSound.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        gameSound.setLooping(true);
        gameSound.start();
    }

    public void handleSelected()
    {
        if(p0!=null) {
            pHandView0.setImageDrawable(p0);
            p0 = null;
        }
        else if(p1!=null) {
            pHandView1.setImageDrawable(p1);
            p1 = null;
        }
        else if(p2!=null) {
            pHandView2.setImageDrawable(p2);
            p2 = null;
        }
        else if(p3!=null) {
            pHandView3.setImageDrawable(p3);
            p3 = null;
        }
    }

    public void handleClick(int d) throws InterruptedException {
        ImageView b=null;

        if(d==1) {
            b = boardView1;
            pspot1 = true;
            taken1 = true;
        }
        else if(d==2) {
            b = boardView2;
            pspot2 = true;
            taken2 = true;
        }
        else if(d==3) {
            b = boardView3;
            pspot3 = true;
            taken3 = true;
        }
        else if(d==4) {
            b = boardView4;
            pspot4 = true;
            taken4 = true;
        }
        else if(d==5) {
            b = boardView5;
            pspot5 = true;
            taken5 = true;
        }
        else if(d==6) {
            b = boardView6;
            pspot6 = true;
            taken6 = true;
        }
        else if(d==7) {
            b = boardView7;
            pspot7 = true;
            taken7 = true;
        }
        else if(d==8) {
            b = boardView8;
            pspot8 = true;
            taken8 = true;
        }
        else if(d==9) {
            b = boardView9;
            pspot9 = true;
            taken9 = true;
        }
        else if(d==10) {
            b = boardView10;
            pspot10 = true;
            taken10 = true;
        }
        else if(d==11) {
            b = boardView11;
            pspot11 = true;
            taken11 = true;
        }
        else if(d==12) {
            b = boardView12;
            pspot12 = true;
            taken12 = true;
        }
        else if(d==13) {
            b = boardView13;
            pspot13 = true;
            taken13 = true;
        }
        else if(d==14) {
            b = boardView14;
            pspot14 = true;
            taken14 = true;
        }
        else if(d==15) {
            b = boardView15;
            pspot15 = true;
            taken15 = true;
        }
        else if(d==16) {
            b = boardView16;
            pspot16 = true;
            taken16 = true;
        }

        if(selected==0) {
            b.setImageDrawable(p0);
            p0 = null;
        }
        else if(selected==1) {
            b.setImageDrawable(p1);
            p1 = null;
        }
        else if(selected==2) {
            b.setImageDrawable(p2);
            p2 = null;
        }
        else if(selected==3) {
            b.setImageDrawable(p3);
            p3 = null;
        }
        else {
            return;
        }

        //battle
        onBoard.add(player.getHand().get(selected));
        spot = d;
        onBoard.get(onBoard.size()-1).setLoc(spot);


        myTurn = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                battle(spot);

                System.out.println(checkFinished());
                if(checkFinished())
                    endGame();
                //manage hand
                checkHand(selected);

                aiLogic();
                System.out.println(checkFinished());
                if(checkFinished())
                    endGame();
                selected = -1;
            }
        },TIME);
    }

    /*void handlerstuff()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },2000);
    }
    */


    @Override
    public void onClick(View view) {
        if(myTurn) {
            if (view.getId() == R.id.pHandView0 && pHandView0.getDrawable() != null) {
                handleSelected();
                p0 = pHandView0.getDrawable();
                selected = 0;
                String temp = "s"+player.getHand().get(0).getName();
                pHandView0.setImageDrawable(getResources().getDrawable
                        (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));
            }
            else if (view.getId() == R.id.pHandView1 && pHandView1.getDrawable() != null) {
                handleSelected();
                p1 = pHandView1.getDrawable();
                selected = 1;
                String temp = "s"+player.getHand().get(1).getName();
                pHandView1.setImageDrawable(getResources().getDrawable
                        (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));
            }
            else if (view.getId() == R.id.pHandView2 && pHandView2.getDrawable() != null) {
                handleSelected();
                p2 = pHandView2.getDrawable();
                selected = 2;
                String temp = "s"+player.getHand().get(2).getName();
                pHandView2.setImageDrawable(getResources().getDrawable
                        (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));
            }
            else if (view.getId() == R.id.pHandView3 && pHandView3.getDrawable() != null) {
                handleSelected();
                p3 = pHandView3.getDrawable();
                selected = 3;
                String temp = "s"+player.getHand().get(3).getName();
                pHandView3.setImageDrawable(getResources().getDrawable
                        (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));
            }
            else if (view.getId() == R.id.boardView1 && selected != -1 && !taken1) {
                try {
                    handleClick(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (view.getId() == R.id.boardView2 && selected != -1 && !taken2) {
                try {
                    handleClick(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (view.getId() == R.id.boardView3 && selected != -1 && !taken3) {
                try {
                    handleClick(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (view.getId() == R.id.boardView4 && selected != -1 && !taken4) {
                try {
                    handleClick(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (view.getId() == R.id.boardView5 && selected != -1 && !taken5) {
                try {
                    handleClick(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (view.getId() == R.id.boardView6 && selected != -1 && !taken6) {
                try {
                    handleClick(6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (view.getId() == R.id.boardView7 && selected != -1 && !taken7) {
                try {
                    handleClick(7);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (view.getId() == R.id.boardView8 && selected != -1 && !taken8) {
                try {
                    handleClick(8);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (view.getId() == R.id.boardView9 && selected != -1 && !taken9) {
                try {
                    handleClick(9);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (view.getId() == R.id.boardView10 && selected != -1 && !taken10) {
                try {
                    handleClick(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (view.getId() == R.id.boardView11 && selected != -1 && !taken11) {
                try {
                    handleClick(11);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (view.getId() == R.id.boardView12 && selected != -1 && !taken12) {
                try {
                    handleClick(12);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (view.getId() == R.id.boardView13 && selected != -1 && !taken13) {
                try {
                    handleClick(13);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (view.getId() == R.id.boardView14 && selected != -1 && !taken14) {
                try {
                    handleClick(14);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (view.getId() == R.id.boardView15 && selected != -1 && !taken15) {
                try {
                    handleClick(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if (view.getId() == R.id.boardView16 && selected != -1 && !taken16) {
                try {
                    handleClick(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }//end myTurn
    }//end click

    //Game Logic
    public void initializeBoard()
    {
        boardView1 = (ImageView)getView().findViewById(R.id.boardView1);
        Drawable drawable = getResources().getDrawable(R.drawable.board);
        boardView1.setImageDrawable(drawable);
        boardView1.setOnClickListener(this);

        boardView2 = (ImageView)getView().findViewById(R.id.boardView2);
        drawable = getResources().getDrawable(R.drawable.board);
        boardView2.setImageDrawable(drawable);
        boardView2.setOnClickListener(this);

        boardView3 = (ImageView)getView().findViewById(R.id.boardView3);
        drawable = getResources().getDrawable(R.drawable.board);
        boardView3.setImageDrawable(drawable);
        boardView3.setOnClickListener(this);

        boardView4 = (ImageView)getView().findViewById(R.id.boardView4);
        drawable = getResources().getDrawable(R.drawable.board);
        boardView4.setImageDrawable(drawable);
        boardView4.setOnClickListener(this);

        boardView5 = (ImageView)getView().findViewById(R.id.boardView5);
        drawable = getResources().getDrawable(R.drawable.board);
        boardView5.setImageDrawable(drawable);
        boardView5.setOnClickListener(this);

        boardView6 = (ImageView)getView().findViewById(R.id.boardView6);
        drawable = getResources().getDrawable(R.drawable.board);
        boardView6.setImageDrawable(drawable);
        boardView6.setOnClickListener(this);

        boardView7 = (ImageView)getView().findViewById(R.id.boardView7);
        drawable = getResources().getDrawable(R.drawable.board);
        boardView7.setImageDrawable(drawable);
        boardView7.setOnClickListener(this);

        boardView8 = (ImageView)getView().findViewById(R.id.boardView8);
        drawable = getResources().getDrawable(R.drawable.board);
        boardView8.setImageDrawable(drawable);
        boardView8.setOnClickListener(this);

        boardView9 = (ImageView)getView().findViewById(R.id.boardView9);
        drawable = getResources().getDrawable(R.drawable.board);
        boardView9.setImageDrawable(drawable);
        boardView9.setOnClickListener(this);

        boardView10 = (ImageView)getView().findViewById(R.id.boardView10);
        drawable = getResources().getDrawable(R.drawable.board);
        boardView10.setImageDrawable(drawable);
        boardView10.setOnClickListener(this);

        boardView11 = (ImageView)getView().findViewById(R.id.boardView11);
        drawable = getResources().getDrawable(R.drawable.board);
        boardView11.setImageDrawable(drawable);
        boardView11.setOnClickListener(this);

        boardView12 = (ImageView)getView().findViewById(R.id.boardView12);
        drawable = getResources().getDrawable(R.drawable.board);
        boardView12.setImageDrawable(drawable);
        boardView12.setOnClickListener(this);

        boardView13 = (ImageView)getView().findViewById(R.id.boardView13);
        drawable = getResources().getDrawable(R.drawable.board);
        boardView13.setImageDrawable(drawable);
        boardView13.setOnClickListener(this);

        boardView14 = (ImageView)getView().findViewById(R.id.boardView14);
        drawable = getResources().getDrawable(R.drawable.board);
        boardView14.setImageDrawable(drawable);
        boardView14.setOnClickListener(this);

        boardView15 = (ImageView)getView().findViewById(R.id.boardView15);
        drawable = getResources().getDrawable(R.drawable.board);
        boardView15.setImageDrawable(drawable);
        boardView15.setOnClickListener(this);

        boardView16 = (ImageView)getView().findViewById(R.id.boardView16);
        drawable = getResources().getDrawable(R.drawable.board);
        boardView16.setImageDrawable(drawable);
        boardView16.setOnClickListener(this);
    }

    public void initializePlayerHand()
    {
        player.getDeck().shuffle();
        for(int i=0; i<4; i++)
        {
            drawSound.start();
            player.draw();
        }
        pHandView0 = (ImageView)getView().findViewById(R.id.pHandView0);
        pHandView0.setImageDrawable(getResources().getDrawable
                (getResources().getIdentifier("com.tetramaster:drawable/"+
                        player.getHand().get(0).getName(),null,null)));
        pHandView0.setOnClickListener(this);

        pHandView1 = (ImageView)getView().findViewById(R.id.pHandView1);
        pHandView1.setImageDrawable(getResources().getDrawable
                (getResources().getIdentifier("com.tetramaster:drawable/"+
                        player.getHand().get(1).getName(),null,null)));
        pHandView1.setOnClickListener(this);

        pHandView2 = (ImageView)getView().findViewById(R.id.pHandView2);
        pHandView2.setImageDrawable(getResources().getDrawable
                (getResources().getIdentifier("com.tetramaster:drawable/"+
                        player.getHand().get(2).getName(),null,null)));
        pHandView2.setOnClickListener(this);

        pHandView3 = (ImageView)getView().findViewById(R.id.pHandView3);
        pHandView3.setImageDrawable(getResources().getDrawable
                (getResources().getIdentifier("com.tetramaster:drawable/"+
                        player.getHand().get(3).getName(),null,null)));
        pHandView3.setOnClickListener(this);
    }

    public void initializeAIHand()
    {
        ai.getDeck().shuffle();
        for(int i=0; i<4; i++)
        {
            drawSound.start();
            ai.draw();
        }
        aHandView0 = (ImageView)getView().findViewById(R.id.aHandView0);
        Drawable drawable = getResources().getDrawable(R.drawable.backa);
        aHandView0.setImageDrawable(drawable);

        aHandView1 = (ImageView)getView().findViewById(R.id.aHandView1);
        drawable = getResources().getDrawable(R.drawable.backa);
        aHandView1.setImageDrawable(drawable);

        aHandView2 = (ImageView)getView().findViewById(R.id.aHandView2);
        drawable = getResources().getDrawable(R.drawable.backa);
        aHandView2.setImageDrawable(drawable);

        aHandView3 = (ImageView)getView().findViewById(R.id.aHandView3);
        drawable = getResources().getDrawable(R.drawable.backa);
        aHandView3.setImageDrawable(drawable);
    }

    public boolean checkFinished()
    {
        if(taken1==true && taken2==true && taken3==true && taken4==true && taken5==true && taken6==true && taken7==true &&
                taken8==true && taken9==true && taken10==true && taken11==true && taken12==true && taken13==true &&
                taken14==true && taken15==true && taken16==true)
            return true;
        else
            return false;
    }

    public void updatePscore()
    {
        pscore++;

        String temp = "s"+String.valueOf(pscore);
        pscoreView.setImageDrawable(getResources().getDrawable
                (getResources().getIdentifier("com.tetramaster:drawable/"+
                        temp,null,null)));

    }

    public void updateAscore()
    {
        ascore++;

        String temp = "s"+String.valueOf(ascore);
        ascoreView.setImageDrawable(getResources().getDrawable
                (getResources().getIdentifier("com.tetramaster:drawable/"+
                        temp,null,null)));

    }

    public void endGame()
    {
        if(pscore>ascore){
            Fragment f = EndGameFragment.newInstance("win");
            android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, f);
            ft.commit();
        }
        //Player 1 wins

        else if(pscore<ascore){
            Fragment f = EndGameFragment.newInstance("lose");
            android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, f);
            ft.commit();

        }
            //Player 2 wins
        else if(pscore==ascore){
            Fragment f = EndGameFragment.newInstance("draw");
            android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, f);
            ft.commit();

        }
            //Draw
    }



    //Player 1 Logic

    public void checkActionP(int spot) throws InterruptedException
    {
        CardAI card = null;//ai card on board
        //aiBoard ai card on board

        if(spot==1)//player put card into spot1
        {
            if(taken2==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==2)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView2.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView1.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }
            if(taken5==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==5)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView5.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView1.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }
        }//end if spot1
        else if(spot==2)
        {
            if(taken1==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==1)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView1.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView2.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken1
            if(taken3==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==3)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView3.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView2.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken3
            if(taken6==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==6)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView2.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken6
        }//end if spot 2
        else if(spot==3)
        {
            if(taken2==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==2)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView2.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView3.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken2
            if(taken4==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==4)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView4.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView3.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken7
            if(taken7==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==7)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView3.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken7
        }//end if spot3
        else if(spot==4)
        {
            if(taken3==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==3)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView3.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView4.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken3
            if(taken8==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==8)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView8.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView4.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken8
        }//end it spot4
        else if(spot==5)
        {
            if(taken1==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==1)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView1.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView5.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken1
            if(taken6==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==6)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView5.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken6
            if(taken9==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==9)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView9.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView5.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken9
        }//end if spot5
        else if(spot==6)
        {
            if(taken2==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==2)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView2.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken2
            if(taken7==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==7)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken7
            if(taken10==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==10)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken10
            if(taken5==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==5)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView5.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken5
        }//end if spot6
        else if(spot==7)
        {
            if(taken3==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==3)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView3.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken3
            if(taken8==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==8)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView8.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken8
            if(taken11==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==11)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken11
            if(taken6==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==6)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken6
        }//end if spot7
        else if(spot==8)
        {
            if(taken4==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==4)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView4.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView8.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken4
            if(taken7==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==7)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView8.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken7
            if(taken12==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==12)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView12.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView8.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken12
        }//end if spot8
        else if(spot==9)
        {
            if(taken5==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==5)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView5.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView9.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken5
            if(taken10==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==10)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView9.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken10
            if(taken13==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==13)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView13.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView9.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken13
        }//end if spot9
        else if(spot==10)
        {
            if(taken6==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==6)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken6
            if(taken11==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==11)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken11
            if(taken14==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==14)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView14.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken14
            if(taken9==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==9)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView9.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken9
        }//end if spot10
        else if(spot==11)
        {
            if(taken7==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==7)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken7
            if(taken12==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==12)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView12.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken12
            if(taken15==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==15)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView15.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken15
            if(taken10==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==10)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken10
        }//end if spot11
        else if(spot==12)
        {
            if(taken8==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==8)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView8.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView12.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken8
            if(taken11==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==11)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView12.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken11
            if(taken16==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==16)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView16.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView12.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken16
        }//end if spot12
        else if(spot==13)
        {
            if(taken9==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==9)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView9.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView13.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken9
            if(taken14==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==14)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView14.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView13.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken14
        }//end if spot13
        else if(spot==14)
        {
            if(taken13==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==13)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView13.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView14.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken13
            if(taken10==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==10)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView14.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end taken10
            if(taken15==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==15)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView15.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView15.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken15
        }//end if spot14
        else if(spot==15)
        {
            if(taken14==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==14)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView14.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView15.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken14
            if(taken11==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==11)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView15.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken11
            if(taken16==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==16)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView16.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView15.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken16
        }//end if spot15
        else if(spot==16)
        {
            if(taken15==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==15)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView15.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView16.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken15
            if(taken12==true)
            {
                //flip lowest card
                for(int it=0; it<aiBoard.size(); it++)
                {
                    if(aiBoard.get(it).getLoc()==12)
                    {
                        card = aiBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView12.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            onBoard.get(onBoard.size()-1).setFlipped(true);

                            boardView16.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                            return;
                        }
                        else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }
        }//end if spot16
    }//end checkAction


    public void checkHand(int selected)
    {
        pHandView0.setImageDrawable(null);
        pHandView1.setImageDrawable(null);
        pHandView2.setImageDrawable(null);
        pHandView3.setImageDrawable(null);

        player.getHand().remove(selected);

        if(player.getDeck().getSize()>0) {
            drawSound.start();
            player.draw();
        }

        for(int x=0; x<player.getHandSize(); x++)		//for loop player hand
        {
            String temp = player.getHand().get(x).getName();
            if(x==0)
            {
                pHandView0.setImageDrawable(getResources().getDrawable
                        (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));
            }
            if(x==1)
            {
                pHandView1.setImageDrawable(getResources().getDrawable
                        (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));
            }
            if(x==2)
            {
                pHandView2.setImageDrawable(getResources().getDrawable
                        (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));
            }
            if(x==3)
            {
                pHandView3.setImageDrawable(getResources().getDrawable
                        (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));
            }

        }
    }//end checkHand

    public void battle(int spot)
    {
        try {
            checkActionP(spot);
        } catch (InterruptedException e) {

        }
    }

    //AI Logic
    public void aiLogic()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getMaxLeft();
                getMaxTop();
                getMaxRight();
                getMaxDown();
                try {
                    aiChoice();
                } catch (InterruptedException e) {

                }

            }
        },TIME);

    }

    public void getMaxLeft()
    {
        for(int mL=0; mL<ai.getHandSize(); mL++)
        {
            if(mL+1 != ai.getHandSize())
            {
                if(ai.getHand().get(mL).getLeft() >= ai.getHand().get(mL+1).getLeft())
                {
                    if(mL==0 || ai.getHand().get(mL).getLeft() > maxL)
                    {
                        maxL = ai.getHand().get(mL).getLeft();
                    }
                }
                if(ai.getHand().get(mL+1).getLeft() >= ai.getHand().get(mL).getLeft())
                {
                    if(mL==0 || ai.getHand().get(mL+1).getLeft() > maxL)
                    {
                        maxL = ai.getHand().get(mL+1).getLeft();
                    }
                }
            }
            else
            {
                if(ai.getHand().get(mL).getLeft() > maxL)
                {
                    maxL = ai.getHand().get(mL).getLeft();
                }
            }

        }
    }

    public void getMaxTop()
    {
        for(int mT=0; mT<ai.getHandSize(); mT++)
        {
            if(mT+1 != ai.getHandSize())
            {
                if(ai.getHand().get(mT).getTop() >= ai.getHand().get(mT+1).getTop())
                {
                    if(mT==0 || ai.getHand().get(mT).getTop() > maxT)
                    {
                        maxT = ai.getHand().get(mT).getTop();
                    }
                }
                if(ai.getHand().get(mT+1).getTop() >= ai.getHand().get(mT).getTop())
                {
                    if(mT==0 || ai.getHand().get(mT+1).getTop() > maxT)
                    {
                        maxT = ai.getHand().get(mT+1).getTop();
                    }
                }
            }
            else
            {
                if(ai.getHand().get(mT).getTop() > maxT)
                {
                    maxT = ai.getHand().get(mT).getTop();
                }
            }

        }
    }

    public void getMaxRight()
    {
        for(int mR=0; mR<ai.getHandSize(); mR++)
        {
            if(mR+1 != ai.getHandSize())
            {
                if(ai.getHand().get(mR).getRight() >= ai.getHand().get(mR+1).getRight())
                {
                    if(mR==0 || ai.getHand().get(mR).getRight() > maxR)
                    {
                        maxR = ai.getHand().get(mR).getRight();
                    }
                }
                if(ai.getHand().get(mR+1).getRight() >= ai.getHand().get(mR).getRight())
                {
                    if(mR==0 || ai.getHand().get(mR+1).getRight() > maxR)
                    {
                        maxR = ai.getHand().get(mR+1).getRight();
                    }
                }
            }
            else
            {
                if(ai.getHand().get(mR).getRight() > maxR)
                {
                    maxR = ai.getHand().get(mR).getRight();
                }
            }

        }
    }

    public void getMaxDown()
    {
        for(int mD=0; mD<ai.getHandSize(); mD++)
        {
            if(mD+1 != ai.getHandSize())
            {
                if(ai.getHand().get(mD).getDown() >= ai.getHand().get(mD+1).getDown())
                {
                    if(mD==0 || ai.getHand().get(mD).getDown() > maxD)
                    {
                        maxD = ai.getHand().get(mD).getDown();
                    }
                }
                if(ai.getHand().get(mD+1).getDown() >= ai.getHand().get(mD).getDown())
                {
                    if(mD==0 || ai.getHand().get(mD+1).getDown() > maxD)
                    {
                        maxD = ai.getHand().get(mD+1).getDown();
                    }
                }
            }
            else
            {
                if(ai.getHand().get(mD).getLeft() > maxD)
                {
                    maxD = ai.getHand().get(mD).getDown();
                }
            }

        }
    }

    public void checkAiHand()
    {
        //if(a.getHandSize()<=5 && a.getHandSize()>0)
        //{
        aHandView0.setImageDrawable(null);
        aHandView1.setImageDrawable(null);
        aHandView2.setImageDrawable(null);
        aHandView3.setImageDrawable(null);
        if(ai.getDeck().getSize()>0) {
            drawSound.start();
            ai.draw();
        }
        String temp = "backa";
        for(int x=0; x<ai.getHandSize(); x++)		//for loop ai hand
        {
            if(x==0)
            {
                aHandView0.setImageDrawable(getResources().getDrawable
                        (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));
            }
            if(x==1)
            {
                aHandView1.setImageDrawable(getResources().getDrawable
                        (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));
            }
            if(x==2)
            {
                aHandView2.setImageDrawable(getResources().getDrawable
                        (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));
            }
            if(x==3)
            {
                aHandView3.setImageDrawable(getResources().getDrawable
                        (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));
            }

        }//end for
        //}

    }

    public void checkActionAI(int spot) throws InterruptedException
    {
        Card card = null;//player card on board
        //aiBoard ai card on board
        if(spot==1)//ai put card into spot1
        {
            if(taken2==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==2)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView2.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView1.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }
            if(taken5==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==5)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView5.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView1.setImageDrawable(getResources().getDrawable(R.drawable.back));


                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }
        }//end if spot1
        if(spot==2)
        {
            if(taken1==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==1)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView1.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView2.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken1
            if(taken3==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==3)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView3.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView2.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken3
            if(taken6==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==6)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView2.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken6
        }//end if spot 2
        if(spot==3)
        {
            if(taken2==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==2)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView2.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView3.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken2
            if(taken4==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==4)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView4.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView3.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken7
            if(taken7==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==7)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView3.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken7
        }//end if spot3
        if(spot==4)
        {
            if(taken3==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==3)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView3.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView4.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken3
            if(taken8==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==8)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView8.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView4.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken8
        }//end it spot4
        if(spot==5)
        {
            if(taken1==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==1)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView1.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView5.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken1
            if(taken6==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==6)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView5.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken6
            if(taken9==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==9)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView9.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView5.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken9
        }//end if spot5
        if(spot==6)
        {
            if(taken2==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==2)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView2.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken2
            if(taken7==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==7)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken7
            if(taken10==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==10)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken10
            if(taken5==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==5)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView5.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken5
        }//end if spot6
        if(spot==7)
        {
            if(taken3==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==3)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView3.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken3
            if(taken8==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==8)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView8.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken8
            if(taken11==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==11)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken11
            if(taken6==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==6)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken6
        }//end if spot7
        if(spot==8)
        {
            if(taken4==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==4)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView4.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView8.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken4
            if(taken7==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==7)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView8.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken7
            if(taken12==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==12)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView12.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView8.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken12
        }//end if spot8
        if(spot==9)
        {
            if(taken5==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==5)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView5.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView9.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken5
            if(taken10==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==10)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView9.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken10
            if(taken13==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==13)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView13.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView9.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken13
        }//end if spot9
        if(spot==10)
        {
            if(taken6==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==6)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView6.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken6
            if(taken11==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==11)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken11
            if(taken14==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==14)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView14.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken14
            if(taken9==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==9)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView9.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken9
        }//end if spot10
        if(spot==11)
        {
            if(taken7==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==7)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView7.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken7
            if(taken12==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==12)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView12.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken12
            if(taken15==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==15)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView15.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken15
            if(taken10==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==10)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken10
        }//end if spot11
        if(spot==12)
        {
            if(taken8==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==8)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView8.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView12.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken8
            if(taken11==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==11)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView12.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken11
            if(taken16==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==16)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView16.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView12.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken16
        }//end if spot12
        if(spot==13)
        {
            if(taken9==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==9)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView9.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView13.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken9
            if(taken14==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==14)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView14.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView13.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken14
        }//end if spot13
        if(spot==14)
        {
            if(taken13==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==13)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView13.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView14.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken13
            if(taken10==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==10)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView10.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView14.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end taken10
            if(taken15==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==15)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView15.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView14.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken15
        }//end if spot14
        if(spot==15)
        {
            if(taken14==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==14)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView14.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView15.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken14
            if(taken11==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==11)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView11.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView15.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken11
            if(taken16==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==16)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView16.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView15.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken16
        }//end if spot15
        if(spot==16)
        {
            if(taken15==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==15)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView15.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView16.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }//end if taken15
            if(taken12==true)
            {
                //flip lowest card
                for(int it=0; it<onBoard.size(); it++)
                {
                    if(onBoard.get(it).getLoc()==12)
                    {
                        card = onBoard.get(it);
                        break;
                    }
                }
                if(card!=null)
                {
                    if(card.getFlipped()==false)
                    {
                        if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 2
                            card.setFlipped(true);

                            boardView12.setImageDrawable(getResources().getDrawable(R.drawable.backp));

                            updateAscore();
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
                        {
                            chipSound.start();
                            //flip card spot 1
                            aiBoard.get(aiBoard.size()-1).setFlipped(true);

                            boardView16.setImageDrawable(getResources().getDrawable(R.drawable.back));

                            updatePscore();
                            return;
                        }
                        else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
                        {
                            //nothing
                        }
                    }
                    card = null;
                }
            }
        }//end if spot16
    }//end checkAction

    public void aiChoice() throws InterruptedException
    {
        CardAI card = null;
        int save = 0;
        String temp = "";

        //logic >
        if(pspot1==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==1)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken2==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if (ai.getHand().get(i).getLeft()>c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView2.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 2;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);


                    final int finalSave = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave);
                            checkAiHand();
                            aspot2 = true;
                            taken2 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken5==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()>c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView5.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 5;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave1 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave1);
                            checkAiHand();
                            aspot5 = true;
                            taken5 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot1
        if(pspot2==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==2)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken1==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()>c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView1.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 1;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave2 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave2);
                            checkAiHand();
                            aspot1 = true;
                            taken1 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken3==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getLeft()>c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView3.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 3;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave3 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave3);
                            checkAiHand();
                            aspot3 = true;
                            taken3 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken6==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()>c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView6.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 6;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave4 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave4);
                            checkAiHand();
                            aspot6 = true;
                            taken6 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot2
        if(pspot3==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==3)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken2==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()>c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView2.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 2;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave5 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave5);
                            checkAiHand();
                            aspot2 = true;
                            taken2 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken4==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if (ai.getHand().get(i).getLeft()>c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView4.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 4;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave6 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave6);
                            checkAiHand();
                            aspot4 = true;
                            taken4 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken7==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()>c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView7.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 7;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave7 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave7);
                            checkAiHand();
                            aspot7 = true;
                            taken7 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot3
        if(pspot4==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==4)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken3==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if (ai.getHand().get(i).getRight()>c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView3.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 3;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave8 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave8);
                            checkAiHand();
                            aspot3 = true;
                            taken3 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken8==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()>c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView8.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 8;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave9 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave9);
                            checkAiHand();
                            aspot8 = true;
                            taken8 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot4
        if(pspot5==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==5)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken1==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()>c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView1.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 1;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave10 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave10);
                            checkAiHand();
                            aspot1 = true;
                            taken1 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken6==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getLeft()>c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView6.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 6;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave11 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave11);
                            checkAiHand();
                            aspot6 = true;
                            taken6 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken9==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()>c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView9.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 9;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave12 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave12);
                            checkAiHand();
                            aspot9 = true;
                            taken9 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot5
        if(pspot6==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==6)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken2==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()>c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView2.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 2;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave13 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave13);
                            checkAiHand();
                            aspot2 = true;
                            taken2 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    }, TIME);


                    return;
                }
            }
            else if(taken7==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getLeft()>c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView7.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 7;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave14 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave14);
                            checkAiHand();
                            aspot7 = true;
                            taken7 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken10==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()>c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView10.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 10;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave15 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave15);
                            checkAiHand();
                            aspot10 = true;
                            taken10 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken5==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()>c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView5.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 5;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave16 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave16);
                            checkAiHand();
                            aspot5 = true;
                            taken5 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot6
        if(pspot7==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==7)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken3==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()>c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView3.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 3;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave17 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave17);
                            checkAiHand();
                            aspot3 = true;
                            taken3 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken8==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if (ai.getHand().get(i).getLeft()>c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView8.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 8;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave18 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave18);
                            checkAiHand();
                            aspot8 = true;
                            taken8 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken11==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()>c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView11.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 11;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave19 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave19);
                            checkAiHand();
                            aspot11 = true;
                            taken11 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken6==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()>c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView6.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 6;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave20 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave20);
                            checkAiHand();
                            aspot6 = true;
                            taken6 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot7
        if(pspot8==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==8)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken4==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()>c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView4.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 4;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave21 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave21);
                            checkAiHand();
                            aspot4 = true;
                            taken4 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken12==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()>c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView12.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 12;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave22 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave22);
                            checkAiHand();
                            aspot12 = true;
                            taken12 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken7==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if (ai.getHand().get(i).getRight()>c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView7.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 7;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave23 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave23);
                            checkAiHand();
                            aspot7 = true;
                            taken7 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot8
        if(pspot9==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==9)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken5==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()>c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView5.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 5;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave24 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave24);
                            checkAiHand();
                            aspot5 = true;
                            taken5 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken10==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getLeft()>c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView10.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 10;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave25 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave25);
                            checkAiHand();
                            aspot10 = true;
                            taken10 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken13==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()>c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView13.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 13;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave26 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave26);
                            checkAiHand();
                            aspot13 = true;
                            taken13 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot9
        if(pspot10==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==10)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken6==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()>c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView6.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 6;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave27 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave27);
                            checkAiHand();
                            aspot6 = true;
                            taken6 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken11==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getLeft()>c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView11.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 11;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave28 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave28);
                            checkAiHand();
                            aspot11 = true;
                            taken11 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken14==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()>c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView14.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 14;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave29 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave29);
                            checkAiHand();
                            aspot14 = true;
                            taken14 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken9==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()>c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView9.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 9;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave30 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave30);
                            checkAiHand();
                            aspot9 = true;
                            taken9 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot10
        if(pspot11==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==11)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken7==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()>c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView7.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 7;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave31 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave31);
                            checkAiHand();
                            aspot7 = true;
                            taken7 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken12==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if (ai.getHand().get(i).getLeft()>c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView12.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 12;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave32 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave32);
                            checkAiHand();
                            aspot12 = true;
                            taken12 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken15==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()>c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView15.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 15;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave33 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave33);
                            checkAiHand();
                            aspot15 = true;
                            taken15 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken10==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()>c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView10.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 10;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave34 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave34);
                            checkAiHand();
                            aspot10 = true;
                            taken10 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot11
        if(pspot12==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==12)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken8==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()>c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView8.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 8;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave35 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave35);
                            checkAiHand();
                            aspot8 = true;
                            taken8 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken16==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()>c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView16.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 16;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave36 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave36);
                            checkAiHand();
                            aspot16 = true;
                            taken16 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken11==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()>c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView11.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 11;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave37 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave37);
                            checkAiHand();
                            aspot11 = true;
                            taken11 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot12
        if(pspot13==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==13)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken9==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()>c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView9.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 9;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave38 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave38);
                            checkAiHand();
                            aspot9 = true;
                            taken9 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken14==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getLeft()>c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView14.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 14;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave39 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave39);
                            checkAiHand();
                            aspot14 = true;
                            taken14 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot13
        if(pspot14==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==14)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken10==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()>c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView10.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 10;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave40 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave40);
                            checkAiHand();
                            aspot10 = true;
                            taken10 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken15==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getLeft()>c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView15.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 15;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave41 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave41);
                            checkAiHand();
                            aspot15 = true;
                            taken15 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken13==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()>c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView13.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 13;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave42 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave42);
                            checkAiHand();
                            aspot13 = true;
                            taken13 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot14
        if(pspot15==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==15)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken11==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()>c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView11.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 11;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave43 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave43);
                            checkAiHand();
                            aspot11 = true;
                            taken11 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken16==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if (ai.getHand().get(i).getLeft()>c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView16.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 16;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave44 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave44);
                            checkAiHand();
                            aspot16 = true;
                            taken16 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken14==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()>c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView14.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 14;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave45 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave45);
                            checkAiHand();
                            aspot14 = true;
                            taken14 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot15
        if(pspot16==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==16)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken12==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()>c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView12.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 12;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave46 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave46);
                            checkAiHand();
                            aspot12 = true;
                            taken12 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken15==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if (ai.getHand().get(i).getRight()>c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView15.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 15;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave47 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave47);
                            checkAiHand();
                            aspot15 = true;
                            taken15 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot16



        //logic =       try to match one on board
        if(pspot1==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==1)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken2==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if (ai.getHand().get(i).getLeft()==c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView2.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 2;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave48 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave48);
                            checkAiHand();
                            aspot2 = true;
                            taken2 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken5==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()==c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView5.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 5;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave49 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave49);
                            checkAiHand();
                            aspot5 = true;
                            taken5 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot1
        if(pspot2==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==2)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken1==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()==c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView1.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 1;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave50 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave50);
                            checkAiHand();
                            aspot1 = true;
                            taken1 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken3==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getLeft()==c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView3.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 3;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave51 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave51);
                            checkAiHand();
                            aspot3 = true;
                            taken3 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken6==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()==c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView6.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 6;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave52 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave52);
                            checkAiHand();
                            aspot6 = true;
                            taken6 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot2
        if(pspot3==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==3)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken2==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()==c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView2.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 2;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave53 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave53);
                            checkAiHand();
                            aspot2 = true;
                            taken2 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken4==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if (ai.getHand().get(i).getLeft()==c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView4.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 4;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave54 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave54);
                            checkAiHand();
                            aspot4 = true;
                            taken4 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken7==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()==c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView7.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 7;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave55 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave55);
                            checkAiHand();
                            aspot7 = true;
                            taken7 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot3
        if(pspot4==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==4)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken3==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if (ai.getHand().get(i).getRight()==c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView3.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 3;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave56 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave56);
                            checkAiHand();
                            aspot3 = true;
                            taken3 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken8==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()==c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView8.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 8;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave57 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave57);
                            checkAiHand();
                            aspot8 = true;
                            taken8 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot4
        if(pspot5==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==5)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken1==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()==c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView1.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 1;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave58 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave58);
                            checkAiHand();
                            aspot1 = true;
                            taken1 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken6==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getLeft()==c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView6.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 6;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave59 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave59);
                            checkAiHand();
                            aspot6 = true;
                            taken6 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken9==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()==c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView9.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 9;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave60 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave60);
                            checkAiHand();
                            aspot9 = true;
                            taken9 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot5
        if(pspot6==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==6)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken2==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()==c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView2.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 2;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave61 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave61);
                            checkAiHand();
                            aspot2 = true;
                            taken2 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken7==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getLeft()==c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView7.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 7;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave62 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave62);
                            checkAiHand();
                            aspot7 = true;
                            taken7 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken10==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()==c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView10.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 10;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave63 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave63);
                            checkAiHand();
                            aspot10 = true;
                            taken10 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken5==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()==c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView5.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 5;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave64 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave64);
                            checkAiHand();
                            aspot5 = true;
                            taken5 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot6
        if(pspot7==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==7)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken3==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()==c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView3.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 3;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave65 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave65);
                            checkAiHand();
                            aspot3 = true;
                            taken3 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken8==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if (ai.getHand().get(i).getLeft()==c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView8.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 8;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave66 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave66);
                            checkAiHand();
                            aspot8 = true;
                            taken8 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken11==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()==c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView11.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 11;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave67 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave67);
                            checkAiHand();
                            aspot11 = true;
                            taken11 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken6==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()==c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView6.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 6;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave68 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave68);
                            checkAiHand();
                            aspot6 = true;
                            taken6 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot7
        if(pspot8==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==8)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken4==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()==c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView4.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 4;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave69 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave69);
                            checkAiHand();
                            aspot4 = true;
                            taken4 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken12==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()==c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView12.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 12;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave70 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave70);
                            checkAiHand();
                            aspot12 = true;
                            taken12 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken7==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if (ai.getHand().get(i).getRight()==c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView7.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 7;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave71 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave71);
                            checkAiHand();
                            aspot7 = true;
                            taken7 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot8
        if(pspot9==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==9)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken5==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()==c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView5.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 5;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave72 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave72);
                            checkAiHand();
                            aspot5 = true;
                            taken5 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken10==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getLeft()==c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView10.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 10;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave73 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave73);
                            checkAiHand();
                            aspot10 = true;
                            taken10 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken13==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()==c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView13.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 13;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave74 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave74);
                            checkAiHand();
                            aspot13 = true;
                            taken13 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot9
        if(pspot10==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==10)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken6==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()==c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView6.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 6;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave75 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave75);
                            checkAiHand();
                            aspot6 = true;
                            taken6 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken11==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getLeft()==c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView11.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 11;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave76 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave76);
                            checkAiHand();
                            aspot11 = true;
                            taken11 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken14==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()==c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView14.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 14;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave77 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave77);
                            checkAiHand();
                            aspot14 = true;
                            taken14 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken9==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()==c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView9.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 9;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave78 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave78);
                            checkAiHand();
                            aspot9 = true;
                            taken9 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot10
        if(pspot11==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==11)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken7==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()==c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView7.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 7;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave79 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave79);
                            checkAiHand();
                            aspot7 = true;
                            taken7 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken12==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if (ai.getHand().get(i).getLeft()==c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView12.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 12;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave80 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave80);
                            checkAiHand();
                            aspot12 = true;
                            taken12 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken15==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()==c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView15.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 15;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave81 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave81);
                            checkAiHand();
                            aspot15 = true;
                            taken15 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken10==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()==c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView10.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 10;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave82 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave82);
                            checkAiHand();
                            aspot10 = true;
                            taken10 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot11
        if(pspot12==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==12)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken8==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()==c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView8.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 8;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave83 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave83);
                            checkAiHand();
                            aspot8 = true;
                            taken8 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken16==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getTop()==c.getDown())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView16.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 16;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave84 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave84);
                            checkAiHand();
                            aspot16 = true;
                            taken16 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken11==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()==c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView11.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 11;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave85 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave85);
                            checkAiHand();
                            aspot11 = true;
                            taken11 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot12
        if(pspot13==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==13)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken9==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()==c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView9.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 9;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave86 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave86);
                            checkAiHand();
                            aspot9 = true;
                            taken9 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken14==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getLeft()==c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView14.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 14;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave87 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave87);
                            checkAiHand();
                            aspot14 = true;
                            taken14 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot13
        if(pspot14==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==14)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken10==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()==c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView10.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 10;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave88 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave88);
                            checkAiHand();
                            aspot10 = true;
                            taken10 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken15==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getLeft()==c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView15.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 15;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave89 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave89);
                            checkAiHand();
                            aspot15 = true;
                            taken15 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken13==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()==c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView13.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 13;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave90 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave90);
                            checkAiHand();
                            aspot13 = true;
                            taken13 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot14
        if(pspot15==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==15)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken11==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()==c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView11.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 11;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave91 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave91);
                            checkAiHand();
                            aspot11 = true;
                            taken11 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken16==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if (ai.getHand().get(i).getLeft()==c.getRight())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView16.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 16;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave92 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave92);
                            checkAiHand();
                            aspot16 = true;
                            taken16 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken14==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()==c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView14.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 14;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave93 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave93);
                            checkAiHand();
                            aspot14 = true;
                            taken14 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot15
        if(pspot16==true)
        {
            Card c = null;
            card = null;
            for(int i=0; i<onBoard.size(); i++)
            {
                if(onBoard.get(i).getLoc()==16)
                {
                    c = onBoard.get(i);
                }
            }
            if(taken12==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getDown()==c.getTop())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView12.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 12;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave94 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave94);
                            checkAiHand();
                            aspot12 = true;
                            taken12 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
            else if(taken15==false)
            {
                for(int i=0; i<ai.getHandSize(); i++)
                {
                    if(ai.getHand().get(i).getRight()==c.getLeft())
                    {
                        card = ai.getHand().get(i);
                        save = i;
                    }
                }
                if(card==null)
                {

                }
                else
                {
                    temp += card.getName();

                    boardView15.setImageDrawable(getResources().getDrawable
                            (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

                    aiBoard.add(card);
                    spot = 15;//first location on board
                    aiBoard.get(aiBoard.size()-1).setLoc(spot);

                    final int finalSave95 = save;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                checkActionAI(spot);//does the battling
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ai.use(finalSave95);
                            checkAiHand();
                            aspot15 = true;
                            taken15 = true;
                            if(checkFinished())
                                endGame();
                            myTurn = true;
                        }
                    },TIME);


                    return;
                }
            }
        }//end pspot16



        //logic for FCFS
        if(taken1==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getRight()==maxR || ai.getHand().get(m).getDown()==maxD)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView1.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 1;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave96 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave96);
                    checkAiHand();
                    aspot1 = true;
                    taken1 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken1	

        else if(taken2==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getRight()==maxR || ai.getHand().get(m).getDown()==maxD
                        || ai.getHand().get(m).getLeft()==maxL)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView2.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 2;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave97 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave97);
                    checkAiHand();
                    aspot2 = true;
                    taken2 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken2	

        else if(taken3==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getRight()==maxR || ai.getHand().get(m).getDown()==maxD
                        || ai.getHand().get(m).getLeft()==maxL)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView3.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 3;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave98 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave98);
                    checkAiHand();
                    aspot3 = true;
                    taken3 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken3	

        else if(taken4==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getDown()==maxD || ai.getHand().get(m).getLeft()==maxL)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView4.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 4;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave99 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave99);
                    checkAiHand();
                    aspot4 = true;
                    taken4 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken4	

        else if(taken5==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getRight()==maxR || ai.getHand().get(m).getDown()==maxD
                        || ai.getHand().get(m).getTop()==maxT)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView5.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 5;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave100 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave100);
                    checkAiHand();
                    aspot5 = true;
                    taken5 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken5	

        else if(taken6==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getRight()==maxR || ai.getHand().get(m).getDown()==maxD
                        || ai.getHand().get(m).getLeft()==maxL || ai.getHand().get(m).getTop()==maxT)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView6.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 6;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave101 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave101);
                    checkAiHand();
                    aspot6 = true;
                    taken6 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken6	

        else if(taken7==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getRight()==maxR || ai.getHand().get(m).getDown()==maxD
                        || ai.getHand().get(m).getLeft()==maxL || ai.getHand().get(m).getTop()==maxT)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView7.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 7;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave102 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave102);
                    checkAiHand();
                    aspot7 = true;
                    taken7 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken7	

        else if(taken8==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getTop()==maxT || ai.getHand().get(m).getDown()==maxD
                        || ai.getHand().get(m).getLeft()==maxL)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView8.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 8;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave103 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave103);
                    checkAiHand();
                    aspot8 = true;
                    taken8 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken8	

        else if(taken9==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getRight()==maxR || ai.getHand().get(m).getDown()==maxD
                        || ai.getHand().get(m).getTop()==maxT)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView9.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 9;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave104 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave104);
                    checkAiHand();
                    aspot9 = true;
                    taken9 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken9	

        else if(taken10==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getRight()==maxR || ai.getHand().get(m).getDown()==maxD
                        || ai.getHand().get(m).getLeft()==maxL || ai.getHand().get(m).getTop()==maxT)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView10.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 10;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave105 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave105);
                    checkAiHand();
                    aspot10 = true;
                    taken10 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken10	

        else if(taken11==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getRight()==maxR || ai.getHand().get(m).getDown()==maxD
                        || ai.getHand().get(m).getLeft()==maxL || ai.getHand().get(m).getTop()==maxT)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView11.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 11;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave106 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave106);
                    checkAiHand();
                    aspot11 = true;
                    taken11 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken11	

        else if(taken12==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getTop()==maxT || ai.getHand().get(m).getDown()==maxD
                        || ai.getHand().get(m).getLeft()==maxL)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView12.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 12;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave107 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave107);
                    checkAiHand();
                    aspot12 = true;
                    taken12 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken12	

        else if(taken13==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getRight()==maxR || ai.getHand().get(m).getTop()==maxT)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView13.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 13;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave108 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave108);
                    checkAiHand();
                    aspot13 = true;
                    taken13 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken13	

        else if(taken14==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getRight()==maxR || ai.getHand().get(m).getTop()==maxT
                        || ai.getHand().get(m).getLeft()==maxL)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView14.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 14;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave109 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave109);
                    checkAiHand();
                    aspot14 = true;
                    taken14 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken14	

        else if(taken15==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getRight()==maxR || ai.getHand().get(m).getTop()==maxT
                        || ai.getHand().get(m).getLeft()==maxL)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView15.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 15;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave110 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave110);
                    checkAiHand();
                    aspot15 = true;
                    taken15 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken15	

        else if(taken16==false)
        {
            for(int m=0; m<ai.getHandSize(); m++)
            {
                if(ai.getHand().get(m).getTop()==maxT || ai.getHand().get(m).getLeft()==maxL)
                {
                    card = ai.getHand().get(m);
                    save = m;
                }
            }
            temp += card.getName();

            boardView16.setImageDrawable(getResources().getDrawable
                    (getResources().getIdentifier("com.tetramaster:drawable/"+temp,null,null)));

            aiBoard.add(card);
            spot = 16;//first location on board
            aiBoard.get(aiBoard.size()-1).setLoc(spot);

            final int finalSave111 = save;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        checkActionAI(spot);//does the battling
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ai.use(finalSave111);
                    checkAiHand();
                    aspot16 = true;
                    taken16 = true;
                    if(checkFinished())
                        endGame();
                    myTurn = true;
                }
            },TIME);
        }//end if taken16


    }


}//end class

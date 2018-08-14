package com.example.android.cricketscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int teamAscore=0;
    public int teamBscore=0;
    public int teamAwicket=0;
    public int teamBwicket=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toast toast=Toast.makeText(getApplicationContext(),"Team A all out",Toast.LENGTH_SHORT);
        final Toast toastB=Toast.makeText(getApplicationContext(),"Team B all out",Toast.LENGTH_SHORT);

        final TextView teamAscoreview=(TextView)findViewById(R.id.team_A_score);

        Button teamA1run=(Button)findViewById(R.id.add_1_run_team_A);
        teamA1run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teamAwicket<=9){
                    teamAscore+=1;
                    teamAscoreview.setText(""+teamAscore);
                }else {
                    toast.show();
                }
            }
        });

        Button teamA2runs=(Button)findViewById(R.id.add_2_runs_team_A);
        teamA2runs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teamAwicket<=9){
                    teamAscore+=2;
                    teamAscoreview.setText(""+teamAscore);
                }else {
                    toast.show();
                }
            }
        });
        Button teamA3runs=(Button)findViewById(R.id.add_3_runs_team_A);
        teamA3runs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teamAwicket<=9){
                    teamAscore+=3;
                    teamAscoreview.setText(""+teamAscore);
                }else {
                    toast.show();
                }
            }
        });

        Button teamA4runs=(Button)findViewById(R.id.add_4_runs_team_A);
        teamA4runs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teamAwicket<=9){
                    teamAscore+=4;
                    teamAscoreview.setText(""+teamAscore);
                }else {
                    toast.show();
                }
            }
        });
        Button teamA6runs=(Button)findViewById(R.id.add_6_runs_team_A);
        teamA6runs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teamAwicket<=9){
                    teamAscore+=6;
                    teamAscoreview.setText(""+teamAscore);
                }else {
                    toast.show();
                }
            }
        });

        final TextView teamAwicketsview=(TextView)findViewById(R.id.team_A_wickets);
        final Button teamAwicketadd=(Button)findViewById(R.id.add_1_wicket_team_A);
        teamAwicketadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teamAwicket<=9){
                    teamAwicket+=1;
                    teamAwicketsview.setText(""+teamAwicket);
                }else {
                    toast.show();
                }
            }
        });


        final TextView teamBscoreview=(TextView)findViewById(R.id.team_B_score);

        Button teamB1run=(Button)findViewById(R.id.add_1_run_team_B);
        teamB1run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teamBwicket<=9){
                    teamBscore+=1;
                    teamBscoreview.setText(""+teamBscore);
                }else {
                    toastB.show();
                }
            }
        });

        Button teamB2runs=(Button)findViewById(R.id.add_2_runs_team_B);
        teamB2runs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teamBwicket<=9){
                    teamBscore+=2;
                    teamBscoreview.setText(""+teamBscore);
                }else {
                    toastB.show();
                }
            }
        });

        Button teamB3runs=(Button)findViewById(R.id.add_3_runs_team_B);
        teamB3runs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teamBwicket<=9){
                    teamBscore+=3;
                    teamBscoreview.setText(""+teamBscore);
                }else {
                    toastB.show();
                }
            }
        });

        Button teamB4runs=(Button)findViewById(R.id.add_4_runs_team_B);
        teamB4runs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teamBwicket<=9){
                    teamBscore+=4;
                    teamBscoreview.setText(""+teamBscore);
                }else {
                    toastB.show();
                }
            }
        });

        Button teamB6runs=(Button)findViewById(R.id.add_6_runs_team_B);
        teamB6runs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teamBwicket<=9){
                    teamBscore+=6;
                    teamBscoreview.setText(""+teamBscore);
                }else {
                    toastB.show();
                }
            }
        });

        final TextView teamBwicketsview=(TextView)findViewById(R.id.team_B_wickets);
        final Button teamBwicketadd=(Button)findViewById(R.id.add_1_wicket_team_B);
        teamBwicketadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teamBwicket<=9){
                    teamBwicket+=1;
                    teamBwicketsview.setText(""+teamBwicket);
                }else {
                    toastB.show();
                }
            }
        });

        Button resetbutton=(Button)findViewById(R.id.reset);
        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAscore=0;
                teamBscore=0;
                teamBscoreview.setText(""+0);
                teamAscoreview.setText(""+0);
                teamAwicket=0;
                teamAwicketsview.setText(""+0);
                teamBwicket=0;
                teamBwicketsview.setText(""+0);
            }
        });
    }
}

package com.hfad.testproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
RecyclerView recyclerView;
List<Integer> testArray;
int Req_Code=1;
List<DataDetails> list;
MyAdapter myAdapter;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Collections.shuffle(list);
        myAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        testArray.add(android.R.drawable.btn_default);
        testArray.add(android.R.drawable.arrow_up_float);
        testArray.add(android.R.drawable.btn_star);
        testArray.add(android.R.drawable.ic_media_play);
        testArray.add(android.R.drawable.button_onoff_indicator_on);
        testArray.add(android.R.drawable.gallery_thumb);
        testArray.add(android.R.drawable.ic_lock_idle_low_battery);
        testArray.add(android.R.drawable.picture_frame);
        testArray.add(android.R.drawable.ic_menu_my_calendar);
        testArray=new ArrayList<Integer>();
        testArray.add(android.R.drawable.divider_horizontal_bright);
        testArray.add(android.R.drawable.ic_delete);
        list=new ArrayList<DataDetails>();
        for(int i=0;i<=10; i++){
            list.add(new DataDetails("row title:" +String.valueOf(i), "row subtitle:" +String.valueOf(i), testArray.get(i)));
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter=new MyAdapter(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
        setHasOptionsMenu(true);

        return view;


    }

    public class MyAdapter extends RecyclerView.Adapter<GenericViewHolder>{

        public MyAdapter(Context context) {

        }



        @Override
        public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType==1){
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_first, parent, false);
                return new MainViewHolder(view);
            }
            else{
                View view2=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_second, parent, false);
                return new SecondViewHolder(view2);
            }

        }

        @Override
        public void onBindViewHolder(GenericViewHolder holder, int position) {
            int i=0;
            switch(holder.getItemViewType())
            {
                case 1 : {

                    ((MainViewHolder) holder).textview1.setText("Row ID"+ list.get(position).data_text1);
                    ((MainViewHolder) holder).textview2.setText("Row sub ID"+ list.get(position).data_text2);
                    ((MainViewHolder) holder).image.setImageResource(list.get(position).data_image);
                }
                break;

                case 2 : {
                    ((SecondViewHolder) holder).textview3.setText("Row ID"+  list.get(position).data_text1);
                    ((SecondViewHolder) holder).textview4.setText("Row Sub ID"+  list.get(position).data_text2);
                    ((SecondViewHolder) holder).image2.setImageResource(list.get(position).data_image);
                }
                break;
            }


        }


        @Override
        public int getItemViewType(int position) {
            if(position%2==0){
                return 2;
            }
            else return 1;
        }

        @Override
        public int getItemCount() {
            return testArray.size();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//This if part is important part, coz when the user does not press any radio button, but presses the back button, then the app might crash, so here we say that go to the back button, with previous data and do not crash.

        if(requestCode==Req_Code&&resultCode==RESULT_OK&&data!=null){
            String text=data.getStringExtra("textvalue");
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        }
    }

    public class GenericViewHolder extends RecyclerView.ViewHolder
    {

        public GenericViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class MainViewHolder extends GenericViewHolder{
        TextView textview1;
        TextView textview2;
        ImageView image;
        public MainViewHolder(View itemView) {
            super(itemView);
            textview1 = (TextView) itemView.findViewById(R.id.textview1);
            textview2 = (TextView) itemView.findViewById(R.id.textview2);
            image = (ImageView) itemView.findViewById(R.id.imageview1);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(getActivity(), SecondActivity.class);
                    i.putExtra("details", list.get(getAdapterPosition()));

                    startActivityForResult(i, 100);
                }
            });

        }
    }

    class SecondViewHolder extends GenericViewHolder
    {

        TextView textview3,textview4;
        ImageView image2;
        public SecondViewHolder(View itemView) {
            super(itemView);

            textview3 = (TextView) itemView.findViewById(R.id.textview3);
            textview4 = (TextView) itemView.findViewById(R.id.textview4);
            image2=(ImageView)itemView.findViewById(R.id.imageview2);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i=new Intent(getActivity(),SecondActivity.class);
                    i.putExtra("details", list.get(getAdapterPosition()));

                    startActivityForResult(i,100);
                }
            });
        }

    }

}

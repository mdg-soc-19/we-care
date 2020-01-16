package com.example.wecare;

import android.content.Context;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.recyclerview.widget.RecyclerView.Adapter;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TakeMedsAlarm1_Fragment extends Fragment implements View.OnClickListener,MainActivity.OnBackPressedListener  {


    private static View view;
    private static Animation shakeAnimation;
    private Button Addbtn, RemoveBtn, BackBtn;
    private List<String> friends;

    private DatabaseReference RootRef, DemoRef1;
    private static FragmentManager fragmentManager;
    public static String s = null;
    int size;
    private RecyclerView AlarmView;
    private FirebaseRecyclerAdapter mFireAdapter;
    private LinearLayoutManager linearLayoutManager;

    public Query query;

    public TakeMedsAlarm1_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.takemedsalarm1_layout, container, false);
        initViews();
        AlarmView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        AlarmView.setLayoutManager(linearLayoutManager);
        fetch();
        setListeners();
        return view;
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);
        Addbtn = (Button) view.findViewById(R.id.add);
        BackBtn = (Button) view.findViewById(R.id.back);
        AlarmView = (RecyclerView) view.findViewById(R.id.alarmView);
        AlarmView.setNestedScrollingEnabled(false);
        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("MedName");


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get total available quest
                friends = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String friend = ds.getKey();
                    friends.add(friend);
                }
                size = (int) dataSnapshot.getChildrenCount();
                Log.i("TakeMedsFrag","line 158 "+size);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public class Model {

        public String mTitle;

        public Model() {
        }

        public Model(String mTitle) {
            this.mTitle = mTitle;
        }


        public String getmTitle() {
            return mTitle;
        }

        public void setmTitle(String mTitle) {
            this.mTitle = mTitle;
        }


    }

    private void setListeners() {

        Addbtn.setOnClickListener(this);
        BackBtn.setOnClickListener(this);
    }


        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView txtTitle;
            public ViewHolder(View itemView) {
                super(itemView);
                txtTitle = itemView.findViewById(R.id.alarm_title);
            }

            public void setTxtTitle(String string) {
                txtTitle.setText(string);
            }

        }

        private void fetch () {
            RootRef = FirebaseDatabase.getInstance().getReference();
            DemoRef1 = RootRef.child("MedName");

            FirebaseRecyclerOptions<Model> options =
                    new FirebaseRecyclerOptions.Builder<Model>()
                            .setQuery(query, new SnapshotParser<Model>() {
                                @NonNull
                                @Override
                                public Model parseSnapshot(@NonNull DataSnapshot snapshot) {
                                       return new Model(
                                       s= snapshot.getValue().toString());

                                }
                            })
                            .build();

            mFireAdapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
                @Override
                public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.list_item, parent, false);
                    Log.i("TakeMedsFrag","line 171 "+size);


                    return new ViewHolder(view);
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public int getItemViewType(int position) {
                    return position;
                }

                @Override
                protected void onBindViewHolder(ViewHolder holder,int position, Model model) {
                    Log.i("TakeMedsFrag","line 196 "+getItemCount());
                    friends.get(position);
                        Log.i("TakeMedsFrag", "line 197 " + getItemCount());

                    if (!mFireAdapter.hasObservers()) {
                        mFireAdapter.setHasStableIds(true);
                    }
                    model.setmTitle(s);
                    holder.setTxtTitle(model.getmTitle());
                    Log.i("TakeMedsFrag","line 195 "+getItemCount());

                }

                @Override
                public int getItemCount() {
                    return size;
                }


            };
            AlarmView.setAdapter(mFireAdapter);
            Log.i("TakeMedsFrag","line 191 "+size);

        }
@Override
        public void onStart () {
            super.onStart();
            mFireAdapter.startListening();
        }

@Override
        public void onStop () {
            super.onStop();
            mFireAdapter.stopListening();
        }





        @Override
        public void onClick (View v){
            switch (v.getId()) {
                case R.id.add:
                    fragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                            .replace(R.id.frameContainer,
                                    new TakeMedsAlarm_Fragment(),
                                    Utils.TakeMedsAlarm_Fragment).commit();



                    break;
                case R.id.back:
                    new MainActivity().replaceMedicalAid_Fragment();


            }
        }
    @Override
    public boolean onBackPressed() {
        Toast.makeText(getActivity(),"You'll be directed to HomePage",Toast.LENGTH_LONG).show();
        return false;

    }

    }





package com.example.wecare;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RestockMeds1_Fragment extends Fragment implements MainActivity.OnBackPressedListener {
    private static View view;
    private static LinearLayout restockmeds1_layout;
    private static Animation shakeAnimation;
    private static Button AddBtn, BackBtn;
    private static FragmentManager fragmentManager;
    private DatabaseReference RootRef, DemoRef11, DemoRef21;
    private FirebaseUser user;
    String uid;
    public static String s = null;
    public  ArrayList<String> arrayList;
    private RecyclerView Restockview;
    private FirebaseRecyclerAdapter mFireAdapter;
    private LinearLayoutManager linearLayoutManager1;
    public Query query;
    int n;


    public RestockMeds1_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.restockmedsalarm1_layout, container, false);

        Restockview = (RecyclerView) view.findViewById(R.id.RestockView);


        fragmentManager = getActivity().getSupportFragmentManager();
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);
        AddBtn = (Button) view.findViewById(R.id.addr);
        BackBtn = (Button) view.findViewById(R.id.back);
        user = FirebaseAuth.getInstance().getCurrentUser();
        RootRef = FirebaseDatabase.getInstance().getReference();
        uid= user.getUid();
        DemoRef11 = RootRef.child(uid).child("RMedName");


        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new RestockMeds_Fragment(),
                                Utils.RestockMeds_Fragment).commit();
            }

        });

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MainActivity().replaceMedicalAid_Fragment();

            }
        });
        Restockview.setLayoutManager(new LinearLayoutManager(getContext()));
        Restockview.setHasFixedSize(true);
        Restockview.setAdapter(mFireAdapter);

        fetch();
        return view;
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


    private void fetch() {



        DemoRef11.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList = new ArrayList<String>();
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    String name = (String) messageSnapshot.getKey();
                    arrayList.add(name);
                }
                if(arrayList.size()!=0) {
                    n = arrayList.size();
                }
                Log.i("UserManager", "Name : "+ arrayList.size());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(DemoRef11, new SnapshotParser<Model>() {
                            @NonNull
                            @Override
                            public Model parseSnapshot(@NonNull DataSnapshot snapshot) {

                                return new Model(
                                        s = snapshot.getValue().toString());

                            }
                        })
                        .build();
        mFireAdapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false);
                return new ViewHolder(view);
            }
            @Override
            protected void onBindViewHolder(final ViewHolder holder,
                                            final int position, final Model model) {
                holder.setTxtTitle(model.getmTitle());


                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        fragmentManager
                                .beginTransaction()
                                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                                .replace(R.id.frameContainer,
                                        new RestockMeds_Fragment(),
                                        Utils.RestockMeds_Fragment).commit();

                    }
                });
            }

            @Override

            public int getItemCount(){

                    return n;

            }
        };
        Restockview.setAdapter(mFireAdapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        mFireAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mFireAdapter.stopListening();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtTitle;

        public ViewHolder(View itemView){
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtTitle = itemView.findViewById(R.id.alarm_title);
        }

        public void setTxtTitle(String string) {
            txtTitle.setText(string);
        }

    }

    @Override
    public boolean onBackPressed() {
        Toast.makeText(getActivity(), "You'll be directed to HomePage", Toast.LENGTH_LONG).show();
        return false;

    }


}




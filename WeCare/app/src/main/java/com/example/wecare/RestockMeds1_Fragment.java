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


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RestockMeds1_Fragment extends Fragment implements View.OnClickListener {
    private static View view;
    private static LinearLayout restockmeds1_layout;
    private static Animation shakeAnimation;
    private static Button AddBtn, RemoveBtn, BackBtn;
    private static FragmentManager fragmentManager;
    private DatabaseReference RootRef, DemoRef11, DemoRef21;
    public static final String TAG = "YOUR-TAG-NAME";
    private RecyclerView Restockview;
    private FirebaseRecyclerAdapter mFireAdapter;
    private LinearLayoutManager linearLayoutManager1;
    public Query query;


    public RestockMeds1_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.restockmedsalarm1_layout, container, false);
        initViews();
        Restockview = (RecyclerView) view.findViewById(R.id.RestockView);
        Restockview.setLayoutManager(new LinearLayoutManager(getContext()));
        Restockview.setHasFixedSize(true);

        Restockview.setAdapter(mFireAdapter);

        setListeners();

        fetch();

        return view;
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);
        AddBtn = (Button) view.findViewById(R.id.addr);
        BackBtn = (Button) view.findViewById(R.id.back);
        RootRef = FirebaseDatabase.getInstance().getReference();
        DemoRef11 = RootRef.child("RMedName");
        DemoRef21 = RootRef.child("RMedDose");
        RemoveBtn = (Button) view.findViewById(R.id.remover);
        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("MedName");


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

        AddBtn.setOnClickListener(this);
        RemoveBtn.setOnClickListener(this);
        BackBtn.setOnClickListener(this);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtTitle = itemView.findViewById(R.id.alarm_title);
        }

        public void setTxtTitle(String string) {
            txtTitle.setText(string);
        }

    }

    private void fetch() {


        FirebaseRecyclerOptions<RestockMeds1_Fragment.Model> options =
                new FirebaseRecyclerOptions.Builder<RestockMeds1_Fragment.Model>()
                        .setQuery(query, new SnapshotParser<RestockMeds1_Fragment.Model>() {
                            @NonNull
                            @Override
                            public RestockMeds1_Fragment.Model parseSnapshot(@NonNull DataSnapshot snapshot) {

                                return new RestockMeds1_Fragment.Model(
                                        snapshot.child("MedName").getValue().toString());

                            }
                        })
                        .build();

        mFireAdapter = new FirebaseRecyclerAdapter<RestockMeds1_Fragment.Model, RestockMeds1_Fragment.ViewHolder>(options) {
            @Override
            public RestockMeds1_Fragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false);
                return new RestockMeds1_Fragment.ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(final RestockMeds1_Fragment.ViewHolder holder,
                                            final int position, final RestockMeds1_Fragment.Model model) {
                RootRef = FirebaseDatabase.getInstance().getReference();
                DemoRef11 = RootRef.child("RMedName");
                DemoRef21 = RootRef.child("RMedDose");

                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Model model= dataSnapshot.getValue(Model.class);
                        String value=dataSnapshot.getValue(String.class);


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
                    public void onCancelled(DatabaseError firebaseError) {
                        Log.e(TAG, "Unable to load grid image: " + firebaseError.getMessage());

                    }

                });

                Restockview.setAdapter(mFireAdapter);


            }
        };
        }
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.addr:
                        fragmentManager
                                .beginTransaction()
                                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                                .replace(R.id.frameContainer,
                                        new RestockMeds_Fragment(),
                                        Utils.RestockMeds_Fragment).commit();
                        onStart1();
                        break;

                    case R.id.back:

                        // Replace login fragment
                        new MainActivity().replaceMedicalAid_Fragment();
                        break;


                }

            }


            public void onStart1() {
                super.onStart();
                mFireAdapter.startListening();
            }

            @Override
            public void onStop() {
                super.onStop();
                mFireAdapter.stopListening();
            }


    }




package com.bobcikprogramming.genertorhesla.view;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bobcikprogramming.genertorhesla.R;
import com.bobcikprogramming.genertorhesla.controllers.GeneratePassword;
import com.bobcikprogramming.genertorhesla.controllers.PatternSetting;
import com.bobcikprogramming.genertorhesla.model.PatternEntity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class PatternList extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    private View view;
    private ImageView btnLogout, btnDelete, btnHelp, btnAccSetting, btnCancel;
    private EditText etSearch;

    private List<PatternEntity> dataFromDatabase;
    private List<PatternEntity> patternListToShow;
    private RecyclerViewPattern adapter;

    private Paint paint = new Paint();
    private Bundle bundle;

    private GeneratePassword generate;
    private boolean inNewManual;

    public PatternList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pattern_list, container, false);

        patternListToShow = new ArrayList<>();
        generate = new GeneratePassword(getContext());

        bundle = this.getArguments();
        inNewManual = bundle != null && bundle.getBoolean("newManual");

        setupUIViews();
        setupAdapter();
        onEditTextChange();
        if(!inNewManual) {
            setUIIfNotNewManual();
            swipeToDelete();
        }else{
            setUIIfNewManual();
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLogout:
                etSearch.setText("");
                getActivity().finish();
                break;
            case R.id.btnDelete:
                AnimatedVectorDrawable animatedVectorDrawableDelete =
                        (AnimatedVectorDrawable) btnDelete.getDrawable();
                animatedVectorDrawableDelete.start();

                etSearch.setText("");
                break;
            case R.id.btnCancel:
                Intent intent = new Intent(getContext(), BottomTabBar.class);
                intent.putExtra("pattern", bundle.getSerializable("pattern"));
                getActivity().setResult(RESULT_OK, intent);
                getActivity().finish();
            case R.id.btnAccSetting:
                Intent intentAcc = new Intent(getContext(), AccountSetting.class);
                startActivity(intentAcc);
                break;
        }
    }

    private void setupUIViews(){
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        etSearch = view.findViewById(R.id.etSearch);

        btnLogout = view.findViewById(R.id.btnLogout);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnHelp = view.findViewById(R.id.btnHelp);
        btnAccSetting = view.findViewById(R.id.btnAccSetting);
        btnCancel = view.findViewById(R.id.btnCancel);

        btnLogout.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnHelp.setOnClickListener(this);
        btnAccSetting.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void setupAdapter(){
        adapter = new RecyclerViewPattern((getActivity()), myClickListener);
        recyclerView.setAdapter(adapter);
        dataFromDatabase = generate.loadPatternListFromDatabase();
        patternListToShow.addAll(dataFromDatabase);
        adapter.setPatternData(patternListToShow);
    }

    private void onEditTextChange(){
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searching = etSearch.getText().toString();
                if(searching.isEmpty()){
                    patternListToShow.addAll(dataFromDatabase);
                }else {
                    patternListToShow = generate.searchForPatternByName(searching, dataFromDatabase);
                }
                adapter.dataListChange(patternListToShow);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    // https://stackoverflow.com/a/45711180
    private View.OnClickListener myClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            int position = (int) view.getTag();
            String id = String.valueOf(dataFromDatabase.get(position).uidPattern);
            PatternSetting patternSetting = generate.getPatternSettingFromDatabaseById(id);
            if(patternSetting != null) {
                Intent intent = new Intent(getContext(), BottomTabBar.class);
                intent.putExtra("pattern", patternSetting);
                if(!inNewManual) {
                    startActivity(intent);
                }else{
                    getActivity().setResult(RESULT_OK, intent);
                    getActivity().finish();
                }
            }
        }
    };

    private void swipeToDelete() {
        ItemTouchHelper.SimpleCallback itemTouchHelperSimpleCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                PatternEntity restoreDataOfPattern = patternListToShow.remove(position);
                int positionInDataFromDb = dataFromDatabase.indexOf(restoreDataOfPattern);
                dataFromDatabase.remove(positionInDataFromDb);
                adapter.dataListChange(patternListToShow);
                generate.removePatternById(restoreDataOfPattern.uidPattern);

                snackBarForRestoreData(position, positionInDataFromDb, restoreDataOfPattern);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    Bitmap icon;
                    Drawable drawable = getActivity().getDrawable(R.drawable.ic_delete);
                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX <= 0){
                        paint.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, paint);
                        icon = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(icon);
                        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                        drawable.draw(canvas);
                        RectF iconRect = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        if (dX < itemView.getLeft()-2*width) {
                            c.drawBitmap(icon, null, iconRect, paint);
                        }
                    }
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperSimpleCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void snackBarForRestoreData(int position, int positionInDataFromDb, PatternEntity data){
        final Snackbar snackbar = Snackbar.make(view, "Vzor '" + data.name + "' smazán ze seznamu!", 5000);
        snackbar.setAction("Vrátit zpět", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patternListToShow.add(position, data);
                dataFromDatabase.add(positionInDataFromDb, data);
                adapter.dataListChange(patternListToShow);
                generate.restorePatternInDatabase(data);
            }
        });
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.setBackgroundTint(getActivity().getColor(R.color.navBarBackground));
        snackbar.setActionTextColor(getActivity().getColor((R.color.button)));
        snackbar.setTextColor(getActivity().getColor(R.color.white));
        snackbar.show();
    }

    private void setUIIfNewManual(){
        btnHelp.setVisibility(View.GONE);
        btnLogout.setVisibility(View.INVISIBLE);
        btnAccSetting.setVisibility(View.INVISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
    }

    private void setUIIfNotNewManual(){
        btnHelp.setVisibility(View.VISIBLE);
        btnLogout.setVisibility(View.VISIBLE);
        btnAccSetting.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.GONE);
    }
}
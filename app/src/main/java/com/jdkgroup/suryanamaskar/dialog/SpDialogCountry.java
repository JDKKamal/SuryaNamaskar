package com.jdkgroup.suryanamaskar.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.jdkgroup.suryanamaskar.R;
import com.jdkgroup.suryanamaskar.adapter.CountrySpAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class SpDialogCountry extends Dialog {
    AppCompatEditText appEdtSearch;
    RelativeLayout rlSearch;
    AppCompatTextView appTvCancel;
    AppCompatTextView appTvTitle;
    RecyclerView recyclerView;

    private Activity activity;
    private WindowManager.LayoutParams lp;
    private OnItemClick itemSelect;
    private String dialogTitle = "Select";

    private CountrySpAdapter countrySpAdapter;
    private List alList = new ArrayList<>();

    public SpDialogCountry(Activity activity, OnItemClick itemSelect, List<?> alList) {
        super(activity);
        this.activity = activity;
        this.itemSelect = itemSelect;
        this.alList.addAll(alList);
    }

    public SpDialogCountry(Activity activity, String title, OnItemClick itemSelect, List<?> alList) {
        super(activity);
        this.activity = activity;
        this.dialogTitle = title;
        this.itemSelect = itemSelect;
        this.alList.addAll(alList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        setContentView(R.layout.custom_spinner_dialog);

        appTvTitle = findViewById(R.id.appTvTitle);
        appEdtSearch = findViewById(R.id.appEdtSearch);
        recyclerView = findViewById(R.id.recyclerView);
        appTvCancel = findViewById(R.id.appTvCancel);
        rlSearch = findViewById(R.id.rlSearch);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        if (alList.size() >= 5) {
            rlSearch.setVisibility(View.VISIBLE);
        }

        appTvTitle.setText(dialogTitle);

        countrySpAdapter = new CountrySpAdapter(activity, alList, object -> {
            dismiss();
            if (itemSelect != null)
                itemSelect.selectedItem(object);
        });
        recyclerView.setAdapter(countrySpAdapter);

        //TODO FILTER
        filterCountry();

        Display display = activity.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();

        final int height = display.getHeight();
        lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());

        lp.width = (int) (width - (width * 0.15));
        this.recyclerView.postDelayed(() -> {
            if (recyclerView.getHeight() >= ((int) (height - (height * 0.20))))
                lp.height = (int) (height - (height * 0.20));
            else
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            getWindow().setAttributes(lp);
        }, 10);
    }

    private void filterCountry() {
        appEdtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (countrySpAdapter != null) {
                    //countrySpAdapter.filter(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public interface OnItemClick {
        void selectedItem(Object object);
    }
}

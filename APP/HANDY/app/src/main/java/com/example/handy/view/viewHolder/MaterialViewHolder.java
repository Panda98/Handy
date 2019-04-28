package com.example.handy.view.viewHolder;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MaterialViewHolder extends BaseViewHolder {
    @BindView(R.id.material_name_edtext)
    EditText materialNameEdText;
    @BindView(R.id.material_amount_edtext)
    EditText amountEdText;

    public MaterialViewHolder(View view){
        super(view);
        ButterKnife.bind(this, view);
    }

    public EditText getMaterialNameEdText() {
        return materialNameEdText;
    }

    public EditText getAmountEdText() {
        return amountEdText;
    }


}

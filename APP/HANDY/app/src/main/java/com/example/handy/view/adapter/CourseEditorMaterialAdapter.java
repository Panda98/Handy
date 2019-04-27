package com.example.handy.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handy.R;
import com.example.handy.core.bean.MaterialItemData;
import com.example.handy.view.viewHolder.MaterialViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xuexiang.xupdate.XUpdate.getContext;

public class CourseEditorMaterialAdapter extends BaseQuickAdapter<MaterialItemData,MaterialViewHolder> {

    private HashMap<String,String> infos;

    public CourseEditorMaterialAdapter(int layoutResId, @Nullable List<MaterialItemData> data){
        super(layoutResId, data);
        infos = new HashMap<>();
    }

    @Override
    protected void convert(MaterialViewHolder helper, MaterialItemData item) {

//        helper.setText(R.id.material_amount_edtext,item.getItemName())
//                .setText(R.id.material_amount_edtext,item.getItemNumber());

        EditText materialNameEdText = helper.getMaterialNameEdText();
        EditText amountEdText = helper.getAmountEdText();
        amountEdText.addTextChangedListener(new AmountEditListner(materialNameEdText));
        materialNameEdText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    InputMethodManager manager = ((InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
                    if (manager != null)
                        manager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
        amountEdText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    InputMethodManager manager = ((InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
                    if (manager != null)
                        manager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
    }

    class AmountEditListner implements TextWatcher{
        private EditText nameEditText;

        public AmountEditListner(EditText nameEditText){
            this.nameEditText = nameEditText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            infos.put(nameEditText.getText().toString(),editable.toString());

        }
    }

    public HashMap<String, String> getInfos(){
        return infos;
    }


}

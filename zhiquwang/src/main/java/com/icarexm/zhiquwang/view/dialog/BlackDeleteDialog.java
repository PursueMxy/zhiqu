package com.icarexm.zhiquwang.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.icarexm.zhiquwang.R;

public class BlackDeleteDialog  extends Dialog {
    public BlackDeleteDialog(@NonNull Context context, int bank_id, String token, Activity activity) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

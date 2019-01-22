package com.academiamoviles.d20183.apprestaurantes.cliente.util;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;

public class MainUtil {

    public static void openDialog(@NonNull Dialog dialog) {
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}

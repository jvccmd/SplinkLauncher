package com.splink.client;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    int purple = Color.rgb(145, 70, 255);
    int white = Color.WHITE;

    GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    TextView text(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        return t;
    }

    Button button(String value) {
        Button b = new Button(this);
        b.setText(value);
        b.setTextColor(white);
        b.setAllCaps(false);
        b.setTextSize(16);
        b.setBackground(shape(purple, 24));
        return b;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(28, 30, 28, 30);
        root.setBackground(new GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            new int[]{Color.rgb(8, 8, 14), Color.rgb(35, 12, 62)}
        ));

        TextView logo = text("SPLINK", 38, purple);
        logo.setTypeface(Typeface.DEFAULT_BOLD);
        root.addView(logo, new LinearLayout.LayoutParams(-1, 65));

        TextView subtitle = text("CLIENT  •  BEDROCK EDITION", 13, white);
        root.addView(subtitle, new LinearLayout.LayoutParams(-1, 38));

        TextView status = text("Your Minecraft launcher", 16,
            Color.LTGRAY);
        LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(-1, 70);
        root.addView(status, sp);

        Button play = button("▶   Launch Minecraft");
        LinearLayout.LayoutParams bp =
            new LinearLayout.LayoutParams(-1, 58);
        bp.setMargins(0, 18, 0, 12);
        root.addView(play, bp);

        play.setOnClickListener(v -> {
            Intent launch = getPackageManager()
                .getLaunchIntentForPackage("com.mojang.minecraftpe");
            if (launch != null) {
                startActivity(launch);
            } else {
                Toast.makeText(this,
                    "Minecraft Bedrock is not installed.",
                    Toast.LENGTH_LONG).show();
            }
        });

        Button info = button("About Splink");
        root.addView(info, new LinearLayout.LayoutParams(-1, 54));
        info.setOnClickListener(v -> Toast.makeText(this,
            "Splink Client v1.0.0 • Android Launcher",
            Toast.LENGTH_LONG).show());

        TextView footer = text(
            "Independent launcher • Not affiliated with Mojang",
            11, Color.LTGRAY);
        LinearLayout.LayoutParams fp =
            new LinearLayout.LayoutParams(-1, 70);
        root.addView(footer, fp);

        setContentView(root);
    }
}

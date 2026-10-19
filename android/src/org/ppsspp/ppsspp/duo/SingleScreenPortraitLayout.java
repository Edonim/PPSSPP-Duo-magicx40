package org.ppsspp.ppsspp.duo;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Initial layout scaffold for portrait handhelds.
 *
 * This helper is intentionally not wired into PpssppActivity yet: the Duo UI is currently
 * owned by DuoPresentation (a separate-display window), so the real integration must first
 * separate its content view from that window lifecycle. Keeping this scaffold isolated lets
 * us test the layout strategy without changing the default emulator rendering path.
 */
public final class SingleScreenPortraitLayout {
    private SingleScreenPortraitLayout() {}

    /**
     * Builds a portrait column with the emulator surface above a placeholder Duo panel.
     * The game surface keeps a larger share of the available height.
     */
    public static View create(Context context, View gameSurface) {
        LinearLayout column = new LinearLayout(context);
        column.setOrientation(LinearLayout.VERTICAL);
        column.setBackgroundColor(Color.BLACK);

        column.addView(gameSurface, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 2.0f));

        FrameLayout panel = new FrameLayout(context);
        panel.setBackgroundColor(Color.rgb(24, 27, 33));

        TextView label = new TextView(context);
        label.setText("PPSSPP Duo — single-screen mode");
        label.setTextColor(Color.WHITE);
        label.setTextSize(14);
        label.setGravity(Gravity.CENTER);
        label.setPadding(12, 12, 12, 12);

        panel.addView(label, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        column.addView(panel, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f));

        return column;
    }
}

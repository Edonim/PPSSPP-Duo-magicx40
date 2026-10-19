package org.ppsspp.ppsspp.duo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Vertical layout for the emulator surface and embedded Duo panel.
 *
 * This helper is intentionally not wired into PpssppActivity yet: the Duo UI is currently
 * owned by DuoPresentation (a separate-display window), so the real integration must first
 * separate its content view from that window lifecycle. Keeping this scaffold isolated lets
 * us test the layout strategy without changing the default emulator rendering path.
 */
public final class SingleScreenPortraitLayout {
    private SingleScreenPortraitLayout() {}

    /**
     * Builds a portrait column with the game surface above the real Duo panel.
     * The game surface keeps a larger share of the available height.
     */
    public static View create(Context context, View gameSurface, View duoPanel) {
        LinearLayout column = new LinearLayout(context);
        column.setOrientation(LinearLayout.VERTICAL);
        column.setBackgroundColor(android.graphics.Color.BLACK);

        column.addView(gameSurface, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 2.0f));

        FrameLayout panel = new FrameLayout(context);
        panel.setBackgroundColor(android.graphics.Color.rgb(24, 27, 33));
        if (duoPanel.getParent() instanceof ViewGroup) {
            ((ViewGroup) duoPanel.getParent()).removeView(duoPanel);
        }
        panel.addView(duoPanel, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        column.addView(panel, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f));

        return column;
    }
}

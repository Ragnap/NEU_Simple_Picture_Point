package custom.listener;

import custom.panel.ColorSettingBar;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class ColorSettingBarListener implements AdjustmentListener {

    ColorSettingBar colorSettingBar;

    public void setColorSettingBar(ColorSettingBar colorSettingBar) {
        this.colorSettingBar = colorSettingBar;
    }

    public void adjustmentValueChanged(AdjustmentEvent e) {
        colorSettingBar.updatePreview();
    }
}

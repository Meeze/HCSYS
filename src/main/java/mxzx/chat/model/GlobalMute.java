package mxzx.chat.model;

import lombok.Data;

@Data
public class GlobalMute {

    private boolean enabled;

    public GlobalMute(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

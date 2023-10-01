package mxzx.chat.controller;

import lombok.Data;
import lombok.Getter;
import mxzx._core.BaseController;
import mxzx.chat.model.GlobalMute;

@Getter
@Data
public class GlobalMuteController implements BaseController {

    private GlobalMute globalMute;

    public GlobalMuteController(GlobalMute globalMute) {
        this.globalMute = globalMute;
    }

    public void setGlobalMute(GlobalMute globalMute) {
        this.globalMute = globalMute;
    }
}

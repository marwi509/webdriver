package no.marcus.mrfridge;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class PostParam {
    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    private String param;

}

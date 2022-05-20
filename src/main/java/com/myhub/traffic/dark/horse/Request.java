package com.myhub.traffic.dark.horse;

import com.myhub.traffic.dark.horse.dispatch.Dispatcher;

public class Request {
    public void newRequest(String resource) {
        Dispatcher.dispatch(resource);
    }
}

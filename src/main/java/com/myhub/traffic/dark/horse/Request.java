package com.myhub.traffic.dark.horse;

import com.myhub.traffic.dark.horse.dispatch.Dispatcher;

public class Request {
    private Dispatcher dispatcher;

    public Request () {
        dispatcher = new Dispatcher();
    }

    public void newRequest(String resource) {
        dispatcher.dispatch(resource);
    }
}

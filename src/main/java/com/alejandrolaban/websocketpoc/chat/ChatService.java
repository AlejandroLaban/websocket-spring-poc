package com.alejandrolaban.websocketpoc.chat;

public interface ChatService {

    Object start(Object o);

    Object setTypingState(Object o);

    Object sendMessage(Object o);

    Object history(Object o);

    Object pool(Object o);
}

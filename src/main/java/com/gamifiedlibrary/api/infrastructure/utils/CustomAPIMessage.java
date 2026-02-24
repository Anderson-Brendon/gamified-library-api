package com.gamifiedlibrary.api.infrastructure.utils;

import java.util.HashMap;
import java.util.Map;

public class CustomAPIMessage {

    
    static public Map<String, String> setMessage(String keyName, String messageContent) {
       Map<String, String> message = new HashMap<>();
       message.put(keyName, messageContent);
       return message;
    }


}

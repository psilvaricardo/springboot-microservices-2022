package com.microservicesws.util;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class MovieInfoConstants {

    private static
    Map<String, String> map = new HashMap<String, String>(
        Map.ofEntries(
            new AbstractMap.SimpleEntry<String, String>("api.key", "06570a1aaab3cd622f52296e7163edfd"),
            new AbstractMap.SimpleEntry<String, String>("access_token", "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNjU3MGExYWFhYjNjZDYyMmY1MjI5NmU3MTYzZWRmZCIsInN1YiI6IjYzODZhZGQwMjI5YWUyMTU1YzU0YWUxYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.33jIhNpgTkV6NQALyEIOBt3dDeyMViOY_GwtXNM1hh0")
        )
    );

    public static String getValueOf( String key ) {
        return map.get(key);
    }

}

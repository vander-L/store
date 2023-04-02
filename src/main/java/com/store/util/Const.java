package com.store.util;

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

public class Const {
    public static final int OK = 200;
    public static final int AVATAR_MAXSIZE = 10 * 1024 * 1024;
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    public static int ADDRESS_MAX_COUNT = 20;

    static {
        AVATAR_TYPE.add("image/jpg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/gif");
        AVATAR_TYPE.add("image/bmp");
    }
}

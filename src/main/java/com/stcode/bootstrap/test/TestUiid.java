package com.stcode.bootstrap.test;

import java.util.UUID;

public class TestUiid {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }
}

package com.stcode.bootstrap.controller.test;

import java.util.UUID;

public class TEST {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }
}

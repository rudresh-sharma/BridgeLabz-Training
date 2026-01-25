package com.collections.annotations.deprecatedannotation;

public class DeprecatedDemo {

    public static void main(String[] args) {

        LegacyAPI api = new LegacyAPI();

        api.oldFeature();   // This will give a DEPRECATION WARNING
        api.newFeature();  // Preferred method
    }
}

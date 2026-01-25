package com.collections.annotations.deprecatedannotation;

public class LegacyAPI {

    @Deprecated
    public void oldFeature() {
        System.out.println("This is the old feature (Deprecated)");
    }

    public void newFeature() {
        System.out.println("This is the new feature");
    }
}

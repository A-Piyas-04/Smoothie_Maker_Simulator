package com.smoothiemaker.model;

public class MugFactory {
    public static Mug createMug(String type) {
        return switch (type) {
            case "Glass Mug" -> new GlassMug();
            case "Plastic Mug" -> new PlasticMug();
            default -> throw new IllegalArgumentException("Unknown mug type: " + type);
        };
    }
}
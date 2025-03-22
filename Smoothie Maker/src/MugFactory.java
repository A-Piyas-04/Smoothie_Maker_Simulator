class MugFactory {
    public static Mug createMug(String type) {
        if (type.contains("Plastic")) return new PlasticMug();
        if (type.contains("Glass")) return new GlassMug();
        throw new IllegalArgumentException("Invalid mug type");
    }
}
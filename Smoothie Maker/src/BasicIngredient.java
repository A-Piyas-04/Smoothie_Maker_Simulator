class BasicIngredient implements Ingredient {
    private String name;
    private int score;
    
    public BasicIngredient(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getScore() {
        return score;
    }
}
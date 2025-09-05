package com.example;

import java.util.List;

public class Lion {

    boolean hasMane;
    private final Predator predator;
    private final KittensProvider kittensProvider;

    public Lion(String sex, Predator predator) throws Exception {
        this.predator = predator;
        this.kittensProvider= new KittensProvider(predator);
        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного - самец или самка");
        }
    }



    public int getKittens() {
        return kittensProvider.getKittens();
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public List<String> getFood() throws Exception {
        return predator.eatMeat();
    }
    private static class KittensProvider {
        private final Predator predator;

        public KittensProvider(Predator predator){
            this.predator = predator;
        }
        public int getKittens(){
            if (predator instanceof Feline){
                return ((Feline) predator).getKittens();
            }
            return 0;
        }
    }
}

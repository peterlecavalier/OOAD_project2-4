import java.util.Random;

public class Helpers {
    public String nameGen(){
        // Generates a random string with 10 letters
        // Ex: GJWOGYPFMP

        // Converting ascii code to string from here:
        // https://www.delftstack.com/howto/java/java-ascii-to-char/

        String name = "";
        Random rng = new Random();

        // add ten letters to string
        for (int i = 0; i < 10; i++){
            name += Character.toString(rng.nextInt(26) + 65);
        }

        return name;
    }

    public double purchasePriceGen(){
        //Rounding to 2 decimals from here:
        // https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java

        Random rng = new Random();
        double purchasePrice = (rng.nextDouble() * 49.0) + 1.0;

        purchasePrice = Math.round(purchasePrice * 100.0) / 100.0;
        return purchasePrice;
    }

    public String newUsedGen(){
        // Randomly generates and returns whether the item
        // is new or used
        Random rng = new Random();
        boolean rngResult = rng.nextBoolean();

        if (rngResult){
            return "new";
        }
        else{
            return "used";
        }
    }

    public String condGen(){
        // Randomly generates and returns the item condition
        Random rng = new Random();
        String conditions[] = {"poor", "fair", "good", "very good", "excellent"};
        int rngResult = rng.nextInt(5);

        return conditions[rngResult];
    }

    public boolean boolGen(){
        // Randomly generates a boolean, either true or false
        Random rng = new Random();
        return rng.nextBoolean();
    }

    public String fluteTypeGen(){
        // Randomly generates and returns the flute type
        Random rng = new Random();
        String types[] = {"standard", "piccolo", "harmony"};
        int rngResult = rng.nextInt(3);

        return types[rngResult];
    }

    public String harmKeyGen(){
        // Randomly generates and returns the harmonica key
        Random rng = new Random();
        String keys[] = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};
        int rngResult = rng.nextInt(12);

        return keys[rngResult];
    }

    public String sizeGen(){
        // Randomly generates and returns the hat/shirt size
        Random rng = new Random();
        String sizes[] = {"XS", "S", "M", "L", "XL", "XXL", "XXL"};
        int rngResult = rng.nextInt(7);

        return sizes[rngResult];
    }

    public int wattLenGen(){
        // Randomly generates and returns an amp wattage (or cable length)
        // between 1-200.
        Random rng = new Random();
        return (rng.nextInt(200) + 1);
    }

    public String stringGen(){
        // Randomly generates and returns the string type
        Random rng = new Random();
        String strings[] = {"Steel", "Nickel", "Brass", "Bronze"};
        int rngResult = rng.nextInt(4);

        return strings[rngResult];
    }
}

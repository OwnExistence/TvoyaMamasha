package content;

public final class gameRPS {
    private static String userChoice(String hand){
        if(hand.equals("камень") || hand.equals("ножницы") || hand.equals("бумага")) {
            return hand;
        } else {
            return "Ты пидор, вызвал эксепшн";
        }
    }

    private static String computerChoice(){
        int random = (int)Math.floor(Math.random()*3);
        String hand = "";
        switch (random) {
            case 1:
                hand = "камень";
                break;
            case 2:
                hand = "ножницы";
                break;
            case 3:
                hand = "бумага";
                break;
        }
        return hand;
    }

    public static String winner(String hand){
        if(userChoice(hand).equals(computerChoice())) {
            return "Ничья";
        } else if(userChoice(hand).equals("ножницы")) {
            if (computerChoice().equals("камень")) {
                return "Компьютер Победил";
            } else {
                return "Игрок Победил";
            }
        } else if(userChoice(hand).equals("камень")) {
            if (computerChoice().equals("бумага")) {
                return "Компьютер Победил";
            } else {
                return "Игрок Победил";
            }
        } else if(userChoice(hand).equals("бумага")) {
            if (computerChoice().equals("ножницы")) {
                return "Компьютер Победил";
            } else {
                return "Игрок Победил";
            }
        } else {
            return userChoice(hand);
        }
    }
}

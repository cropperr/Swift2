package Task_00_Cards;

import java.util.EnumSet;


public class Program {
    public static void main(String[] args) {
        
        System.out.println(java.util.Arrays.asList(CardSuit.values()));
        System.out.println(java.util.Arrays.asList(CardRank.values()));
        System.out.println();
        // 2 way : 
       for (CardSuit info : EnumSet.allOf(CardSuit.class)) {
    System.out.println(info); 
       }
        System.out.println();
        System.out.println("Card rank : ");
    for (CardRank info : EnumSet.allOf(CardRank.class)) {
     System.out.println(info);
    }
    }
}
    





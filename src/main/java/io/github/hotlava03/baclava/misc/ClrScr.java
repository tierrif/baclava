package io.github.hotlava03.baclava.misc;


public class ClrScr {
    public static void clrscr(){
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        for(int i = 0; i < 35; i++){
            System.out.println("\n");
        }
        System.out.println("Bot is loaded.\n");
    }
}

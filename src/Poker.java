import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
public class Poker {
	static int[] deck = new int[52];
	static int[] mainDeck = new int[5];
	static int tries = 0;
	
	static double noPairZahl = 0;
	static double pairZahl = 0;
	static double twoPairZahl = 0;
	static double threeOfAKindZahl = 0;
	static double fourOfAKindZahl = 0;
	static double fullHouseZahl = 0;
	static double straightZahl = 0;
	static double flushZahl = 0;
	static double straightFlushZahl = 0;
	static double royalFlushZahl = 0;

	public static void main(String[] args) {
		for (int i = 0; i < deck.length; i++) {
			deck[i] = i;
		}
		System.out.println("Wie viele Versuche : ");
		Scanner sc = new Scanner(System.in);
		while(tries == 0)
		{
			try
			{
				tries = Integer.parseInt(sc.next()); 
			}
			catch (Exception e) {
				System.out.println("Du Hund gib zahl ein");
			}

		}
		
		for(int i = 0; i<= tries; i++)
		{			
			draw();
			if (royalFlush() == true) {
				royalFlushZahl++;
				continue;
			}
			if (straightFlush() == true) {
				straightFlushZahl++;
				continue;
			}
			if (fourOfAKind() == true) {
				fourOfAKindZahl++;
				continue;
			}
			if (fullHouse() == true) {
				fullHouseZahl++;
				continue;
			}
			if (flush() == true) {
				flushZahl++;
				continue;
			}
			if (straight() == true) {
				straightZahl++;
				continue;
			}
			if (threeOfAKind() == true) {
				threeOfAKindZahl++;
				continue;
			}
			if (twoPair() == true) {
				twoPairZahl++;
				continue;
			}
			if (onePair() == true) {
				pairZahl++;
				continue;
			}
			noPairZahl++;
		}
		System.out.println(tries+" Versuche : ");
		System.out.println("NoPairs = "+(noPairZahl/tries)*	100 +"%");
		System.out.println("Pair = "+(pairZahl/tries)*	100 +"%");
		System.out.println("TwoPairs = "+(twoPairZahl/tries)*100 +"%");
		System.out.println("Three of a kind = "+(threeOfAKindZahl/tries)*100 +"%");
		System.out.println("Straight = "+(straightZahl/tries)*100 +"%");
		System.out.println("Flush = "+(flushZahl/tries)*100 +"%");
		System.out.println("Full House = "+(fullHouseZahl/tries)*100 +"%");
		System.out.println("Four of a kind = "+(fourOfAKindZahl/tries)*100 +"%");
		System.out.println("StraightFlush = "+(straightFlushZahl/tries)*100 +"%");
		System.out.println("RoyalFlush = "+(royalFlushZahl/tries)*100 +"%");
		sc.close();
	}
	static int getColor(int zahl) {
		assert ((zahl >= 0)&&(zahl < 55)):"Die Karte liegt nicht zwischen 1 und 55";
		if (zahl >= 0 && zahl < 13) {
			return 0;
		}
		if (zahl < 27 && zahl > 12) {
			return 1;
		}
		if (zahl < 41 && zahl > 26) {
			return 2;
		}
		if (zahl < 55 && zahl > 40) {
			return 3;
		}
		return -1;
	}
	// Zieht 5 Karten und verschiebt sie im array und definiert 
	//die Farbe und Nummer der Karte
	static void draw() {
		Random zufall = new Random();
		int lastArrayPlace;
		for (int i = 0; i < mainDeck.length; i++) {
			int randomIndex = zufall.nextInt((deck.length) - i);
			mainDeck[i] = deck[randomIndex];
			lastArrayPlace = deck[deck.length-1 - i];
			deck[deck.length-1 - i] = deck[randomIndex];
			deck[randomIndex] = lastArrayPlace;
		}
	}
	static int pair()
	{
		int zaehler = 0;
		for(int i = 0 ; i < mainDeck.length - 1 ; i++)
		{
			for(int j = i+1 ; j < mainDeck.length ; j++ )
			{
				if(mainDeck[i]%13 == mainDeck[j]%13)
				{
					zaehler++;
				}
			}
		}
		return zaehler;
	}
	static boolean onePair() {
		if(pair() == 1) 
		{
			return true;
		}
		return false;
	}
	static boolean twoPair() {
		if(pair() == 2) 
		{
			return true;
		}
		return false;
	}
	static boolean threeOfAKind() {
		if(pair() == 3) 
		{
			return true;
		}
		return false;
	}
	static boolean fourOfAKind() {
		if(pair() == 6) 
		{
			return true;
		}
		return false;
	}
	static boolean fullHouse()
	{
		if(pair() == 4) 
		{
			return true;
		}
		return false;
	}
	static boolean flush()
	{
		int zahler = 0;
		for(int i = 0 ; i < mainDeck.length-1 ; i++)
		{
			for(int j = i+1 ; j <mainDeck.length ; j++)
			{
				if(getColor(mainDeck[i])==getColor(mainDeck[j])) {zahler++;}
			}
		}
		if(zahler==10) return true;
		return false;
	}
	static boolean straight()
	{
		int[] mainDeckModulo = mainDeck.clone();
		for(int i = 0 ; i < mainDeckModulo.length ; i++)
		{
			mainDeckModulo[i] = mainDeckModulo[i]%13;
		}
		Arrays.sort(mainDeckModulo);
		for(int i  = 0 ; i < mainDeckModulo.length -1; i++)
		{
			if(mainDeckModulo[i]+1 == mainDeckModulo[i+1])
			{
				continue;
			}
			return false;
		}
		return true;
	}
	static boolean straightFlush()
	{
		if(straight() == true && flush()==true)
		{
			return true;
		}
		return false;
	}
	static boolean royalFlush()
	{
		Arrays.sort(mainDeck);
		if(mainDeck[0] %13 >= 8 && straightFlush())
		{
			return true;
		}
		return false;
	}
}
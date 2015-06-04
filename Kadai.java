import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;

public class Kadai {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int[] count = new int[27];//与えられた16文字の中でa-zが何回出現しているか数える配列
	int[] countL = new int[26];//与えられた16文字の中でA-Zが何回出現しているか数える配列
	ArrayList<String> list = new ArrayList<String>();
	String[] word = new String[16];//与えられた単語


	//ファイルを読み込み16文字以下の単語をArrayListにいれる
	try{
	    File inputFile = new File("/usr/share/dict/words");
	    Scanner scan = new Scanner(inputFile);
	    while(scan.hasNext()){
		String w = scan.next();
		if(w.length()<=16){
		    list.add(w);
		}
	    }
	}catch(FileNotFoundException e){
	    System.out.println(e);
	}

	//与えられた16文字を取得し出現回数を数える。
	System.out.println("16文字を取得");
	for(int i = 0;i < 16;i++){
	    word[i] = in.next();
	    setCount(word[i],count,countL);
	}

	//辞書を文字数の長い順番に並び替える
	Collections.sort(list, new Comparator<String>(){
		public int compare(String t1, String t2) {
		    return t2.length()-t1.length();
		}
	    });

	System.out.println("答えは"+find(list,count,countL)+"です。");

    }


    //答えを見つけるメソッド
    public static String find(ArrayList<String> list,int[] count,int[]countL){
	for(String dic:list) {
	    boolean t = true;//正しい答えを見つけたかどうか
	    int[] count1 = new int[27];//与えられた16文字の中でa-zが何回出現しているか数える配列
	    int[] count2 = new int[26];//与えられた16文字の中でA-Zが何回出現しているか数える配列
	    setCount(dic,count1,count2);
	    for(int i = 0;i < count2.length;i++){
		if(count[i] < count1[i]||countL[i] < count2[i]) {
		    t = false;
		    break;
		}
	    }
	    if(t) return dic;
	}
	return null;
    }

    //出現回数を数えるメソッド
    public static void setCount(String word,int[] count,int[] countL) {
	for(int i = 0;i < word.length();i++){
	    char w = word.charAt(i);
	    if ( w >= 'a' && w <= 'z' ){
		count[w-'a'] ++ ;
	    } else if ( w >= 'A' && w <= 'Z' ){
		countL[w-'A'] ++ ;
	    }
	}
    }
}   

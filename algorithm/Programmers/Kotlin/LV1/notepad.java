import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;

public class notepad {

    static int SIZE = 10000;
    static int[][] stack = new int[10][SIZE];
    static int[] queue = new int[SIZE];
    static Random random = new Random();
    static int[] sp = new int[10];

    public static void main(String[] args){
        for(int c = 0; c < SIZE; c++){
            int temp = random.nextInt(1023);
            int mod = temp % 9;

            stack[mod][sp[mod]++] = temp;
        }



        for(int count = 0; count < 9; count++){
            sp = new int[10];

            for(int i = 0; i < SIZE; i++){
                int min = 1024;

                for(int j = 0; j < 9; j++){
                    if(min > stack[j][sp[j]]){
                        min = stack[j+1][sp[j]++];
                    }
                }
                stack[9][i] = min;
            }

            sp = new int[10];

            for(int c = 0; c < SIZE; c++){
                int temp = stack[9][c];
                int mod = temp % (10);
                stack[mod][sp[mod]++] = temp;
            }
        }



        System.out.println(1);
    }
}

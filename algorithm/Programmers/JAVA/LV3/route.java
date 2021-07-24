package BaekJoon.AlgorithmSolve.Programmers.JAVA.LV3;

import java.util.*;

import javax.xml.stream.events.StartElement;

public class route {
    static List<String> list = new ArrayList<>();
    static List<String> result = new ArrayList<>(); 
    public static void main(String[] args){
        String[] answer;
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        String start = "ICN";
        String prev = "";
        dfs(tickets, start, prev);
        
        answer = new String[result.size()];
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i);
        }

        System.out.println(answer);
    }

    public static void dfs(String[][] tickets, String start, String prev) {
        
        result.add(start);
        prev = start;


        for(int i = 0; i < tickets.length; i++){
            if(tickets[i][0] == start){
                list.add(tickets[i][1]);
            }
        }
        if(list.size() == 0){
            return;
        }
        if(list.size() != 1){
            Collections.sort(list);

            start = list.get(0);
            for(int i = 0; i < tickets.length; i++){
                if(tickets[i][1] == start && tickets[i][0] == prev){
                    tickets[i][0] = "";
                    tickets[i][1] = "";
                }
            }
        
            list.clear();
            dfs(tickets, start, prev);
        }
        else if(list.size() == 1){
            start = list.get(0);
            for(int i = 0; i < tickets.length; i++){
                if(tickets[i][1] == start && tickets[i][0] == prev){
                    tickets[i][0] = "";
                    tickets[i][1] = "";
                }
            }
        
            list.clear();
            dfs(tickets, start, prev);
        }
        return;

    }
    
}

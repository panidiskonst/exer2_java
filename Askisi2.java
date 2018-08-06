import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class LinkedList
{
    Node head;  // head of list
    Node tail;
    static class Node
    {
        int x,y,t;
        char force;
        Node next;

        // Constructor to create a new node
        // Next is by default initialized
        // as null
        Node(int xi, int yi,int ti, char f) {x = xi; y=yi; t=ti; force=f; next=null;}
    }
    public void atEnd(int x, int y,int t, char f){
        Node new_node=new Node(x,y,t,f);
        if (head==null)
        {
            head=new_node;
            tail=new_node;
            return;
        }
        else
        {
            tail.next=new_node;
            tail=new_node;
            return;
        }
    }
    public int headx(){
        return head.x;
    }
    public int heady(){
        return head.y;
    }
    public int headt(){
        return head.t;
    }
    public char headf(){
        return head.force;
    }
    public void deleteHead(){

        if(head!=null){
            head=head.next;
            return;
        }
        else return;

    }

    public void printList()
    {
        Node tnode = head;
        while (tnode != null)
        {
            System.out.print(" [x:"+ tnode.x+" y:"+tnode.y+" force:"+tnode.force+"] -> ");
            tnode = tnode.next;
        }
    }
}

    /* Linked list Node*/

public class Askisi2 {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get(args[0]);
        Scanner reader = new Scanner(filePath);
        ArrayList<String> strings = new ArrayList<>();
        List<List<Character>> matrix= new ArrayList<List<Character>>();
        LinkedList llist = new LinkedList();
        int time=0,row=0,column=0;
        while (reader.hasNext()) {
            String string=reader.next();
            ArrayList<Character> chars = new ArrayList<>();
            for (char ch: string.toCharArray()) {
//                System.out.println(ch);
                chars.add(ch);
                if(ch=='+' || ch=='-')
                {
                    llist.atEnd(row,column,time,ch);
                }
                row++;
            }
            matrix.add(chars);
            column++;
            row=0;
//            System.out.println(chars);
//            chars.clear();
//            strings.add(string);
        }
//        System.out.println(matrix);

//        System.out.println(matrix.get(0).get(4));
        int row_size=matrix.get(0).size();
        int column_size=matrix.size();
//        System.out.println(row_size);
//        System.out.println(column_size);


//        System.out.println(matrix.get(4).get(0));//second is x(row),first is y(column


//        llist.printList();
        boolean boomFlag=false;
        time++;

        while(llist.head!=null)
        {
            if(llist.headt()==time){
                if(boomFlag) break;
                else time++;						//we covered all the cells of current time,move to next time unit,or break if explosion is about to go
            }
            int x=llist.headx();
            int y=llist.heady();
            char f=llist.headf();
            if(x+1<row_size)        //check east if there is east
            {
                if( matrix.get(y).get(x+1)=='.')
                {
                    matrix.get(y).set(x+1,f);
                    llist.atEnd(x+1,y,time,f);
                }
                if(matrix.get(y).get(x+1)!='X' && f!=matrix.get(y).get(x+1))//if we find not X and not same force,boom initialize
                {
                    matrix.get(y).set(x+1,'*'); boomFlag=true; //set area to * and when time increases we break from loop
                }
            }
            if(x-1>=0)        //check west if there is west
            {
                if( matrix.get(y).get(x-1)=='.')
                {
                    matrix.get(y).set(x-1,f);
                    llist.atEnd(x-1,y,time,f);
                }
                if(matrix.get(y).get(x-1)!='X' && f!=matrix.get(y).get(x-1))//if we find not X and not same force,boom initialize
                {
                    matrix.get(y).set(x-1,'*'); boomFlag=true; //set area to * and when time increases we break from loop
                }
            }

            if(y+1<column_size)
            {
                if( matrix.get(y+1).get(x)=='.')
                {
                    matrix.get(y+1).set(x,f);
                    llist.atEnd(x,y+1,time,f);
                }
                if(matrix.get(y+1).get(x)!='X' && f!=matrix.get(y+1).get(x))//if we find not X and not same force,boom initialize
                {
                    matrix.get(y+1).set(x,'*'); boomFlag=true; //set area to * and when time increases we break from loop
                }
            }

            if(y-1>=0)
            {
                if( matrix.get(y-1).get(x)=='.')
                {
                    matrix.get(y-1).set(x,f);
                    llist.atEnd(x,y-1,time,f);
                }
                if(matrix.get(y-1).get(x)!='X' && f!=matrix.get(y-1).get(x))//if we find not X and not same force,boom initialize
                {
                    matrix.get(y-1).set(x,'*'); boomFlag=true; //set area to * and when time increases we break from loop
                }
            }

            llist.deleteHead();
        }
        if(!boomFlag) {
            System.out.print("the world is saved");
            for (List<Character> chars : matrix) {
                System.out.println("");
                for (char ch : chars) {
                    System.out.print(ch);
                }
            }
        }

        else{
            System.out.print(time);
            int j=0;
            for( List<Character> chars:matrix)
            {
                System.out.println("");
                for(char ch: chars)
                {
                    System.out.print(ch);
                }
//                j++;
//                if(j<=column_size) System.out.println("");




            }
        }

    }


}

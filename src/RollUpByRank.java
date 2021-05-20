import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Phone interview from next door
* Assume these are ranked by score:
* P1,P2,N1,V1,P3,N2,V2,P4,N3,P5  -> P1P2P3,N1,V1,N2,V2,P4P5,N3
* */
/*

* 2021/05/19  phone interview for nextdoor
1.rank based on score
2.loop through the list. mark the cur photo list.
     if ele is photo
       if list.size < 3
            add to cur list
       else
            cur = new list
            add to cur list
     else
       create list and add to it
* */
public class RollUpByRank {
    enum ObjectType {
        NORMAL,
        PHOTO,
        VIDEO
    }
    static class FeedObject {
        int id;
        String subject;
        String body;
        ObjectType type;
        int score;
        public FeedObject(int id, String subject, String body, ObjectType type, int score) {
            this.id = id;
            this.subject = subject;
            this.body = body;
            this.type = type;
            this.score = score;
        }
    }

    private static List<List<FeedObject>> rollUp(List<FeedObject> list){
        list.sort((FeedObject f1, FeedObject f2) -> {
            if(f1.score == f2.score){
                if(f1.id == f2.id) return 0;
                return f1.id > f2.id ? -1 : 1;
            };
            return f1.score > f2.score ? -1 : 1;
        });
        List<List<FeedObject>> res = new ArrayList<>();
        List<FeedObject> curList = new ArrayList<>();
        for(FeedObject fo : list){
            if(fo.type.equals(ObjectType.PHOTO) ){
                if(curList.size() == 0){
                    res.add(curList);
                }
                if(curList.size() < 3){
                    curList.add(fo);
                }else{
                    curList = new ArrayList<>();
                    curList.add(fo);
                    res.add(curList);
                }
            }
            else{
                res.add(Arrays.asList(new FeedObject[]{fo}));
            }
        }
        
        
        return res;
        
    }
    
            
            
    // Photo(id: 1, score: 5), Photo(id: 2, score: 10), Photo(id: 3, score: 15), Photo(id: 4, score: 20)
    // Received output: [ [P1,P2,P3], [P4] ]
    // Expected output: [ [P4,P3,P2], [P1] ]

    public static void main(String[] args) {
        
        List<FeedObject> test = new ArrayList<>();
        FeedObject f1 = new FeedObject(1, "t", "b", ObjectType.PHOTO, 5);
        FeedObject f2 = new FeedObject(2, "t", "b", ObjectType.PHOTO, 6);
        FeedObject f3 = new FeedObject(3, "t", "b", ObjectType.PHOTO, 7);
        FeedObject f4 = new FeedObject(4, "t", "b", ObjectType.NORMAL, 1);

        test.add(f1);
        test.add(f2);
        test.add(f3);
        test.add(f4);


        List<FeedObject> test2 = new ArrayList<>();
        FeedObject f5 = new FeedObject(1, "t", "b", ObjectType.PHOTO, 5);
        FeedObject f6 = new FeedObject(2, "t", "b", ObjectType.PHOTO, 10);
        FeedObject f7 = new FeedObject(3, "t", "b", ObjectType.PHOTO, 15);
        FeedObject f8 = new FeedObject(4, "t", "b", ObjectType.PHOTO, 20);

        test2.add(f5);
        test2.add(f6);
        test2.add(f7);
        test2.add(f8);


        // P1,P2,N1,V1,P3,N2,V2,P4,N3,P5  -> P1P2P3,N1,V1,N2,V2,P4P5,N3

        List<FeedObject> test3 = new ArrayList<>();
        FeedObject f9 = new FeedObject(1, "P1", "b", ObjectType.PHOTO, 30);
        FeedObject f10 = new FeedObject(2, "P2", "b", ObjectType.PHOTO, 29);
        FeedObject f11 = new FeedObject(3, "N1", "b", ObjectType.NORMAL, 28);
        FeedObject f12 = new FeedObject(4, "V1", "b", ObjectType.VIDEO, 27);
        FeedObject f13 = new FeedObject(5, "P3", "b", ObjectType.PHOTO, 26);
        FeedObject f14 = new FeedObject(6, "N2", "b", ObjectType.NORMAL, 25);
        FeedObject f15 = new FeedObject(7, "V2", "b", ObjectType.VIDEO, 14);
        FeedObject f16 = new FeedObject(8, "P4", "b", ObjectType.PHOTO, 13);
        FeedObject f17 = new FeedObject(9, "N3", "b", ObjectType.NORMAL, 12);
        FeedObject f18 = new FeedObject(10, "P5", "b", ObjectType.PHOTO, 11);



        test3.add(f9);
        test3.add(f10);
        test3.add(f11);
        test3.add(f12);
        test3.add(f13);
        test3.add(f14);
        test3.add(f15);
        test3.add(f16);
        test3.add(f17);
        test3.add(f18);


        List<List<FeedObject>> testRes = rollUp(test3);

        
    }

}

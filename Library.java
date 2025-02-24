import java.util.*;

class Mng {
    TreeMap<String, String> al = new TreeMap<>();
    TreeMap<String, String> issue = new TreeMap<>();
    Mng() {
        al.put("Book1", "Author 1");
        al.put("Book2", "Author 2");
        al.put("Book3", "Author 3");
    }
    void addBook(String st, String at) {
        al.put(st, at);
        System.out.println(st + " Book Successfully added");
    }
    void showBook() {
        System.out.println("Book Name\tAuthor Name");
        for (Map.Entry<String, String> e : al.entrySet()) {
            System.out.println(e.getKey() + "\t\t" + e.getValue());
        }
    }
    void issueBook(String abc, String st) {
        if (al.containsKey(abc)) {
            issue.put(abc, st);
            al.remove(abc);
            System.out.println("Book Issued Successfully to " + st);
        } else {
            System.out.println("Book not found");
        }
    }
    void issueTable() {
        System.out.println("Issued Books:");
        System.out.println("Book\tUser");
        for (Map.Entry<String, String> el : issue.entrySet()) {
            System.out.println(el.getKey() + "\t" + el.getValue());
        }
    }
    void res(String abc, String xyz) {
        if (issue.containsKey(abc) && issue.containsValue(xyz)) {
            System.out.println("Enter Author Name :- ");
            Scanner re = new Scanner(System.in);
            String gh = re.nextLine();
            al.put(abc, gh);
            issue.remove(abc);
            System.out.println("Book returned successfully!");}
        else if (xyz.equals("admin")) {
            System.out.println("Enter Author Name :- ");
            Scanner re = new Scanner(System.in);
            String gh = re.nextLine();
            al.put(abc, gh);
            issue.remove(abc);
            System.out.println("Book returned successfully!");

        } else {
            System.out.println("Error: Book return failed. Book was not issued to " + xyz);
        }
    }
}

public class Library {
    public static void main(String[] args) {
        try {
            HashMap<String, String> user = new HashMap<>();
            Set<String> s = user.keySet();
            Mng mng = new Mng();
            boolean x = false;
            
            user.put("Chinmay", "Chinmay@123");
            user.put("Pratik", "Pratik@123");
            user.put("xyz", "xyz@123");
            user.put("1", "123");
            user.put("admin", "12");
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter user ID :- ");
            String ax = sc.nextLine();
            System.out.print("Enter Password :- ");
            String ay = sc.nextLine();
            
         //  for(String str : s){
            //if (ax.equals(str) && ay.equals(user.get(str))) {
             if(user.containsKey(ax) && user.get(ax).equals(ay)){    
                x = true;
                while (x) {
                    
                    System.out.println("1. To Add Book   \t 2. To Show Book \t3. To Issue Book");
                    System.out.println("4. To Return Book \t 5. To Exit \t\t6.Logout ");
                    if(ax.equals ("admin")){
                        System.out.println("7. To Show Issue Table \t 8. To Add User \t9. To Show User");
                    }

                    int sw = sc.nextInt();
                    sc.nextLine();  // Consume the newline character

                    switch (sw) {
                        case 1:
                            System.out.print("Enter name of Book :- ");
                            String c = sc.nextLine();
                            System.out.print("Enter author name :- ");
                            String d = sc.nextLine();
                            mng.addBook(c, d);
                            break;
                        case 2:
                            mng.showBook();
                            break;
                        case 3:
                            System.out.print("Book to issue :- ");
                            c = sc.nextLine();
                            mng.issueBook(c, ax);
                            break;
                        case 4:
                            System.out.print("Book to Return :- ");
                            String cde = sc.nextLine();
                            mng.res(cde, ax);
                            break;
                        case 5:
                            x = false;
                            System.out.println("Please Visit Again!");
                            break;
                        case 6:
                             System.out.print("Enter user ID :- ");
                            ax = sc.nextLine();
                            System.out.print("Enter Password :- ");
                            ay = sc.nextLine();
                           if (!user.containsKey(ax) || !user.get(ax).equals(ay)) {
                            x = false;
                                System.out.println("Invalid user ID or password. Please visit again!");
                            }   
                            break;
                            
                        case 7 :
                            if(ax.equals("admin")){
                            mng.issueTable();
                            }
                            else{System.out.println("Not Functional Key");}
                            break;
                        case 8 :
                            if(ax.equals("admin")){
                            System.out.println("Enter user Id to Add :- ");
                            String xa = sc.nextLine();
                            System.out.println("Enter Password:-");
                            String ya = sc.nextLine();
                            user.put(xa,ya);
                            }
                            else{System.out.println("Not Functional Key");}
                            break;
                        case 9 :
                            if(ax.equals("admin")){
                            for (Map.Entry<String, String> entry : user.entrySet()) {
                                System.out.println(entry.getKey() + " :- " + entry.getValue());
                            }
                            }
                            else{System.out.println("Not Functional Key");}
                            break;
                        default:
                            System.out.println("Not Functional Key");
                            break;
                    }
                }
                }
            else{
                    System.out.println("Sorry, user not found.");
            //   break;
                }     
         //   }
           
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}

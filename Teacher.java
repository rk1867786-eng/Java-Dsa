public class Teacher {
    String designation ="Teacher";
    String CollegeName ="ITM";
    void does(){
        System.out.println("Teaching");
    }
    void display(){
        System.out.println("Designation:"+designation);
        System.out.println("College Name:"+CollegeName);
        
    }
}
class computerTeacher extends Teacher{
    void does1(){
        super.does();
        
    }
}
class xs{
    public static void main(String[] args){
        computerTeacher ob1 =new computerTeacher();
        ob1.does1();
        
    }
}

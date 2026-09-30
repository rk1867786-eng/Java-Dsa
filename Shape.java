abstract class Shape {
    abstract void area();
    
    public void display(){
        System.out.println("This is a shape");
    }
}
class Circle extends Shape {
    @Override
    void area() {
        System.out.println("Area of Circle is πr²");
    }
}
class rectangle extends Shape {
    @Override
    void area() {
        System.out.println("Area of Rectangle is l×w");
    }
}
class main {
    public static void main(String[] args) {
        Circle circle = new Circle();
        rectangle rect = new rectangle();
        
        circle.area();
        circle.display();
        
        rect.area();
        rect.display();
    }
}
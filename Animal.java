abstract  class Animal {
    abstract void eat();
    
    public void sleep(){
        System.out.println("Animal is sleeping");
    }
}
class Dog extends Animal {
    @Override
    void eat() {
        System.out.println("Dog is eating");
    }
}
class main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();
        dog.sleep();
    }
}

import java.util.Random;
public class Main {
    public static void main(String[] args) {
        DoughnutFactory factory = new DoughnutFactory();

        Doughnut cherry = factory.getDoughnut(DoughnutTypes.CHERRY);
        Doughnut chocolate = factory.getDoughnut(DoughnutTypes.CHOCOLATE);
        Doughnut almond = factory.getDoughnut(DoughnutTypes.ALMOND);

        cherry.eat();
        chocolate.eat();
        almond.eat();
        DoughnutFactory factory1=new DoughnutFactory();
        eatRandomDoughnut(factory1);
    }

    public static void eatRandomDoughnut(DoughnutFactory factory){
        Doughnut randomDougnut = getRandomDougnut(factory);
        System.out.printf("What a surprise! ");
        randomDougnut.eat();
    }

    public static Doughnut getRandomDougnut(DoughnutFactory factory){
        Random random = new Random();
        DoughnutTypes type = DoughnutTypes.values()[random.nextInt(DoughnutTypes.values().length)];

        return(factory.getDoughnut(type));
    }
}

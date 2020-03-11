package pro.it.sis.javacourse;

public class BlazingAsphalt extends Weapon {
    void hit(Target target) {
        target.physicalDamage+=100;
        target.fireDamage+=50;
    }
}

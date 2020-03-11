package pro.it.sis.javacourse;

public class KnifeNight extends Weapon{
    void hit(Target target) {
        target.physicalDamage+=100;
        target.iceDamage+=50;
    }
}

package pro.it.sis.javacourse;

import org.junit.Test;

import java.nio.file.Watchable;

import static org.junit.Assert.*;

public class WeaponTest {

    @Test
    public void testPhysicalDamage() {

        Target t = new Target();
        Weapon w = new Weapon();
        w.hit(t);


        assertEquals(100, t.getPhysicalDamage());
    }

    @Test
    public void testFireDamage() {

        Target t = new Target();
        BlazingAsphalt b = new BlazingAsphalt();
        b.hit(t);

        assertEquals(50, t.getFireDamage());
    }

    @Test
    public void testIceDamage() {

        Target t = new Target();
        KnifeNight k = new KnifeNight();
        k.hit(t);

        assertEquals(50, t.getIceDamage());
    }
    @Test
    public void testEphritis(){
        Ephritis e=new Ephritis();
        KnifeNight k=new KnifeNight();
        k.hit(e);
        assertEquals(50,e.getIceDamage());
        assertEquals(100,e.getPhysicalDamage());
        BlazingAsphalt b=new BlazingAsphalt();
        b.hit(e);
        assertEquals(200,e.getPhysicalDamage());
        assertEquals(0,e.getFireDamage());
    }
    @Test
    public void testIceGigant(){
        IceGigant g=new IceGigant();
        KnifeNight k=new KnifeNight();
        k.hit(g);
        assertEquals(0,g.getIceDamage());
        assertEquals(100,g.getPhysicalDamage());
        BlazingAsphalt b=new BlazingAsphalt();
        b.hit(g);
        Weapon w = new Weapon();
        w.hit(g);
        assertEquals(300,g.getPhysicalDamage());
        assertEquals(50,g.getFireDamage());
    }
}
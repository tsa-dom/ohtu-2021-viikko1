package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varastoSaldolla;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varastoSaldolla = new Varasto(10, 4);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVarastonOikeallaSaldolla() { assertEquals(4, varastoSaldolla.getSaldo(), vertailuTarkkuus); }

    @Test
    public void varastoNegatiivisellaTilavuudella() {
        Varasto negatiivinenVarasto = new Varasto(-5);
        assertEquals(0, negatiivinenVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastoSaldollaNegatiivisellaTilavuudella() {
        Varasto negatiivinenVarasto = new Varasto(-5, 4);
        assertEquals(0, negatiivinenVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastoNeagtiivisellaSaldollaOnNolla() {
        Varasto varasto = new Varasto(10, -5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaSaldoVarastollaOikeaTilavuus() { assertEquals(10, varastoSaldolla.getTilavuus(), vertailuTarkkuus); }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastoonEiVoiLisätäNegatiivistaSaldoa() {
        varasto.lisaaVarastoon(-5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoonMahtuuMaksimissaanSenKapasiteetti() {
        varastoSaldolla.lisaaVarastoon(9);
        assertEquals(10, varastoSaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttoVarastostaEiVähennäVarastoa() {
        varastoSaldolla.otaVarastosta(-3);
        assertEquals(4, varastoSaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaOtetaanSenVerranKuinVoidaan() { assertEquals(4, varastoSaldolla.otaVarastosta(6), vertailuTarkkuus);}

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void stringToStringMerkkijonoEsitysOikea() { assertEquals("saldo = 4.0, vielä tilaa 6.0", varastoSaldolla.toString());}

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bank.bankieren.Bank;
import bank.bankieren.IBank;
import java.lang.RuntimeException;
import bank.bankieren.IRekening;
import bank.bankieren.Money;
import fontys.util.NumberDoesntExistException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Frank
 */
public class TestKlasse {
    
    public TestKlasse() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testOpenRekening(){
        Bank bank = new Bank("Franks Feest Bank");  
        assertEquals(100000000,bank.openRekening("Rekening1", "Tilburg"));
        assertEquals(-1, bank.openRekening("",""));
    }
    
    @Test
    public void testGetName(){
        Bank bank = new Bank("Franks Feest Bank");
        assertEquals("Franks Feest Bank", bank.getName());
    }
    
    @Test
    public void testGetRekening() {
        Bank bank = new Bank("Franks Feest Bank");
        bank.openRekening("Rekening2", "Tilburg");
        assertEquals(null, bank.getRekening(0));
        IRekening r = bank.getRekening(100000000);
        assertEquals(r.getNr(), 100000000);
    }

    @Test
    (expected=RuntimeException.class)
    public void testMaakOverWrongDestination() throws NumberDoesntExistException {
        Bank bank = new Bank("Franks Feest Bank");
        Money money = new Money(100, "Euro");
        bank.maakOver(0, 0, money);
    }
    
    @Test
    (expected=RuntimeException.class)
    public void testMaakOverWrongMoney() throws NumberDoesntExistException {
        Bank bank = new Bank("Franks Feest Bank");
        Money money = new Money(-10, "Euro");
        bank.maakOver(0, 1, money);
    }
    
    @Test
    (expected=NumberDoesntExistException.class)
    public void tesMaakOverFoutNummer() throws NumberDoesntExistException{
        Bank bank = new Bank("Franks Feest Bank");
        bank.openRekening("Frankie", "Tilburg");
        bank.openRekening("Stijn", "Tilburg");
        Money money = new Money(10, "Euro");
        bank.maakOver(7, 100000000, money);
    }
    
    @Test
    public void testMaakOver() throws NumberDoesntExistException{
        Bank bank = new Bank("Franks Feest Bank");
        bank.openRekening("Frankie", "Tilburg");
        bank.openRekening("Stijn", "Tilburg");
        Money money = new Money(100, Money.EURO);
        assertEquals(true, bank.maakOver(100000000, 100000001, money));
    }
    
    @Test
    (expected=NumberDoesntExistException.class)
    public void testMaakOverFoutNummerDest() throws NumberDoesntExistException{
                Bank bank = new Bank("Franks Feest Bank");
        bank.openRekening("Frankie", "Tilburg");
        bank.openRekening("Stijn", "Tilburg");
        Money money = new Money(10, Money.EURO);
        bank.maakOver(100000000, 7, money);
    }
    
    @Test
        public void testMaakOverFalse() throws NumberDoesntExistException{
        Bank bank = new Bank("Franks Feest Bank");
        bank.openRekening("Frankie", "Tilburg");
        bank.openRekening("Stijn", "Tilburg");
        Money money = new Money(1000000000, Money.EURO);
        assertEquals(false, bank.maakOver(100000000, 100000001, money));
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

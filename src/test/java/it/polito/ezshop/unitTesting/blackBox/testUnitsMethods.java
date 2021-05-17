package it.polito.ezshop.unitTesting.blackBox;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import it.polito.ezshop.model.*;


public class testUnitsMethods {

    @Test
    public void testBalanceOperationClass(){
        BalanceOperationClass b = new BalanceOperationClass(1, LocalDate.now(), 1, "CREDIT");

        assertEquals(1, b.getBalanceId());
        assertEquals(LocalDate.now(), b.getDate());
        assertEquals(1, b.getMoney(), 0.01);
        assertEquals("CREDIT", b.getType());

        b.setBalanceId(2);
        b.setDate(LocalDate.now().plusDays(1));
        b.setMoney(2.51);
        b.setType("DEBIT");

        assertEquals(2, b.getBalanceId());
        assertEquals(LocalDate.now().plusDays(1), b.getDate());
        assertEquals(2.51, b.getMoney(), 0.001);
        assertEquals("DEBIT", b.getType());
    }

    @Test
    public void testCreditCardClass(){
        CreditCardClass c = new CreditCardClass("4485370086510891", 150.20);

        assertEquals("4485370086510891", c.getCreditCardId());
        assertEquals(150.20, c.getBalance(), 0.001);

        c.setCreditCardId("5100293991053009");
        c.setBalance(120.20);

        assertEquals("5100293991053009", c.getCreditCardId());
        assertEquals(120.20, c.getBalance(), 0.001);
    }

    @Test
     public void testCustomerClass(){
        Customer c = new Customer(1, "customer1", "001", 0);

        assertEquals(1, (int) c.getId());
        assertEquals("customer1", c.getCustomerName());
        assertEquals("001", c.getCustomerCard());
        assertEquals(0, (int) c.getPoints());

        c.setId(2);
        c.setCustomerName("customer2");
        c.setCustomerCard("005");
        c.setPoints(12);

        assertEquals(2, (int) c.getId());
        assertEquals("customer2", c.getCustomerName());
        assertEquals("005", c.getCustomerCard());
        assertEquals(12, (int) c.getPoints());
    }

    @Test
    public void testOrderClass(){
        OrderClass o = new OrderClass(1, 1, "0628176957012", 1.5, 10, "ISSUED");

        assertEquals(1, (int) o.getOrderId());
        assertEquals(1, (int) o.getBalanceId());
        assertEquals("0628176957012", o.getProductCode());
        assertEquals(1.5, o.getPricePerUnit(), 0.001);
        assertEquals(10, o.getQuantity());
        assertEquals("ISSUED", o.getStatus());

        o.setOrderId(2);
        o.setBalanceId(2);
        o.setProductCode("628176957029");
        o.setPricePerUnit(2.3);
        o.setQuantity(12);
        o.setStatus("PAYED");

        assertEquals(2, (int) o.getOrderId());
        assertEquals(2, (int) o.getBalanceId());
        assertEquals("628176957029", o.getProductCode());
        assertEquals(2.3, o.getPricePerUnit(), 0.001);
        assertEquals(12, o.getQuantity());
        assertEquals("PAYED", o.getStatus());
    }

    @Test
    public void testProductTypeClass(){
        ProductTypeClass p = new ProductTypeClass(1, "apple", "628176957029", 1.1, "red apples");
        ProductTypeClass p2 = new ProductTypeClass(1, 10, "1-f-1", "apple", "628176957029", 1.1, "red apples");

        assertEquals(1, (int) p.getId());
        assertEquals("apple", p.getProductDescription());
        assertEquals("628176957029", p.getBarCode());
        assertEquals(1.1, p.getPricePerUnit(), 0.001);
        assertEquals("red apples", p.getNote());
        assertEquals(10, (int) p2.getQuantity());
        assertEquals("1-f-1", p2.getLocation());

        p.setId(3);
        p.setProductDescription("kiwi");
        p.setBarCode("0628176957012");
        p.setPricePerUnit(0.9);
        p.setNote("kiwidescription");
        p.setLocation("1-f-1");
        p.setQuantity(12);

        assertEquals(3, (int) p.getId());
        assertEquals("kiwi", p.getProductDescription());
        assertEquals("0628176957012", p.getBarCode());
        assertEquals(0.9, p.getPricePerUnit(), 0.001);
        assertEquals("kiwidescription", p.getNote());
        assertEquals("1-f-1", p.getLocation());
        assertEquals(12, (int) p.getQuantity());
    }

    @Test
    public void testUserClass(){
        UserClass u = new UserClass(1, "username", "pwd", "Administrator");

        assertEquals(1, (int) u.getId());
        assertEquals("username", u.getUsername());
        assertEquals("pwd", u.getPassword());
        assertEquals("Administrator", u.getRole());

        u.setId(2);
        u.setUsername("username2");
        u.setPassword("pwd2");
        u.setRole("Cashier");

        assertEquals(2, (int) u.getId());
        assertEquals("username2", u.getUsername());
        assertEquals("pwd2", u.getPassword());
        assertEquals("Cashier", u.getRole());
    }

    @Test
    public void testTicketEntryClass(){
        TicketEntryClass t = new TicketEntryClass(1, "628176957029", "apple", 2, 1.1, 0D);

        assertEquals(1, (int) t.getTransactionId());
        assertEquals("628176957029", t.getBarCode());
        assertEquals("apple", t.getProductDescription());
        assertEquals(2, t.getAmount());
        assertEquals(1.1, t.getPricePerUnit(), 0.001);
        assertEquals(0D, t.getDiscountRate(), 0.001);

        t.setTransactionId(2);
        t.setBarCode("0628176957012");
        t.setProductDescription("kiwi");
        t.setPricePerUnit(0.9);
        t.setAmount(4);
        t.setDiscountRate(0.5);

        assertEquals(2, (int) t.getTransactionId());
        assertEquals("0628176957012", t.getBarCode());
        assertEquals("kiwi", t.getProductDescription());
        assertEquals(4, t.getAmount());
        assertEquals(0.9, t.getPricePerUnit(), 0.001);
        assertEquals(0.5, t.getDiscountRate(), 0.001);
    }

    @Test
    public void testSaleTransactionClass(){

    }

    @Test
    public void testReturnTransactionClass(){
        
    }
}

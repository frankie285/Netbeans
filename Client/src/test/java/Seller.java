
import auction.webservice.Bid;
import auction.webservice.Category;
import auction.webservice.Item;
import auction.webservice.Money;
import auction.webservice.User;
import static org.junit.Assert.*;

import org.junit.After;

import org.junit.Before;
import org.junit.Test;




public class Seller {


    @Before
    public void setUp()
    {
        clean();
    }

    /**
     * Test of offerItem method, of class SellerMgr.
     */
    @Test
    public void testOfferItem() {
        String omsch = "omsch";

        User user1 = registerUser("xx@nl");
        Category cat = newCategory("cat1");
        Item item1 = offerItem(user1, cat, omsch);
        assertEquals(omsch, item1.getDescription());
        assertNotNull(item1.getId());
    }

    /**
     * Test of revokeItem method, of class SellerMgr.
     */
    
   @Test
   public void testRevokeItem() {
        String omsch = "omsch";
        String omsch2 = "omsch2";
        
    
        User seller = registerUser("sel@nl");
        User seller2 = registerUser("sel2@nl");
        User buyer = registerUser("buy@nl");
        Category cat = new Category();
        cat.setDescription("cat1");
        
            // revoke before bidding
        Item item1 = offerItem(seller, cat, omsch);
        boolean res = revokeItem(item1);
        assertTrue(res);
        int count = findItemByDescription(omsch).size();
        assertEquals(0, count);
        
            // revoke after bid has been made
        Item item2 = offerItem(seller2, cat, omsch2);
        Money money = new Money();
        money.setCents(100);
        money.setCurrency("Euro");
        newBid(item2, buyer, money);
        item2 = getItem(item2.getId());
        boolean res2 = revokeItem(item2);
        assertFalse(res2);
        int count2 = findItemByDescription(omsch2).size();
        assertEquals(1, count2);      
    }



    private static void clean() {
        auction.webservice.RegistrationService service = new auction.webservice.RegistrationService();
        auction.webservice.Registration port = service.getRegistrationPort();
        port.clean();
    }
    
    private static Item getItem(java.lang.Long arg0) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.getItem(arg0);
    }

    private static java.util.List<auction.webservice.Item> findItemByDescription(java.lang.String arg0) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.findItemByDescription(arg0);
    }

    private static boolean revokeItem(auction.webservice.Item arg0) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.revokeItem(arg0);
    }

    private static Bid newBid(auction.webservice.Item arg0, auction.webservice.User arg1, auction.webservice.Money arg2) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.newBid(arg0, arg1, arg2);
    }

    private static Item offerItem(auction.webservice.User arg0, auction.webservice.Category arg1, java.lang.String arg2) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.offerItem(arg0, arg1, arg2);
    }

    private static Category newCategory(java.lang.String arg0) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.newCategory(arg0);
    }

    private static User registerUser(java.lang.String arg0) {
        auction.webservice.RegistrationService service = new auction.webservice.RegistrationService();
        auction.webservice.Registration port = service.getRegistrationPort();
        return port.registerUser(arg0);
    }
    
}

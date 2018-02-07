import auction.webservice.Bid;
import auction.webservice.Category;
import auction.webservice.Item;
import auction.webservice.Money;
import auction.webservice.User;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yanni
 */
public class Auction {

    public Auction() {
    }

    @Before
    public void setUp() throws Exception {
        clean();
    }

    @Test
    public void getItem() {

        String email = "xx2@nl";
        String omsch = "omsch";

        User seller1 = registerUser(email);
        Category cat = new Category();
        cat.setDescription("cat1");
        Item item1 = offerItem(seller1, cat, omsch);
        Item item2 = getItem(item1.getId());
        assertEquals(omsch, item2.getDescription());
        assertEquals(email, item2.getSeller().getEmail());
    }

    @Test
    public void findItemByDescription() {
        String email3 = "xx3@nl";
        String omsch = "omsch";
        String email4 = "xx4@nl";
        String omsch2 = "omsch2";

        User seller3 = registerUser(email3);
        User seller4 = registerUser(email4);
        Category cat = new Category();
        cat.setDescription("cat3");
        Item item1 = offerItem(seller3, cat, omsch);
        Item item2 = offerItem(seller4, cat, omsch);

        ArrayList<Item> res = (ArrayList<Item>) findItemByDescription(omsch2);
        assertEquals(0, res.size());

        res = (ArrayList<Item>) findItemByDescription(omsch);
        assertEquals(2, res.size());

    }

    @Test
    public void newBid() {

        String email = "ss2@nl";
        String emailb = "bb@nl";
        String emailb2 = "bb2@nl";
        String omsch = "omsch_bb";

        User seller = registerUser(email);
        User buyer = registerUser(emailb);
        User buyer2 = registerUser(emailb2);
        // eerste bod
        Category cat = new Category();
        cat.setDescription("cat9");
        Item item1 = offerItem(seller, cat, omsch);
        Money money = new Money();
        money.setCents(10);
        money.setCurrency("eur");
        Bid new1 = newBid(item1, buyer, money);
        assertEquals(emailb, new1.getBuyer().getEmail());

        // lager bod
        money.setCents(9);
        item1 = getItem(item1.getId());
        Bid new2 = newBid(item1, buyer2, money);
        assertNull(new2);

        // hoger bod
        money.setCents(11);
        item1 = getItem(item1.getId());
        Bid new3 = newBid(item1, buyer2, money);
        assertEquals(emailb2, new3.getBuyer().getEmail());
    }


    private static Item getItem(java.lang.Long arg0) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.getItem(arg0);
    }

    private static void clean() {
        auction.webservice.RegistrationService service = new auction.webservice.RegistrationService();
        auction.webservice.Registration port = service.getRegistrationPort();
        port.clean();
    }

    private static Item offerItem(auction.webservice.User arg0, auction.webservice.Category arg1, java.lang.String arg2) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.offerItem(arg0, arg1, arg2);
    }

    private static User registerUser(java.lang.String arg0) {
        auction.webservice.RegistrationService service = new auction.webservice.RegistrationService();
        auction.webservice.Registration port = service.getRegistrationPort();
        return port.registerUser(arg0);
    }

    private static Money newMoney(long arg0, java.lang.String arg1) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.newMoney(arg0, arg1);
    }

    private static Category newCategory(java.lang.String arg0) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.newCategory(arg0);
    }

    private static Bid newBid(auction.webservice.Item arg0, auction.webservice.User arg1, auction.webservice.Money arg2) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.newBid(arg0, arg1, arg2);
    }

    private static java.util.List<auction.webservice.Item> findItemByDescription(java.lang.String arg0) {
        auction.webservice.AuctionService service = new auction.webservice.AuctionService();
        auction.webservice.Auction port = service.getAuctionPort();
        return port.findItemByDescription(arg0);
    }

}
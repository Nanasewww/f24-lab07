package AndrewWebServices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AndrewWebServicesTest {
    Database mockDatabase;
    RecSys mockRecommender;
    PromoService mockPromoService;
    AndrewWebServices andrewWebService;

    @Before
    public void setUp() {
        // You need to use some mock objects here
        // database = new Database(); // We probably don't want to access our real database...
        // recommender = new RecSys();
        // promoService = new PromoService();

        // mock database
        mockDatabase = mock(Database.class);
        when(mockDatabase.getPassword("Scotty")).thenReturn(17214);

        // mock recommender
        mockRecommender = mock(RecSys.class);
        when(mockRecommender.getRecommendation("Scotty")).thenReturn("Animal House");

        // mock promoService
        mockPromoService = mock(PromoService.class);

        andrewWebService = new AndrewWebServices(mockDatabase, mockRecommender, mockPromoService);
    }

    @Test
    public void testLogIn() {
        // This is taking way too long to test
        assertTrue(andrewWebService.logIn("Scotty", 17214));
    }

    @Test
    public void testGetRecommendation() {
        // This is taking way too long to test
        assertEquals("Animal House", andrewWebService.getRecommendation("Scotty"));
    }

    @Test
    public void testSendEmail() {
        // How should we test sendEmail() when it doesn't have a return value?
        // Hint: is there something from Mockito that seems useful here?
        andrewWebService.sendPromoEmail("Scotty");
        verify(mockPromoService).mailTo("Scotty");
    }

    @Test
    public void testNoSendEmail() {
        // How should we test that no email has been sent in certain situations (like right after logging in)?
        // Hint: is there something from Mockito that seems useful here?
        andrewWebService.sendPromoEmail(null);
        verify(mockPromoService, times(0)).mailTo(anyString());
    }
}

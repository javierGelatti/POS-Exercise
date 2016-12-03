package com.javiergelatti.pos.test;

import com.javiergelatti.pos.ItemCatalog;
import com.javiergelatti.pos.PointOfSale;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScanProductTest {

    private final ItemCatalog catalog = new ItemCatalog();
    private final PointOfSale pointOfSale = new PointOfSale(catalog);

    @Test
    public void itemNotFound() throws Exception {
        pointOfSale.onBarcode("Not found");

        String shownText = pointOfSale.lastTextShown();
        assertEquals("Item not found", shownText);
    }

    @Test
    public void itemFound() throws Exception {
        catalog.addItem("123", 11.50);

        pointOfSale.onBarcode("123");

        String shownText = pointOfSale.lastTextShown();
        assertEquals("$ 11.50", shownText);
    }

    @Test
    public void manyItems() throws Exception {
        catalog.addItem("123", 11.50);
        catalog.addItem("124", 25.30);

        pointOfSale.onBarcode("123");

        String shownText = pointOfSale.lastTextShown();
        assertEquals("$ 11.50", shownText);
    }
}

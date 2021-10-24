package io.lslaoang.moviecatalogservice.model;

import java.util.List;

public class CatalogItemList {

    /* Added CatalogItemList to handle object type rather than List*/
    private List<CatalogItem> catalogItemList;

    public CatalogItemList(){
        //empty constructor
    }

    public CatalogItemList(List<CatalogItem> catalogItemList) {
        this.catalogItemList = catalogItemList;
    }

    public List<CatalogItem> getCatalogItemList() {
        return catalogItemList;
    }

    public void setCatalogItemList(List<CatalogItem> catalogItemList) {
        this.catalogItemList = catalogItemList;
    }
}

package io.lslaoang.moviecatalogservice.model;

import java.util.List;

public class Catalog {

    private List<CatalogItem> catalogItemList;

    public Catalog(){
        //empty constructor
    }

    public Catalog(List<CatalogItem> catalogItemList) {
        this.catalogItemList = catalogItemList;
    }

    public List<CatalogItem> getCatalogItemList() {
        return catalogItemList;
    }

    public void setCatalogItemList(List<CatalogItem> catalogItemList) {
        this.catalogItemList = catalogItemList;
    }
}

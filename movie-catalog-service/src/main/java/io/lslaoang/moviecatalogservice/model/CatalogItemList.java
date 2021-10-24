package io.lslaoang.moviecatalogservice.model;

import java.util.List;

public class CatalogItemList {

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

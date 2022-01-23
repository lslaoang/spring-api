package io.lslaoang.moviecatalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogItemList {

    /* Added CatalogItemList to handle object type rather than List*/
    private List<CatalogItem> catalogItemList;
}

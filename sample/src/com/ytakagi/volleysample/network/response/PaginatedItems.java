
package com.ytakagi.volleysample.network.response;

import lombok.Data;

import com.ytakagi.volleysample.model.Item;

@Data
public class PaginatedItems {
    private int total;
    private int page;
    private int limit;
    private Item[] items;
}

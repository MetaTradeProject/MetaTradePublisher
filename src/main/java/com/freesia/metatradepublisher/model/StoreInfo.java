package com.freesia.metatradepublisher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record StoreInfo (String address, String id, String description, @JsonIgnore String pubkey, @JsonIgnore String prikey){
    
}

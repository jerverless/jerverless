/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jerverless.boot.config;

/**
 *
 * @author shalithasuranga
 */
public class TypeConfig extends IConfigProperty {
    private String contentType;

    public TypeConfig(String type) {
        contentType = type;
    }
    
    public String getContentType() {
        return contentType;
    }
}

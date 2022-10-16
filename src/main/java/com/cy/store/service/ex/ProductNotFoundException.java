package com.cy.store.service.ex;

/**
 * @Auther: su_gh
 * @Date: 2022 - 10 - 15 - 17:59
 * @Description: 商品不存在
 * @Vsersion: 1.0
 */
public class ProductNotFoundException extends ServiceException{

    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(Throwable cause) {
        super(cause);
    }

    public ProductNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

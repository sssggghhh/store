package com.cy.store.service.ex;

/**
 * @Auther: su_gh
 * @Date: 2022 - 08 - 27 - 17:23
 * @Description: com.cy.store.service.ex
 * @Vsersion: 1.0
 */
public class AddressNotFoundException extends ServiceException{
    public AddressNotFoundException() {
        super();
    }

    public AddressNotFoundException(String message) {
        super(message);
    }

    public AddressNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AddressNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

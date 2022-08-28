package com.cy.store.service.ex;

/**
 * @Auther: su_gh
 * @Date: 2022 - 08 - 27 - 17:25
 * @Description: com.cy.store.service.ex
 * @Vsersion: 1.0
 */
public class AddressDeniedException extends ServiceException{
    public AddressDeniedException() {
        super();
    }

    public AddressDeniedException(String message) {
        super(message);
    }

    public AddressDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressDeniedException(Throwable cause) {
        super(cause);
    }

    protected AddressDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

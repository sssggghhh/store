package com.cy.store.service.ex;

/**
 * @Auther: su_gh
 * @Date: 2022 - 08 - 28 - 18:34
 * @Description: com.cy.store.service.ex
 * @Vsersion: 1.0
 * 删除数据时发生异常
 */
public class DeleteException extends ServiceException{
    public DeleteException() {
        super();
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

    protected DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package org.yujoo.baas.util;

import java.io.Serializable;

public class HttpException extends Exception implements Serializable {
    private static final long serialVersionUID = -6540474598464389528L;

    public HttpException(String msg) {
        super(msg);
    }
}

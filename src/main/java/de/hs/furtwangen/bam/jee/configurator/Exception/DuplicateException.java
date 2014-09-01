package de.hs.furtwangen.bam.jee.configurator.Exception;

public class DuplicateException extends Exception {
    private static final long serialVersionUID = 6532938055712085137L;

    public DuplicateException() {
    }

    public DuplicateException(String arg0) {
        super(arg0);
    }

    public DuplicateException(Throwable arg0) {
        super(arg0);
    }

    public DuplicateException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public DuplicateException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}

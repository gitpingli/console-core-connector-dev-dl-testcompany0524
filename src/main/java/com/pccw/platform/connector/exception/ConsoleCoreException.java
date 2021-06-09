/** */
package com.pccw.platform.connector.exception;

/** @author dxiong */
public class ConsoleCoreException extends RuntimeException {
  /** */
  private static final long serialVersionUID = 2322270398663247466L;

  private final int code;

  public ConsoleCoreException(int code) {
    this.code = code;
  }

  public ConsoleCoreException(int code, String message) {
    super(message);
    this.code = code;
  }

  public ConsoleCoreException(int code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

  public ConsoleCoreException(int code, Throwable cause) {
    super(cause);
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}

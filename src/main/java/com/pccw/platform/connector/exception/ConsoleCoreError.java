/** */
package com.pccw.platform.connector.exception;

import lombok.Builder;
import lombok.Data;

/** @author dxiong */
@Data
@Builder
public class ConsoleCoreError {
  private int code;
  private String error;
}

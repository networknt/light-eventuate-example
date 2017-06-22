package com.networknt.eventuate.queryservice.account;

import java.math.BigDecimal;

public class MoneyUtil {

  public static long toIntegerRepr(BigDecimal d) {
    return d.multiply(new BigDecimal(100)).longValueExact();
  }

}

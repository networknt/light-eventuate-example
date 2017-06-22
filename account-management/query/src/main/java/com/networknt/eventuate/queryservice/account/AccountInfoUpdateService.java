package com.networknt.eventuate.queryservice.account;


import com.networknt.eventuate.account.common.model.account.AccountChangeInfo;
import com.networknt.eventuate.account.common.model.account.AccountTransactionInfo;
import com.networknt.eventuate.account.common.model.transaction.TransferState;
import com.networknt.eventuate.common.Int128;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;


public class AccountInfoUpdateService {
  private Logger logger = LoggerFactory.getLogger(getClass());

  private AccountInfoRepository accountInfoRepository;


  public AccountInfoUpdateService(AccountInfoRepository accountInfoRepository) {
    this.accountInfoRepository = accountInfoRepository;
  }


  public void create(String accountId, String customerId, String title, BigDecimal initialBalance, String description, Int128 version) {
    try {
      AccountChangeInfo ci = new AccountChangeInfo();
   /*   ci.setAmount(toIntegerRepr(initialBalance));
      WriteResult x = mongoTemplate.upsert(new Query(where("id").is(accountId).and("version").exists(false)),
              new Update()
                      .set("customerId", customerId)
                      .set("title", title)
                      .set("description", description)
                      .set("balance", toIntegerRepr(initialBalance))
                      .push("changes", ci)
                      .set("creationDate", new Date(version.getHi()))
                      .set("version", version.asString()),
              AccountInfo.class);
      logger.info("Saved in mongo");
*/
    } catch (Exception t) {
      logger.warn("When saving ", t);
    } catch (Throwable t) {
      logger.error("Error during saving: ", t);
      throw new RuntimeException(t);
    }
  }

  public void delete(String accountId) {
    accountInfoRepository.delete(accountId);
  }


  public void addTransaction(String accountId, AccountTransactionInfo ti) {
 /*   mongoTemplate.upsert(new Query(where("id").is(accountId)),
            new Update().
                    set("transactions." + ti.getTransactionId(), ti),
            AccountInfo.class);*/
  }


  public void updateBalance(String accountId, String changeId, long balanceDelta, AccountChangeInfo ci) {
  /*  WriteResult x = mongoTemplate.updateMulti(new Query(where("id").is(accountId).and("version").lt(changeId)),
            new Update().
                    inc("balance", balanceDelta).
                    push("changes", ci).
                    set("version", changeId),
            AccountInfo.class);*/
  }

  public void updateTransactionStatus(String accountId, String transactionId, TransferState status) {
 /*     mongoTemplate.upsert(new Query(where("id").is(accountId)),
              new Update().
                      set("transactions." + transactionId + ".status", status),
              AccountInfo.class);*/
  }
}

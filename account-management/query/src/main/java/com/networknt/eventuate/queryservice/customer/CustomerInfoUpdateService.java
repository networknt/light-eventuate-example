package com.networknt.eventuate.queryservice.customer;


import com.networknt.eventuate.account.common.model.customer.CustomerInfo;
import com.networknt.eventuate.account.common.model.customer.ToAccountInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;




public class CustomerInfoUpdateService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  private CustomerViewRepository querySideCustomerRepository;

  public CustomerInfoUpdateService(CustomerViewRepository querySideCustomerRepository) {
    this.querySideCustomerRepository = querySideCustomerRepository;

  }

  public void create(String id, CustomerInfo customerInfo) {
    try {
      querySideCustomerRepository.save(new QuerySideCustomer(id,
                      customerInfo.getName(),
                      customerInfo.getUserCredentials().getEmail(),
                      customerInfo.getUserCredentials().getPassword(),
                      customerInfo.getSsn(),
                      customerInfo.getPhoneNumber(),
                      customerInfo.getAddress(),
                      Collections.<String, ToAccountInfo>emptyMap()
              )
      );
      logger.info("Saved in mongo");
    } catch (Exception t) {
      logger.warn("When saving ", t);
    }
  }

  public void addToAccount(String id, ToAccountInfo accountInfo) {
  /*  mongoTemplate.upsert(new Query(where("id").is(id)),
            new Update().
                    set("toAccounts." + accountInfo.getId(), accountInfo),
            QuerySideCustomer.class);*/
  }

  public void deleteToAccountFromAllCustomers(String accountId) {
  /*  mongoTemplate.updateMulti(new Query(where("toAccounts." + accountId).exists(true)),
            new Update().
                    unset("toAccounts." + accountId),
            QuerySideCustomer.class);*/
  }

  public void deleteToAccount(String customerId, String accountId) {
  /*  mongoTemplate.upsert(new Query(where("id").is(customerId)),
            new Update().
                    unset("toAccounts." + accountId),
            QuerySideCustomer.class);*/
  }
}

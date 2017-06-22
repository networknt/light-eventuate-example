package com.networknt.eventuate.queryservice.account;


import java.util.List;

interface AccountInfoRepository {

    List<AccountInfo> findByCustomerId(String customerId);

    AccountInfo findOneAccount(String accountId);

    void delete(String accountId);
}

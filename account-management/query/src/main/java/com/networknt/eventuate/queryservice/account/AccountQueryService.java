package com.networknt.eventuate.queryservice.account;

import java.util.List;

public class AccountQueryService {

  private AccountInfoRepository accountInfoRepository;

  public AccountQueryService(AccountInfoRepository accountInfoRepository) {
    this.accountInfoRepository = accountInfoRepository;
  }

  public AccountInfo findByAccountId(String accountId) {
    AccountInfo account = accountInfoRepository.findOneAccount(accountId);
    if (account == null)
      throw new AccountNotFoundException(accountId);
    else
      return account;
  }

  public List<AccountInfo> findByCustomerId(String customerId) {
      return accountInfoRepository.findByCustomerId(customerId);
  }
}

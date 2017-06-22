package com.networknt.eventuate.queryservice.customer;



import java.util.List;

interface CustomerViewRepository  {

  List<QuerySideCustomer> findByEmailLike(String email);

  QuerySideCustomer findOneCustomer(String customerId);

  void save(QuerySideCustomer customer);

}

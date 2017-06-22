package com.networknt.eventuate.queryservice.account;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AccountInfoRepositoryImpl implements  AccountInfoRepository{
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private DataSource dataSource;

    public AccountInfoRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {this.dataSource = dataSource;}

    public List<AccountInfo> findByCustomerId(String customerId) {
        Objects.requireNonNull(customerId);
        List<AccountInfo> accounts = new ArrayList<AccountInfo>();

        String psSelect = "SELECT a.account_id, c.customer_id,  title, version, description, balance, creation_Date " +
                "FROM account_info a join account_customer c ON a.account_id =c.account_id WHERE ACTIVE_FLG = 'Y' AND c.customer_id = ?";

        try (final Connection connection = dataSource.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(psSelect);
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs == null ) {
                logger.error("incorrect fetch result {}", customerId);
            }
            while (rs.next()) {
                AccountInfo accountInfo = new AccountInfo();
                accountInfo.setId(rs.getString("account_id"));
                accountInfo.setCustomerId(rs.getString("customer_id"));
                accountInfo.setTitle(rs.getString("title"));
                accountInfo.setDescription(rs.getString("description"));
                accountInfo.setVersion(rs.getString("version"));
                accountInfo.setBalance(MoneyUtil.toIntegerRepr(rs.getBigDecimal("version")));
                accountInfo.setCreationDate(rs.getDate("creation_Date"));

                accounts.add(accountInfo);
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }


        return accounts;
    }

    public AccountInfo findOneAccount(String accountId){
        Objects.requireNonNull(accountId);
        String psSelect = "SELECT a.account_id, c.customer_id,  title, version, description, balance, creation_Date " +
                        "FROM account_info a join account_customer c ON a.account_id =c.account_id WHERE ACTIVE_FLG = 'Y' AND a.account_id = ?";
        AccountInfo accountInfo = null;
        try (final Connection connection = dataSource.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(psSelect);
            stmt.setString(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs == null || rs.getFetchSize() > 1) {
                logger.error("incorrect fetch result {}", accountId);
            }
            while (rs.next()) {
                accountInfo = new AccountInfo();
                accountInfo.setId(rs.getString("account_id"));
                accountInfo.setCustomerId(rs.getString("customer_id"));
                accountInfo.setTitle(rs.getString("title"));
                accountInfo.setDescription(rs.getString("description"));
                accountInfo.setVersion(rs.getString("version"));
                accountInfo.setBalance(MoneyUtil.toIntegerRepr(rs.getBigDecimal("version")));
                accountInfo.setCreationDate(rs.getDate("creation_Date"));


            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

        return accountInfo;
    }

    public void delete(String accountId){
        Objects.requireNonNull(accountId);
        try (final Connection connection = dataSource.getConnection()){
            String psDelete = "UPDATE account_info SET ACTIVE_FLG = 'N' WHERE account_id = ?";
            PreparedStatement psEntity = connection.prepareStatement(psDelete);
            psEntity.setString(1, accountId);
            int count = psEntity.executeUpdate();
            if (count != 1) {
                logger.error("Failed to update account_info: {}", accountId);
            }

        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

    }

    public int createAccount(AccountInfo accountInfo) {
        Objects.requireNonNull(accountInfo);

        String psInsert = "INSERT INTO account_info (account_id, title, description, version, balance,creation_Date) VALUES (?, ? ,?,?,? , current_date);";
        String psInsert2 = "INSERT INTO account_customer (account_id, customer_Id) VALUES (?, ?);";
        int count = 0;
        try (final Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement stmt = connection.prepareStatement(psInsert);
            stmt.setString(1, accountInfo.getId());
            stmt.setString(2, accountInfo.getTitle());
            stmt.setString(3, accountInfo.getDescription());
            stmt.setString(4, accountInfo.getVersion());
            stmt.setLong(5, accountInfo.getBalance());

            count = stmt.executeUpdate();
            if (count>0) {
                try (PreparedStatement psXref = connection.prepareStatement(psInsert2)) {

                    psXref.setString(1,  accountInfo.getId());
                    psXref.setString(2, accountInfo.getCustomerId());

                    psXref.executeUpdate();
                }
            }
            connection.commit();

            if (count != 1) {
                logger.error("Failed to insert account_info: {}", accountInfo.getId());
            }
        } catch (SQLException e) {
            logger.error("SqlException:", e);
        }

        return count;
    }

}

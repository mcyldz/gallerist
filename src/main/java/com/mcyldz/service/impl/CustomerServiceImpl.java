package com.mcyldz.service.impl;

import com.mcyldz.dto.DtoAccount;
import com.mcyldz.dto.DtoAddress;
import com.mcyldz.dto.DtoCustomer;
import com.mcyldz.dto.DtoCustomerIU;
import com.mcyldz.exception.BaseException;
import com.mcyldz.exception.ErrorMessage;
import com.mcyldz.exception.MessageType;
import com.mcyldz.model.Account;
import com.mcyldz.model.Address;
import com.mcyldz.model.Customer;
import com.mcyldz.repository.AccountRepository;
import com.mcyldz.repository.AddressRepository;
import com.mcyldz.repository.CustomerRepository;
import com.mcyldz.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;

    private final AddressRepository addressRepository;

    private final AccountRepository accountRepository;

    public CustomerServiceImpl (CustomerRepository customerRepository, AddressRepository addressRepository, AccountRepository accountRepository){
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.accountRepository = accountRepository;
    }

    private Customer createCustomer(DtoCustomerIU dtoCustomerIU){

        Optional<Address> optionalAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
        if (optionalAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressId().toString()));
        }

        Optional<Account> optionalAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
        if (optionalAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString()));
        }

        Customer customer = new Customer();
        customer.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCustomerIU, customer);
        customer.setAccount(optionalAccount.get());
        customer.setAddress(optionalAddress.get());

        return customer;
    }

    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
        DtoAccount dtoAccount = new DtoAccount();
        DtoAddress dtoAddress = new DtoAddress();
        DtoCustomer dtoCustomer = new DtoCustomer();

        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));

        BeanUtils.copyProperties(savedCustomer, dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);

        return dtoCustomer;
    }
}

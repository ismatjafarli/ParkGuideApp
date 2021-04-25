package com.example.parksapp.model;

import java.util.List;

public class Contacts
{
    private List<PhoneNumbers> phoneNumbers;

    private List<EmailAddresses> emailAddresses;

    public void setPhoneNumbers(List<PhoneNumbers> phoneNumbers){
        this.phoneNumbers = phoneNumbers;
    }
    public List<PhoneNumbers> getPhoneNumbers(){
        return this.phoneNumbers;
    }
    public void setEmailAddresses(List<EmailAddresses> emailAddresses){
        this.emailAddresses = emailAddresses;
    }
    public List<EmailAddresses> getEmailAddresses(){
        return this.emailAddresses;
    }
}


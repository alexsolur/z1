module appication {
    uses com.epam.service.Service;
    uses com.epam.bank.Bank;
    requires jmp.cloud.service.impl;
    requires jmp.cloud.bank.impl;
    requires jmp.dto;
}
import com.epam.bank.Bank;
import com.epam.bank.cloud.CloudBank;

module jmp.cloud.bank.impl {
    requires transitive jmp.bank.api;
    requires jmp.dto;
    exports com.epam.bank.cloud;
    provides Bank with CloudBank;
}
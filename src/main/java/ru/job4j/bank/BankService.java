package ru.job4j.bank;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс содержит основную логику работы  банковской системы
 * @author Ivan Svyatobog
 * @version 1.0
 */

public class BankService {
    /**
     * Поле представленно в виде map в которой хранятся пользователь с привязанними к ним счетами
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public boolean deleteUser(String passport) {
        return users.remove(new User(passport, "")) != null;
    }

    /**
     * Метод добавляеет новый счет для пользователя при условии,
     * что пользователь с такими паспортными данными существует в системе и
     * что счета с такими реквизитами еще нет.
     * @param passport номер паспорта пользователя
     * @param account обьект класса Account, который содержит номер счета и баланс.
     */

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод возвращает обьект пользователя по номеру паспорта
     * при условии, что такой пользователь зарегистрирован в системе
     * @return возвращает обьект User
     */

    public User findByPassport(String passport) {
        User result = null;
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = user;
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает объект аккаунта (счета) по пасорту и реквизиту
     * при условии, что такие пользователь и счет существуют.
     * @param passport номер паспорта
     * @param requisite реквизит
     * @return возвращает номер счета Account
     */

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        Account result = null;
        if (user != null) {
            List<Account> accounts = users.get(user);
            for (Account account : accounts) {
                if (requisite.equals(account.getRequisite())) {
                    result = account;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Метод осуществляет перевод денежных средств с одного счета на другой
     * с проверкой условий, что счета зарегистрированы в системе и что на /
     * счете отправителя имеется достаточная сумма для перевода
     * @param srcPassport паспортные данные владельца счета отправителя
     * @param srcRequisite счет для списания денежных средств
     * @param destPassport паспортные данные владельца счета получателя
     * @param destRequisite счет для зачисления денежных средств
     * @param amount сумма для перевода
     * @return возращает true, если перевод произведен успешно и false если перевод не осуществелен
     */

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
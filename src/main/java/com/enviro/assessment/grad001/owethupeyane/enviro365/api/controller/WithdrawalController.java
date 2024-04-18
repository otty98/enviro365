package com.enviro.assessment.grad001.owethupeyane.enviro365.api.controller;

import com.enviro.assessment.grad001.owethupeyane.enviro365.api.model.Investor;
import com.enviro.assessment.grad001.owethupeyane.enviro365.api.model.Product;
import com.enviro.assessment.grad001.owethupeyane.enviro365.api.model.Withdrawal;
import com.enviro.assessment.grad001.owethupeyane.enviro365.api.repo.ProductType;
import com.enviro.assessment.grad001.owethupeyane.enviro365.api.respond.ApiResponder;
import com.enviro.assessment.grad001.owethupeyane.enviro365.doa.InvestorRepo;
import com.enviro.assessment.grad001.owethupeyane.enviro365.doa.ProductRepo;
import com.enviro.assessment.grad001.owethupeyane.enviro365.doa.WithdrawalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling HTTP requests related to withdrawals.
 * This class defines REST endpoints for making withdrawals from investor accounts.
 */
@RestController
public class WithdrawalController extends ApiResponder {

    @Autowired
    private WithdrawalRepo withdrawalRepo;

    @Autowired
    private InvestorRepo investorRepo;

    @Autowired
    private ProductRepo productRepo;

    /**
     * REST endpoint to process a withdrawal request.
     * @param withdrawal Withdrawal object containing withdrawal details.
     * @return ResponseEntity containing HTTP status and response body.
     */
    @PostMapping("/withdraw")
    public ResponseEntity<Object> makeWithdrawal(@RequestBody Withdrawal withdrawal) {
        Product product = productRepo.findById(withdrawal.getProductID()).orElse(null);
        Investor investor = investorRepo.findById(withdrawal.getInvestorID()).orElse(null);

        if (product != null && investor != null) {
            int age = investor.getAge();
            double availableBalance = product.getCurrentBalance() * 0.9;

            Withdrawal withdraw = new Withdrawal(withdrawal.getInvestorID(), withdrawal.getProductID(), withdrawal.getAmount());

            // Check if investor's age meets withdrawal age limit
            if (age >= ProductType.valueOf(product.getProductType()).getWithdrawAgeLimit()) {
                // Check if withdrawal amount does not exceed 90% of the available balance
                if (withdrawal.getAmount() <= availableBalance) {
                    updateWithdrawInformation(withdraw, "Successful");
                    return getResponse(HttpStatus.OK, "Withdrawal successful");
                } else {
                    updateWithdrawInformation(withdraw, "Request above 90%");
                    return getResponseError(HttpStatus.CONFLICT, "Cannot withdraw more than 90% of the balance.");
                }
            } else {
                updateWithdrawInformation(withdraw, "Age below " + ProductType.RETIREMENT.getWithdrawAgeLimit());
                return getResponseError(HttpStatus.TOO_EARLY, "You need to be at least " + ProductType.RETIREMENT.getWithdrawAgeLimit() + " years old.");
            }
        }

        return getResponseError(HttpStatus.BAD_REQUEST, "Failed, invalid investor or product ID.");
    }

    /**
     * Helper method to update withdrawal information and save to repository.
     * @param withdrawal Withdrawal object to update.
     * @param result Result of the withdrawal request.
     */
    private void updateWithdrawInformation(Withdrawal withdrawal, String result) {
        withdrawal.setWithdrawResults(result);
        withdrawalRepo.save(withdrawal);
    }
}

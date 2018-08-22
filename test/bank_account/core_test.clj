(ns bank-account.core-test
  (:require [clojure.test :refer :all]
    ;[bank-account.core :refer :all]
            [bank-account.movements :refer :all]))

(def initial-balance
  {:account/id "C1" :account/balance 100.00}
  )

(defn requests
  [customer-atom]
  [(future (withdraw-atom customer-atom 70.00))
   (future (withdraw-atom customer-atom 20.00))
   (future (withdraw-atom customer-atom 80.00))])

(deftest test-racing-condition
  ( testing "Trying 3 simultaneous withdraw with atom. Should not pass 0 balance"
    (is (= '({:account/id "C1", :account/balance 30.0}
              {:account/id "C1", :account/balance 10.0}
              nil)
           (map deref (requests (atom initial-balance)))))))


(run-tests)
(ns bank-account.core
  (:require [bank-account.movements :as mv]))

(def customers-def
  {:account/id "C1" :account/balance 100.00})

(def customers-atom
  (atom {:account/id "C1" :account/balance 100.00}))


(defn request-def
  []
  [(future (mv/withdraw-def customers-def 70.00))
   (future (mv/withdraw-def customers-def 40.00))
   (future (mv/withdraw-def customers-def 80.00))])

(defn request-atom
  []
  [(future (mv/withdraw-atom customers-atom 70.00))
   (future (mv/withdraw-atom customers-atom 40.00))
   (future (mv/withdraw-atom customers-atom 80.00))])



(defn -main
  [& args]
  (println "Withdraw using def:")
  (run! #(println (deref %)) (request-def))
  (println "Withdraw using Atom:")
  (run! #(println (deref %)) (request-atom)))


; Migrar esse main para o ambiente de teste!



;--------------------------------------------------------------

(defn create-new-account
  [account-id]
  {:account/id account-id :account/balance 0.00})

(defn teste-criando-conta
  [& args]
  (let [new-customer (create-new-account "C1")
        wallet (mv/add-balance new-customer 10.00)]
    ;(def future-wallet (future (mv/withdraw wallet 5.00)))
    ;(println (deref future-wallet))
    (println (mv/withdraw-def wallet 3.00))
    (println (mv/withdraw-def wallet 5.00))))


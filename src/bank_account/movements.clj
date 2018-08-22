(ns bank-account.movements)

(defn add-balance
  [customers amount]
  (assoc customers :account/balance amount))

(defn withdraw-def
  [wallet amount]
  (if (>= (- (:account/balance wallet) amount) 0)
    (update wallet :account/balance - amount)
    nil))

(defn withdraw-atom
  [wallet amount]
  (let [deref-wallet @wallet]
    (if (>= (- (:account/balance deref-wallet) amount) 0)
      (swap! wallet update-in [:account/balance] - amount)
      nil)))

(ns marketentry.registry-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.registry :as registry]))

(deftest engagement-fee-recompute
  (let [e {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 860000.0}]
    (is (== 860000.0 (registry/compute-engagement-fee e)))
    (is (true? (registry/engagement-fee-matches-claim? e))))
  (let [bad {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 999000.0}]
    (is (false? (registry/engagement-fee-matches-claim? bad)))))

(deftest register-draft-and-submit
  (let [d (registry/register-draft "eng-1" "SUR" 0)
        s (registry/register-submit "eng-1" "SUR" 0)]
    (is (= "SUR-DFT-000000" (get d "draft_number")))
    (is (= "SUR-SUB-000000" (get s "submit_number")))
    (is (nil? (get-in d ["certificate" "proof"])))
    (is (= "draft-unsigned" (get-in s ["certificate" "status"])))))

(deftest register-requires-ids
  (is (thrown? Exception (registry/register-draft "" "SUR" 0)))
  (is (thrown? Exception (registry/register-submit "eng-1" "" 0))))

;; --------------- Comptabiliteitsbesluit Artikel 18 procurement thresholds ---------------

(deftest low-tier-never-requires-tender-or-approval
  (testing "at or below SRD 4,000, neither tender nor Council-of-Ministers approval is required"
    (is (false? (registry/public-tender-required? {:contract-value-srd 4000})))
    (is (false? (registry/council-approval-required? {:contract-value-srd 4000})))
    (is (false? (registry/procurement-threshold-noncompliant?
                 {:contract-value-srd 4000 :procurement-path nil
                  :presidential-exception? false :council-of-ministers-approved? false})))
    (is (false? (registry/procurement-threshold-noncompliant?
                 {:contract-value-srd 3000 :procurement-path nil
                  :presidential-exception? false :council-of-ministers-approved? false})))))

(deftest middle-tier-requires-council-of-ministers-approval
  (testing "above SRD 4,000 and at or below SRD 30,000: no tender required, but Council-of-Ministers approval is"
    (is (true? (registry/council-approval-required? {:contract-value-srd 10000})))
    (is (false? (registry/public-tender-required? {:contract-value-srd 10000})))
    (is (true? (registry/procurement-threshold-noncompliant?
                {:contract-value-srd 10000 :procurement-path :onderhandse-gunning
                 :presidential-exception? false :council-of-ministers-approved? false})))
    (is (false? (registry/procurement-threshold-noncompliant?
                 {:contract-value-srd 10000 :procurement-path :onderhandse-gunning
                  :presidential-exception? false :council-of-ministers-approved? true})))
    (is (false? (registry/procurement-threshold-noncompliant?
                 {:contract-value-srd 30000 :procurement-path :onderhandse-gunning
                  :presidential-exception? false :council-of-ministers-approved? true}))
        "exactly SRD 30,000 is still the middle tier (strictly above 30,000 triggers the top tier)")))

(deftest top-tier-requires-tender-or-presidential-exception
  (testing "above SRD 30,000: must be openbare-aanbesteding, unless a presidential exception is on file"
    (is (true? (registry/public-tender-required? {:contract-value-srd 50000})))
    (is (true? (registry/procurement-threshold-noncompliant?
                {:contract-value-srd 50000 :procurement-path :onderhandse-gunning
                 :presidential-exception? false :council-of-ministers-approved? false}))
        "non-tendered, no exception -> noncompliant")
    (is (false? (registry/procurement-threshold-noncompliant?
                 {:contract-value-srd 50000 :procurement-path :onderhandse-gunning
                  :presidential-exception? true :council-of-ministers-approved? false}))
        "non-tendered, but a presidential exception is on file -> compliant")
    (is (false? (registry/procurement-threshold-noncompliant?
                 {:contract-value-srd 50000 :procurement-path :openbare-aanbesteding
                  :presidential-exception? false :council-of-ministers-approved? false}))
        "properly tendered -> compliant, no exception needed")))

(deftest missing-contract-value-is-not-independently-flagged-here
  (testing "a nil :contract-value-srd never fires this check (caught upstream by evidence-incomplete instead)"
    (is (false? (registry/procurement-threshold-noncompliant?
                 {:contract-value-srd nil :procurement-path nil
                  :presidential-exception? false :council-of-ministers-approved? false})))))

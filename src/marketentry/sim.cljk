(ns marketentry.sim
  "Demo driver -- `clojure -M:dev:run`. Walks a clean engagement
  through intake -> jurisdiction assessment -> filing draft
  (escalate/approve/commit) -> filing submit (escalate/approve/
  commit), then shows HARD-hold scenarios, including all three tiers
  of the flagship `procurement-threshold-noncompliant` check."
  (:require [langgraph.graph :as g]
            [marketentry.store :as store]
            [marketentry.operation :as op]))

(def operator {:actor-id "op-1" :actor-role :market-entry-operator :phase 3})

(defn- exec-op [actor tid request context]
  (g/run* actor {:request request :context context} {:thread-id tid}))

(defn- approve! [actor tid]
  (g/run* actor {:approval {:status :approved :by "op-1"}} {:thread-id tid :resume? true}))

(defn -main [& _]
  (let [db (store/seed-db)
        actor (op/build db)]
    (println "== engagement/intake eng-1 (SUR, clean) ==")
    (println (exec-op actor "t1" {:op :engagement/intake :subject "eng-1"
                                  :patch {:id "eng-1" :operator "Paramaribo Handelshuis N.V."}} operator))

    (println "== jurisdiction/assess eng-1 (escalates -- human approves) ==")
    (println (exec-op actor "t2" {:op :jurisdiction/assess :subject "eng-1"} operator))
    (println (approve! actor "t2"))

    (println "== filing/draft eng-1 (always escalates -- actuation/draft-filing) ==")
    (let [r (exec-op actor "t3" {:op :filing/draft :subject "eng-1"} operator)]
      (println r)
      (println "-- human market-entry operator approves --")
      (println (approve! actor "t3")))

    (println "== filing/submit eng-1 (always escalates -- actuation/submit-filing) ==")
    (let [r (exec-op actor "t4" {:op :filing/submit :subject "eng-1"} operator)]
      (println r)
      (println "-- human market-entry operator approves --")
      (println (approve! actor "t4")))

    (println "== jurisdiction/assess eng-2 (no spec-basis -> HARD hold) ==")
    (println (exec-op actor "t5" {:op :jurisdiction/assess :subject "eng-2" :no-spec? true} operator))

    (println "== jurisdiction/assess eng-3 (sets up fee-mismatch) ==")
    (println (exec-op actor "t6" {:op :jurisdiction/assess :subject "eng-3"} operator))
    (println (approve! actor "t6"))
    (println (exec-op actor "t6b" {:op :filing/draft :subject "eng-3"} operator))
    (println (approve! actor "t6b"))
    (println "== filing/submit eng-3 (fee mismatch -> HARD hold) ==")
    (println (exec-op actor "t7" {:op :filing/submit :subject "eng-3"} operator))

    (println "== jurisdiction/assess eng-4 (sets up top-tier procurement-threshold-noncompliant) ==")
    (println (exec-op actor "t8" {:op :jurisdiction/assess :subject "eng-4"} operator))
    (println (approve! actor "t8"))
    (println (exec-op actor "t8b" {:op :filing/draft :subject "eng-4"} operator))
    (println (approve! actor "t8b"))
    (println "== filing/submit eng-4 (SRD 50,000, onderhandse-gunning, no presidential exception -> HARD hold) ==")
    (println (exec-op actor "t9" {:op :filing/submit :subject "eng-4"} operator))

    (println "== jurisdiction/assess eng-5 (sets up tax-registration-unconfirmed) ==")
    (println (exec-op actor "t10" {:op :jurisdiction/assess :subject "eng-5"} operator))
    (println (approve! actor "t10"))
    (println (exec-op actor "t10b" {:op :filing/draft :subject "eng-5"} operator))
    (println (approve! actor "t10b"))
    (println "== filing/submit eng-5 (tax-registration-unconfirmed -> HARD hold) ==")
    (println (exec-op actor "t11" {:op :filing/submit :subject "eng-5"} operator))

    (println "== jurisdiction/assess eng-6 (sets up middle-tier noncompliant, no Council-of-Ministers approval) ==")
    (println (exec-op actor "t12" {:op :jurisdiction/assess :subject "eng-6"} operator))
    (println (approve! actor "t12"))
    (println (exec-op actor "t12b" {:op :filing/draft :subject "eng-6"} operator))
    (println (approve! actor "t12b"))
    (println "== filing/submit eng-6 (SRD 10,000, no Raad van Ministers approval -> HARD hold) ==")
    (println (exec-op actor "t13" {:op :filing/submit :subject "eng-6"} operator))

    (println "== jurisdiction/assess eng-7 (middle-tier, Council-of-Ministers approval ON FILE) ==")
    (println (exec-op actor "t14" {:op :jurisdiction/assess :subject "eng-7"} operator))
    (println (approve! actor "t14"))
    (println (exec-op actor "t14b" {:op :filing/draft :subject "eng-7"} operator))
    (println (approve! actor "t14b"))
    (println "== filing/submit eng-7 (approved -> only the normal actuation escalation, NOT a procurement-threshold hold) ==")
    (let [r (exec-op actor "t15" {:op :filing/submit :subject "eng-7"} operator)]
      (println r)
      (println "-- human market-entry operator approves --")
      (println (approve! actor "t15")))

    (println "== jurisdiction/assess eng-8 (top-tier, PRESIDENTIAL EXCEPTION on file) ==")
    (println (exec-op actor "t16" {:op :jurisdiction/assess :subject "eng-8"} operator))
    (println (approve! actor "t16"))
    (println (exec-op actor "t16b" {:op :filing/draft :subject "eng-8"} operator))
    (println (approve! actor "t16b"))
    (println "== filing/submit eng-8 (exception honored -> only the normal actuation escalation, NOT a procurement-threshold hold) ==")
    (let [r (exec-op actor "t17" {:op :filing/submit :subject "eng-8"} operator)]
      (println r)
      (println "-- human market-entry operator approves --")
      (println (approve! actor "t17")))

    (println "== jurisdiction/assess eng-9 (top-tier, PROPERLY tendered openbare-aanbesteding) ==")
    (println (exec-op actor "t18" {:op :jurisdiction/assess :subject "eng-9"} operator))
    (println (approve! actor "t18"))
    (println (exec-op actor "t18b" {:op :filing/draft :subject "eng-9"} operator))
    (println (approve! actor "t18b"))
    (println "== filing/submit eng-9 (properly tendered -> only the normal actuation escalation, NOT a procurement-threshold hold) ==")
    (let [r (exec-op actor "t19" {:op :filing/submit :subject "eng-9"} operator)]
      (println r)
      (println "-- human market-entry operator approves --")
      (println (approve! actor "t19")))

    (println "== filing/draft eng-1 AGAIN (double-draft -> HARD hold) ==")
    (println (exec-op actor "t20" {:op :filing/draft :subject "eng-1"} operator))

    (println "== filing/submit eng-1 AGAIN (double-submit -> HARD hold) ==")
    (println (exec-op actor "t21" {:op :filing/submit :subject "eng-1"} operator))

    (println "== audit ledger ==")
    (doseq [f (store/ledger db)] (println f))

    (println "== draft records ==")
    (doseq [r (store/draft-history db)] (println r))

    (println "== submit records ==")
    (doseq [r (store/submit-history db)] (println r))))

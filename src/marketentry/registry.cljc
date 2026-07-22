(ns marketentry.registry
  "Pure-function market-entry filing-draft + filing-submit record
  construction -- an append-only market-entry book-of-record draft.

  Like every sibling actor's registry, there is no single international
  reference-number standard for a public-procurement market-entry
  filing -- every jurisdiction assigns its own format. This namespace
  does NOT invent one; it builds a jurisdiction-scoped sequence number
  and validates the record's required fields, the same honest,
  non-fabricating discipline `marketentry.facts` uses.

  `engagement-fee-matches-claim?` is an HONEST reapplication of the
  SAME ground-truth-recompute DISCIPLINE sibling actors use (verify a
  claimed monetary total against the entity's own recorded quantity x
  unit fields), reapplied to a market-entry engagement fee line.

  `procurement-threshold-noncompliant?` grounds this jurisdiction's
  FLAGSHIP governor check -- genuinely different in shape from every
  sibling actor's own flagship (Guyana's non-resident trigger set,
  Guinea-Bissau's boolean registration flag): a numeric THREE-TIER
  threshold recompute, directly grounded in the Comptabiliteitsbesluit
  (G.B. 1953 no. 100, amended through S.B. 2004 no. 116) Artikel 18's
  own text (fetched and read this session, see `marketentry.facts`):

    - contract value > SRD 30,000  -> MUST be an openbare aanbesteding
      (public tender), unless a presidential exception is on file
      (with its own recorded reason -- the law requires the deviation
      decision to state its grounds and be copied to Parliament).
    - SRD 4,000 < contract value <= SRD 30,000 -> a non-tendered
      ('onderhandse') procurement path is allowed, but REQUIRES Raad
      van Ministers (Council of Ministers) approval on file.
    - contract value <= SRD 4,000 -> neither a tender nor Council-of-
      Ministers approval is required.

  This is pure data + pure functions -- no I/O, no network call to any
  real Surinamese government system, no host date API. It builds the
  RECORD an operator would keep, not the act of submitting a real
  filing itself (that is `marketentry.operation`'s `:filing/submit`,
  always human-gated -- see README Actuation)."
  (:require [clojure.string :as str]))

(defn- unsigned-certificate
  "Every certificate this actor produces is UNSIGNED -- signature is
  the market-entry operator's act, not this actor's."
  [kind subject record-id]
  {"@context" ["https://www.w3.org/ns/credentials/v2"]
   "type" ["VerifiableCredential" kind]
   "credentialSubject" {"id" subject "record" record-id}
   "proof" nil
   "issued_by_registry" false
   "status" "draft-unsigned"})

(defn- zero-pad [n w]
  (let [s (str n)]
    (str (apply str (repeat (max 0 (- w (count s))) "0")) s)))

(defn compute-engagement-fee
  "The ground-truth engagement fee for `engagement`'s own `:base-fee`
  and `:monitoring-months` x `:monthly-rate` -- a single flat
  base + months x rate calculation, not a full pricing engine."
  [{:keys [base-fee monthly-rate monitoring-months]}]
  (+ (double base-fee)
     (* (double monthly-rate) (double monitoring-months))))

(defn engagement-fee-matches-claim?
  "Does `engagement`'s own `:claimed-fee` equal the independently
  recomputed `compute-engagement-fee`?"
  [{:keys [claimed-fee] :as engagement}]
  (== (double claimed-fee) (compute-engagement-fee engagement)))

;; --------------- Comptabiliteitsbesluit Artikel 18 procurement thresholds ---------------

(def public-tender-threshold-srd
  "Contracts strictly ABOVE this SRD amount MUST be an openbare
  aanbesteding (public tender), per Comptabiliteitsbesluit Artikel 18,
  unless a presidential exception is on record. Source: G.B. 1953 no.
  100, amended through S.B. 2004 no. 116."
  30000)

(def council-of-ministers-approval-threshold-srd
  "Non-tendered contracts strictly ABOVE this SRD amount (and at or
  below `public-tender-threshold-srd`) require Raad van Ministers
  (Council of Ministers) approval, per the same Comptabiliteitsbesluit
  Artikel 18."
  4000)

(defn public-tender-required?
  "TRUE when `engagement`'s own `:contract-value-srd` exceeds the
  public-tender threshold -- an openbare aanbesteding is legally
  required unless a presidential exception is on file."
  [{:keys [contract-value-srd]}]
  (boolean (and contract-value-srd (> contract-value-srd public-tender-threshold-srd))))

(defn council-approval-required?
  "TRUE when `engagement`'s own `:contract-value-srd` is in the middle
  tier (above the Council-of-Ministers-approval threshold, at or below
  the public-tender threshold) -- no tender required, but Council-of-
  Ministers approval is."
  [{:keys [contract-value-srd] :as engagement}]
  (boolean
   (and contract-value-srd
        (> contract-value-srd council-of-ministers-approval-threshold-srd)
        (not (public-tender-required? engagement)))))

(defn procurement-threshold-noncompliant?
  "TRUE when `engagement`'s own claimed procurement path does not
  satisfy what Comptabiliteitsbesluit Artikel 18 requires for its own
  `:contract-value-srd`:

    - above SRD 30,000: `:procurement-path` must be
      `:openbare-aanbesteding`, UNLESS `:presidential-exception?` is
      true (an on-the-record deviation).
    - above SRD 4,000 and at or below SRD 30,000: requires
      `:council-of-ministers-approved?` true (tender not required).
    - at or below SRD 4,000: always compliant (neither required).

  A missing `:contract-value-srd` is NOT independently flagged here --
  that gap is caught upstream by `marketentry.governor`'s
  evidence-incomplete check (the contract-value/procurement-path
  classification record is part of this jurisdiction's own
  required-evidence checklist, see `marketentry.facts`)."
  [{:keys [procurement-path presidential-exception? council-of-ministers-approved?]
    :as engagement}]
  (boolean
   (cond
     (public-tender-required? engagement)
     (and (not= procurement-path :openbare-aanbesteding)
          (not (true? presidential-exception?)))

     (council-approval-required? engagement)
     (not (true? council-of-ministers-approved?))

     :else false)))

;; ----------------------------- filing records -----------------------------

(defn register-draft
  "Validate + construct the FILING-DRAFT registration DRAFT -- the
  market-entry operator's own act of preparing a filing package. Pure
  function -- does not touch any real Surinamese government system."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "draft: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "draft: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "draft: sequence must be >= 0" {})))
  (let [draft-number (str (str/upper-case jurisdiction) "-DFT-" (zero-pad sequence 6))
        record {"record_id" draft-number
                "kind" "filing-draft"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "draft_number" draft-number
     "certificate" (unsigned-certificate "FilingDraft" draft-number draft-number)}))

(defn register-submit
  "Validate + construct the FILING-SUBMIT registration DRAFT -- the
  market-entry operator's own act of actually submitting a filing
  (always human-gated upstream)."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "submit: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "submit: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "submit: sequence must be >= 0" {})))
  (let [submit-number (str (str/upper-case jurisdiction) "-SUB-" (zero-pad sequence 6))
        record {"record_id" submit-number
                "kind" "filing-submit"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "submit_number" submit-number
     "certificate" (unsigned-certificate "FilingSubmit" submit-number submit-number)}))

(defn append [history result]
  (conj (vec history) (get result "record")))
